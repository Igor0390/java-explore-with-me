package ru.practicum.events.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.enums.EventStateActionUser;
import ru.practicum.location.model.dto.LocationDto;
import ru.practicum.util.constants.ConstantPattern;

import javax.validation.constraints.Future;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest {

    @Size(max = 2000, min = 20)
    private String annotation;
    private Long category;

    @Size(max = 7000, min = 20)
    private String description;

    @Future
    @JsonFormat(pattern = ConstantPattern.PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime eventDate;
    private LocationDto location;
    private Boolean paid;

    @PositiveOrZero
    private Integer participantLimit;
    private Boolean requestModeration;
    private EventStateActionUser stateAction;

    @Size(max = 120, min = 3)
    private String title;
}
