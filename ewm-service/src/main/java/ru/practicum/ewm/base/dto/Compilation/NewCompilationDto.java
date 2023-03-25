package ru.practicum.ewm.base.dto.Compilation;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCompilationDto {
    private Set<Long> events;
    private Boolean pinned;
    @NotBlank
    private String title;
}
