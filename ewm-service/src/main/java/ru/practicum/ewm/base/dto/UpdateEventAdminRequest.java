package ru.practicum.ewm.base.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.base.model.Location;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEventAdminRequest {
    @Length(min = 20, max = 2000)
    String annotation;
    Integer category;
    @Length(min = 20, max = 7000)
    String description;
    String eventDate;
    Location location;
    boolean paid;
    Long participantLimit;
    boolean requestModeration;
    String stateAction;
    @Length(min = 3, max = 120)
    String title;
}
