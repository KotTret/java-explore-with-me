package ru.practicum.ewm.base.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationRequestDto {
    private Integer id;
    private String created;
    private Integer event;
    private Long requester;
    private String status;
}
