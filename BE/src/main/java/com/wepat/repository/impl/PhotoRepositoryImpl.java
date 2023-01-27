package com.wepat.repository.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.wepat.dto.CommentDto;
import com.wepat.dto.PhotoDto;
import com.wepat.entity.PhotoEntity;
import com.wepat.exception.photo.AlreadyDeleteImage;
import com.wepat.exception.photo.NotExistImage;
import com.wepat.repository.MemberRepository;
import com.wepat.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
    private final static Logger logger = LoggerFactory.getLogger(MemberRepository.class);
    private final static String CALENDAR_COLLECTION = "calendar";
    private final static String MEMBER_COLLECTION = "member";
    private final static String PHOTO_COLLECTION = "photo";
    private final Firestore db = FirestoreClient.getFirestore();
    private final CollectionReference calCollection = db.collection(CALENDAR_COLLECTION);
    private final CollectionReference memCollection = db.collection(MEMBER_COLLECTION);
    private final CollectionReference photoCollection = db.collection(PHOTO_COLLECTION);

    // 가족 앨범 전체 조회
    @Override
    public List<PhotoDto> getAllPhoto(String calendarId) throws ExecutionException, InterruptedException {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        List<QueryDocumentSnapshot> photoDocumentsList = photoCollection.get().get().getDocuments();
        for (QueryDocumentSnapshot photoList : photoDocumentsList) {
            if ((photoList.toObject(PhotoEntity.class).getCalendarId()).equals(calendarId)) {
                photoDtoList.add(photoCollection.document(photoList.getId()).get().get().toObject(PhotoDto.class));
            }
        }
        return photoDtoList;
    }
    
    // 특정 이미지
    @Override
    public PhotoDto getPhotoById(String calendarId, String photoId) throws ExecutionException, InterruptedException {
        DocumentSnapshot photoSnaphot = photoCollection.document(photoId).get().get();
        if (photoSnaphot.exists()) {
            return photoSnaphot.toObject(PhotoDto.class);
        } else {
            throw new NotExistImage();
        }
    }

    // 이미지 추가
    @Override
    public ResponseEntity<?> addPhoto(String calendarId, PhotoDto photoDto) {
        DocumentReference photoDocRef = photoCollection.document();
        photoDto.setPhotoId(photoDocRef.getId());
        photoCollection.document(photoDocRef.getId()).set(photoDto);
        return new ResponseEntity<>("등록 성공!", HttpStatus.OK);
    }

    // 이미지 삭제
    @Override
    public ResponseEntity<?> deletePhoto(String calendarId, String photoId) throws ExecutionException, InterruptedException {
        DocumentReference photoDocRef = photoCollection.document(photoId);
        ApiFuture<?> responseEntityApiFuture = db.runTransaction(transaction -> {
            DocumentSnapshot photoSnapshot = transaction.get(photoDocRef).get();
            if (photoSnapshot.exists()) {
                transaction.delete(photoDocRef);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return null;
            }
        });
        if (responseEntityApiFuture.get()!=null) {
            return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
        } else {
            throw new AlreadyDeleteImage();
        }
    }

    // SNS 업로드
    @Override
    public ResponseEntity<?> updateSNSByPhoto(String calendarId, String photoId) throws ExecutionException, InterruptedException {
        DocumentReference photoDocRef = photoCollection.document(photoId);
        ApiFuture<?> responseEntityApiFuture = db.runTransaction(transaction -> {
            DocumentSnapshot photoSnapshot = transaction.get(photoDocRef).get();
            if (photoSnapshot.exists()) {
                boolean sns = photoCollection.document(photoId).get().get().toObject(PhotoEntity.class).isSns();
                transaction.update(photoDocRef, "sns", !sns);
                if (sns) {
                    return new ResponseEntity<>("업로드 취소!", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("업로드 성공!", HttpStatus.OK);
                }
            } else {
                return null;
            }
        });
        if (responseEntityApiFuture.get()==null) {
            throw new NotExistImage();
        } else {
            return (ResponseEntity<?>) responseEntityApiFuture.get();
        }
    }

    @Override
    public ResponseEntity<?> addCommentByPhoto(String calendarId, String photoId, CommentDto commentDto) throws ExecutionException, InterruptedException {
        DocumentReference photoDocRef = photoCollection.document(photoId);

        ApiFuture<?> responseEntityApiFuture = db.runTransaction(transaction -> {
            DocumentSnapshot photoSnapshot = transaction.get(photoDocRef).get();
            if (photoSnapshot.exists()) {
                List<CommentDto> commentList = photoDocRef.get().get().toObject(PhotoEntity.class).getCommentList();
                commentList.add(commentDto);
                transaction.update(photoDocRef, "commentList", commentList);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return null;
            }
        });
        if (responseEntityApiFuture.get()!=null) {
            return (ResponseEntity<?>) responseEntityApiFuture.get();
        } else {
            throw new NotExistImage();
        }
    }

    @Override
    public PhotoDto addLike(String photoId) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public PhotoDto addReport(String photoId) throws ExecutionException, InterruptedException {
        return null;
    }

}
