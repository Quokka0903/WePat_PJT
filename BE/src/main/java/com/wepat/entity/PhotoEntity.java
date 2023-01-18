package com.wepat.entity;

import com.wepat.dto.CommentDto;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PhotoEntity {
    @ApiParam(value = "사진ID", required = true)
    private String docId;
    @ApiParam(value = "업로드한 사용자ID")
    private String memberId;
    @ApiParam(value = "사진 url", required = true)
    private String url;
    @ApiParam(value = "댓글Dto 리스트")
    private List<CommentDto> commentList;
    @ApiParam(value = "SNS 등록 여부")
    private boolean isOnSNS;
    @ApiParam(value = "좋아요 수")
    private int like;
    @ApiParam(value = "신고자 명단")
    private List<String> reportIdList;
    @ApiParam(value = "등록일")
    private Timestamp date;
}
