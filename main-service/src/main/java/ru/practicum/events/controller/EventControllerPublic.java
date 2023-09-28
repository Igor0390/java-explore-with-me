package ru.practicum.events.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.util.constants.ConstantPattern;
import ru.practicum.enums.EventSortOptions;
import ru.practicum.events.model.dto.EventFullDto;
import ru.practicum.events.model.dto.EventShortDto;
import ru.practicum.events.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class EventControllerPublic {

    private final EventService eventService;
    private final ConstantPattern constantPattern;

    @GetMapping("/events")
    public List<EventShortDto> getAllEventsByParams(@RequestParam(required = false) String text,
                                                    @RequestParam(required = false) List<Long> categories,
                                                    @RequestParam(required = false) Boolean paid,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = constantPattern.PATTERN) LocalDateTime rangeStart,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = constantPattern.PATTERN) LocalDateTime rangeEnd,
                                                    @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                                    @RequestParam(required = false) EventSortOptions sort,
                                                    @RequestParam(defaultValue = "0") Integer from,
                                                    @RequestParam(defaultValue = "10") Integer size,
                                                    HttpServletRequest request) {
        log.info("GET at '/events' to get all events by params: text={}, categories={}, " +
                        "paid={}, rangeStart={}, rangeEnd={}, onlyAvailable={}, sort={}, from={}, size={}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        return eventService.getEventsByParams(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request);
    }

    @GetMapping("/events/{eventId}")
    public EventFullDto getEventById(@PathVariable Long eventId,
                                     HttpServletRequest request) {
        log.info("GET at '/events/{}' to get event by id={}", eventId, eventId);
        return eventService.getEventById(eventId, request);
    }

}
