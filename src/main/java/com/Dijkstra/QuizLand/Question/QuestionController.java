package com.Dijkstra.QuizLand.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class QuestionController{
    private final QuestionService service;

    @GetMapping
    ResponseEntity<List<Question>> readAllQuestions(){
        List<Question> questions = service.readAllQuestion();
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    ResponseEntity<Question> createQuestion(
            @RequestBody QuestionDTO questionToSave
    ){
        Question question = service.createQuestion(questionToSave);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(question.getId());
        return ResponseEntity.created(location).build();
    }
}
