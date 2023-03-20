package ru.practicum.ewm.base.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;
    private String status;

    @Override
    public String toString() {
        return "EventRequestStatusUpdateRequest{" +
                "requestIds=" + requestIds.toString() +
                ", status='" + status + '\'' +
                '}';
    }
}
