package ru.practicum.ewm.base.dto;

import lombok.*;
import ru.practicum.ewm.base.enums.State;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventFullDto {
    private Integer id;
    private String annotation;
    private CategoryDto category;
    private int confirmedRequests;
    private String createdOn;
    private String description;
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private LocationDto location;
    private boolean paid;
    private Long participantLimit;
    private String publishedOn;
    private boolean requestModeration;
    private State state;
    private String title;
    private Long views;
}
