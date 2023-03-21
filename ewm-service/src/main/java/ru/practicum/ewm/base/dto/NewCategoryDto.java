package ru.practicum.ewm.base.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCategoryDto {
    @NotBlank
    private String name;

    @Override
    public String toString() {
        return "NewCategoryDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
