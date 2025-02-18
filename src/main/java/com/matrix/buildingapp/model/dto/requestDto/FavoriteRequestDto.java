package com.matrix.buildingapp.model.dto.requestDto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRequestDto {
    private Long userId;
    private Long announcementId;
}
