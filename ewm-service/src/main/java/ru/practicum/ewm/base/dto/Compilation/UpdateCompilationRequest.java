package ru.practicum.ewm.base.dto.Compilation;

import lombok.*;
import ru.practicum.ewm.base.util.notblanknull.NotBlankNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCompilationRequest {
    private Set<Long> events;
    private Boolean pinned;
    @NotBlankNull
    private String title;
}
