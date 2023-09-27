package ru.practicum.location.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class LocationDto {
    private Float lat;
    private Float lon;
}
