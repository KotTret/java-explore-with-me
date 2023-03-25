package ru.practicum.ewm.base.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.base.model.Location;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEventAdminRequest {
    @Length(min = 20, max = 2000)
    String annotation;
    Long category;
    @Length(min = 20, max = 7000)
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Location location;
    Boolean paid;
    Long participantLimit;
    Boolean requestModeration;
    String stateAction;
    @Length(min = 3, max = 120)
    String title;
}
