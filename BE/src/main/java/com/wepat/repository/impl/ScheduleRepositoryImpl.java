package com.wepat.repository.impl;

import com.wepat.dto.ScheduleDto;
import com.wepat.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final Logger logger = LoggerFactory.getLogger(ScheduleRepositoryImpl.class);
    private final String COLLECTION_NAME = "schedule";

    @Override
    public List<Timestamp> getAllSchedule(String calendarId) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public ScheduleDto addSchedule(ScheduleDto Schedule) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public List<Timestamp> getScheduleByPet(String petId) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public List<ScheduleDto> getScheduleByDate(String CalendarId, Timestamp date) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public ScheduleDto modifySchedule(String calendarId, Timestamp writtenTime, ScheduleDto scheduleDto) throws ExecutionException, InterruptedException {
        return null;
    }
}
