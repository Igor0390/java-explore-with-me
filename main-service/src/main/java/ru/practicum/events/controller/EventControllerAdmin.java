package ru.practicum.events.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.util.constants.ConstantPattern;
import ru.practicum.events.model.dto.EventFullDto;
import ru.practicum.events.service.EventService;
import ru.practicum.events.model.dto.UpdateEventAdminRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class EventControllerAdmin {

    private final EventService eventService;

    @GetMapping("/admin/events")
    public List<EventFullDto> getEventsByUserIds(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = ConstantPattern.PATTERN) LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = ConstantPattern.PATTERN) LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET at '/admin/events' to get all events with params: users={}, states={}, categories={}, rangeStart={}, rangeEnd={}, from={}, size={}",
                users, states, categories, rangeStart, rangeEnd, from, size);
        return eventService.getEventsByUserIds(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/admin/events/{eventId}")
    public EventFullDto updateEvent(@PathVariable Long eventId,
                                    @Valid @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        log.info("UPDATE at '/admin/events/{}' to update event with id={} with body={}", eventId, eventId, updateEventAdminRequest.toString());
        return eventService.updateEventByAdmin(eventId, updateEventAdminRequest);
    }
}
