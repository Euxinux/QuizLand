package com.Dijkstra.QuizLand.Question.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OptionsExceptionHandler{

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(CorrectAnswerNotExistsException.class)
    protected ApiErrorResponse handleIncorrectOptions(CorrectAnswerNotExistsException ex){
        return new ApiErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                ex.getMessage()
        );
    }

}
