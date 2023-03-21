package ru.practicum.ewm.base.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
}
