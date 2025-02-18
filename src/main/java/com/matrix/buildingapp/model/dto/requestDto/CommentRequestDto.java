package com.matrix.buildingapp.model.dto.requestDto;

import com.matrix.buildingapp.model.entity.ConstructionCompany;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentRequestDto {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private Integer constructionCompanyId;

}
