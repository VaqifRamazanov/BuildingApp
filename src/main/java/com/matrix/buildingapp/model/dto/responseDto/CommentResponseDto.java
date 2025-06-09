package com.matrix.buildingapp.model.dto.responseDto;

import com.matrix.buildingapp.model.entity.ConstructionCompany;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private Integer constructionCompanyId;


}
