package ru.practicum.ewm.base.dto.event;


import lombok.*;
import ru.practicum.ewm.base.dto.Category.CategoryDto;
import ru.practicum.ewm.base.dto.user.UserShortDto;

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
    private Long id;
    private UserShortDto initiator;
    private boolean paid;
    private String title;
    private long views;

}
