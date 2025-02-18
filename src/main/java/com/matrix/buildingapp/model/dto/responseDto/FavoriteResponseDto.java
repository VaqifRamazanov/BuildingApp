package com.matrix.buildingapp.model.dto.responseDto;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteResponseDto {
   private UserResponseDto user;
    private List<AnnouncementResponseDto> announcements;
}
