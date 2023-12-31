package ru.practicum.comments.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.model.dto.CommentDto;
import ru.practicum.comments.model.dto.NewCommentDto;
import ru.practicum.comments.model.dto.UpdateCommentRequest;
import ru.practicum.comments.service.CommentService;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}")
public class CommentControllerPrivate {

    private final CommentService commentService;

    @PostMapping("/events/{eventId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable Long userId,
                                    @PathVariable Long eventId,
                                    @Valid @RequestBody NewCommentDto newCommentDto) {
        log.info("POST '/users/{}/events/{}/comments'создать comment с event и с id={}", userId, eventId, eventId);
        return commentService.createComment(userId, eventId, newCommentDto);
    }

    @PostMapping("/comments/{commentId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLikeToComment(@PathVariable Long userId,
                                 @PathVariable Long commentId) {
        log.info("POST '/users/{}/comments/{}/like' понравившийся comment с id={}", userId, commentId, commentId);
        commentService.addLikeToComment(userId, commentId);
    }

    @PostMapping("/comments/{commentId}/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDislikeToComment(@PathVariable Long userId,
                                    @PathVariable Long commentId) {
        log.info("POST '/users/{}/comments/{}/dislike' dislike comment с id={}", userId, commentId, commentId);
        commentService.addDislikeToComment(userId, commentId);
    }

    @PatchMapping("/comments/{commentId}")
    public CommentDto updateCommentById(@PathVariable Long userId,
                                        @PathVariable Long commentId,
                                        @Valid @RequestBody UpdateCommentRequest updateCommentRequest) {
        log.info("PATCH '/users/{}/comments/{}' обновить comment с id={}", userId, commentId, commentId);
        return commentService.updateComment(userId, commentId, updateCommentRequest);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentByUser(@PathVariable Long userId,
                                    @PathVariable Long commentId) {
        log.info("DELETE '/users/{}/comments/{}' удалить comment с id={}", userId, commentId, commentId);
        commentService.deleteCommentByUser(userId, commentId);
    }
}
