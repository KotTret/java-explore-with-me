package ru.practicum.ewm.base.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.base.dto.location.LocationDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewEventDto {
    @Length(min = 20, max = 2000)
    private String annotation;
    @NotNull
    private Long category;
    @Length(min = 20, max = 7000)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    @NotNull
    private LocationDto location;
    private Boolean paid;
    private Long participantLimit;
    private Boolean requestModeration;
    @Length(min = 3, max = 120)
    String title;

    @Override
    public String toString() {
        return "NewEventDto{" +
                "annotation='" + annotation + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", location=" + location +
                ", paid=" + paid +
                ", participantLimit=" + participantLimit +
                ", requestModeration=" + requestModeration +
                ", title='" + title + '\'' +
                '}';
    }
}
