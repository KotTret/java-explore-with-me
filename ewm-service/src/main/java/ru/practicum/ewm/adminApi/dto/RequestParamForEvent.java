package ru.practicum.ewm.adminApi.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.base.enums.State;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestParamForEvent {

    private List<Long> users;
    private List<State> states;
    private List<Long> categories;
    private String rangeStart;
    private String rangeEnd;
    private int from;
    private int size;
}