package ru.practicum.ewm.base.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventShortDto {
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime eventDate;
    private Integer id;
    private UserShortDto initiator;
    private boolean paid;
    private String title;
    private long views;

}
