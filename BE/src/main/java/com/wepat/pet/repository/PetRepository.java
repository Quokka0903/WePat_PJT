package com.wepat.pet.repository;

import com.wepat.pet.PetDto;
import com.wepat.pet.PetEntity;
import com.wepat.pet.WeightDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PetRepository {
    // 반려동물 추가하기
    void addPet(PetDto pet) throws ExecutionException, InterruptedException;
    // 전체 펫 정보 가져오기
    List<PetDto> getAllPets(String calendarId) throws ExecutionException, InterruptedException;
    // 펫 정보 가져오기
    PetDto getPet(String calendarId) throws ExecutionException, InterruptedException;
    // 반려동물 정보 변경하기
    void modifyPet(String petId, PetDto pet) throws ExecutionException, InterruptedException;
    // 반려동물 몸무게 추가
    void addPetWeight(String petId, WeightDto weightDto) throws ExecutionException, InterruptedException;
    // 반려동물 몸무게 정보 변경하기
    void modifyPetWeight(String petId, String date, WeightDto weightDto) throws ExecutionException, InterruptedException;
    // 반려동물 삭제하기
    void deletePet(String petId) throws ExecutionException, InterruptedException;

}
