package ru.practicum.comments.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.comments.model.dto.CommentDto;
import ru.practicum.comments.service.CommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
public class CommentControllerPublic {

    private CommentService commentService;

    @GetMapping("/events/{eventId}/comments")
    public List<CommentDto> getAllCommentsByEventId(@PathVariable Long eventId,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET '/events/{}/comments' получить все comments по event с id={}", eventId, eventId);
        return commentService.getAllCommentsByEventId(eventId, from, size);
    }

    @GetMapping("/events/{eventId}/comments/count")
    public Long getCommentsCountByEventId(@PathVariable Long eventId) {
        log.info("GET '/events/{eventId}/comments/count' получить кол-во comments по event с id={}", eventId);
        return commentService.getCommentsCountByEventId(eventId);
    }
}
