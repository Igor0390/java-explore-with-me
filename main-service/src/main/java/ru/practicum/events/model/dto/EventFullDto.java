package ru.practicum.events.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.category.model.dto.CategoryDto;
import ru.practicum.enums.EventState;
import ru.practicum.location.model.dto.LocationDto;
import ru.practicum.users.model.dto.UserShortDto;
import ru.practicum.util.constants.ConstantPattern;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {

    private String annotation;

    @NotNull
    private CategoryDto category;
    private Long confirmedRequests;

    @JsonFormat(pattern = ConstantPattern.PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdOn;
    private String description;

    @NotNull
    @JsonFormat(pattern = ConstantPattern.PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime eventDate;
    private Long id;

    @NotNull
    private UserShortDto initiator;

    @NotNull
    private LocationDto location;

    @NotNull
    private Boolean paid;

    @PositiveOrZero
    private Integer participantLimit = 0;

    @JsonFormat(pattern = ConstantPattern.PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime publishedOn;
    private Boolean requestModeration = true;
    private EventState state;

    @NotNull
    private String title;
    private Long views;
}
