package ru.practicum.ewm.base.dto.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.base.dto.ParticipationRequestDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestStatusUpdateResult {
    List<ParticipationRequestDto> confirmedRequests;
    List<ParticipationRequestDto> rejectedRequests;
}
