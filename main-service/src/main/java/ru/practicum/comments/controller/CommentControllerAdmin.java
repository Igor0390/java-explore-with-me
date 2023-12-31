package ru.practicum.comments.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.model.dto.CommentDto;
import ru.practicum.comments.service.CommentService;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/admin/comments/{commentId}")
public class CommentControllerAdmin {

    private final CommentService commentService;

    @GetMapping
    public CommentDto getCommentById(@PathVariable Long commentId) {
        log.info("GET '/admin/comments/{}' получает comment с id={}", commentId, commentId);
        return commentService.getCommentById(commentId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentByAdmin(@PathVariable Long commentId) {
        log.info("DELETE '/admin/comments/{}' удалить comment с id={}", commentId, commentId);
        commentService.deleteCommentByAdmin(commentId);
    }
}
