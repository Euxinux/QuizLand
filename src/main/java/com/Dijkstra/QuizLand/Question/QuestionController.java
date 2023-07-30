package com.Dijkstra.QuizLand.Question;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class QuestionController{
    private final QuestionService questionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<QuestionReadDTO> readAllQuestions(){
        return questionService.readAllQuestion();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    QuestionReadDTO readQuestion(
            @PathVariable("id") int questionId
    ){
        return questionService.readQuestion(questionId);
    }

    @PostMapping
    ResponseEntity<?> createQuestion(
            @RequestBody @Valid QuestionDTO questionToSave
    ){
        Question questionSaved = questionService.createQuestion(questionToSave);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(questionSaved.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestion(
            @PathVariable("id") Integer questionId
    ){
        questionService.deleteQuestion(questionId);
    }
}
