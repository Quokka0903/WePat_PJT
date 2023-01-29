package com.wepat.pet;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    public PetDto(PetEntity pet) {
        this.calendarId = pet.getCalendarId();
        this.petId = pet.getPetId();
        this.name = pet.getName();
        this.animalType = pet.getAnimalType();
        this.age = pet.getAge();
        this.photoURL = pet.getPhotoURL();
        this.color = pet.getColor();
        this.birthday = pet.getBirthday();
        this.adaptday = pet.getAdaptday();
        this.weightList = pet.getWeightList();
    }
    @ApiParam(value = "반려동물 데이터가 표시되는 달력")
    private String calendarId;
    @ApiParam(value = "반려동물 ID", hidden = true)
    private String petId;
    @ApiParam(value = "반려동물 이름")
    private String name;
    @ApiParam(value = "동물 종류")
    private String animalType;
    @ApiParam(value = "반려동물 나이")
    private int age;
    @ApiParam(value = "반려동물 프로필")
    private String photoURL;
    @ApiParam(value = "프로필 색상")
    private String color;
    @ApiParam(value = "반려동물 생일")
    private String birthday;
    @ApiParam(value = "반려동물 입양일")
    private String adaptday;
    @ApiParam(value = "몸무게 정보(Dto) 리스트", hidden = true)
    private List<WeightDto> weightList;
}