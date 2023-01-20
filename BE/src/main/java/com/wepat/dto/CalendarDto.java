package com.wepat.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CalendarDto {
    @ApiParam(value = "펫ID")
    private List<String> petId;
    @ApiParam(value = "가계부 내용 목록")
    private List<FinanceDto> financeList;
}
