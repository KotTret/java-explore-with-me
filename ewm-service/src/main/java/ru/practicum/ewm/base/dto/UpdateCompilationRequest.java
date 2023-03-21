package ru.practicum.ewm.base.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCompilationRequest {
    private Set<Long> events;
    private Boolean pinned;
    private String title;
}
