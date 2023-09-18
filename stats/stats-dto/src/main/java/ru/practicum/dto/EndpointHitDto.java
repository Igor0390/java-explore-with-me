package ru.practicum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class EndpointHitDto {

    private Long id;

    @NotBlank(message = "Название приложения не может быть пустым или нулевым")
    private String app;

    @NotBlank(message = "URI не может быть пустым или нулевым")
    private String uri;

    @NotBlank(message = "IP не может быть пустым или нулевым")
    private String ip;

    @NotNull(message = "Timestamp не может быть пустым или нулевым")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
