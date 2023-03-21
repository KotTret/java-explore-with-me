package ru.practicum.ewm.base.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCompilationDto {
    private Set<Integer> events;
    private Boolean pinned;
    @NotBlank
    private String title;
}
