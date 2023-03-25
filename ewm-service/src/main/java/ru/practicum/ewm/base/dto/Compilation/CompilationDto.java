package ru.practicum.ewm.base.dto.Compilation;

import lombok.*;
import ru.practicum.ewm.base.dto.event.EventShortDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationDto {

    private List<EventShortDto> events;
    private Long id;
    private Boolean pinned;
    private String title;
}
