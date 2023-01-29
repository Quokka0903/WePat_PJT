package com.wepat.photo.service;

import com.wepat.photo.PhotoDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PhotoService {
    // 전체 앨범 가져오기
    List<PhotoDto> getAllPhoto(String calendarId) throws ExecutionException, InterruptedException ;
    // 특정 사진 선택 시 보여주기
    PhotoDto getPhotoById(String calendarId, String photoId) throws ExecutionException, InterruptedException ;
    // 이미지 추가
    void addPhoto(String calendarId, PhotoDto photoDto);
    // 사진 삭제하기
    PhotoDto deletePhoto(String photoId) throws ExecutionException, InterruptedException ;
    // 사진 좋아요 반영하기
    PhotoDto addLike(String photoId) throws ExecutionException, InterruptedException ;
    // 사진 신고 반영하기
    PhotoDto addReport(String photoId) throws ExecutionException, InterruptedException ;
}