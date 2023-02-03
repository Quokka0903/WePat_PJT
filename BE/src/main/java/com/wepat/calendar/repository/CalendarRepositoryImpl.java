package com.wepat.calendar.repository;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.wepat.schedule.ScheduleDto;
import com.wepat.schedule.ScheduleEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class CalendarRepositoryImpl implements CalendarRepository {
    private static final String SCHEDULE_COLLECTION = "schedule";

    @Override
    public Map<String, List<String>> getScheduleByMonth(String calendarId, String startDate, String endDate) {
        try {
            Map<String, List<String>> datePetMap = new HashMap<>();

            CollectionReference scheduleCollection = FirestoreClient.getFirestore().collection(SCHEDULE_COLLECTION);
            List<QueryDocumentSnapshot> documents = scheduleCollection
                    .whereEqualTo("calendarId", calendarId)
                    .whereGreaterThanOrEqualTo("date", startDate)
                    .whereLessThanOrEqualTo("date", startDate)
                    .get().get().getDocuments();

            for (QueryDocumentSnapshot doc : documents) {
                ScheduleEntity entity = doc.toObject(ScheduleEntity.class);
                if (datePetMap.containsKey(entity.getDate())) {
                    datePetMap.get(entity.getDate()).add(entity.getPetId());
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(entity.getPetId());
                    datePetMap.put(entity.getDate(), list);
                }
            }
            return datePetMap;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ScheduleDto> getScheduleListByDate(String calendarId, String date) throws ExecutionException, InterruptedException {
        CollectionReference scheduleCollection = FirestoreClient.getFirestore().collection(SCHEDULE_COLLECTION);
        List<ScheduleDto> scheduleDtoList = scheduleCollection
                .whereEqualTo("calendarId", calendarId)
                .whereEqualTo("date", date)
                .get().get().toObjects(ScheduleDto.class);
        return scheduleDtoList;
    }

    @Override
    public void addSchedule(ScheduleDto scheduleDto) {
    }

    @Override
    public ScheduleDto getScheduleByDate(String calendarId, String date) {
        return null;
    }

    @Override
    public void modifySchedule(String calendarId, ScheduleDto scheduleDto, String date) {
    }

    @Override
    public void deleteSchedule(String calendarId, String date) {
    }

    @Override
    public ScheduleDto getScheduleDetailByDate(String calendarId, String date) {
        return null;
    }
}
