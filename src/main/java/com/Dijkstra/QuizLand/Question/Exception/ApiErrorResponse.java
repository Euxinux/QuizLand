package com.Dijkstra.QuizLand.Question.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Setter
@Getter
class ApiErrorResponse{
    private HttpStatus code;
    private String message;
    private LocalDateTime timestamp;

    ApiErrorResponse(HttpStatus code, String message){
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
