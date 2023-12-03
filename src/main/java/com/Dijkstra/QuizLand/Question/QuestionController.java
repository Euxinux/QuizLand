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

    @PostMapping
    ResponseEntity<?> createQuestion(@RequestBody @Valid QuestionCreateDTO questionToSave){
        Question questionSaved = questionService.createQuestion(questionToSave);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(questionSaved.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    QuestionReadDTO readQuestion(@PathVariable("id") int questionId){
        return questionService.readQuestion(questionId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestion(@PathVariable("id") int questionId){
        questionService.deleteQuestion(questionId);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateQuestionContent(@PathVariable("id") int questionId,
                               @RequestBody @Valid QuestionUpdateDTO questionToUpdate){
        questionService.updateQuestion(questionId, questionToUpdate);
    }

    @PatchMapping("/update/active/{id}")
    @ResponseStatus(HttpStatus.OK)
    void toggleActive(@PathVariable("id") int questionId){
        questionService.toggleActive(questionId);
    }

    @GetMapping("/{id}/options")
    @ResponseStatus(HttpStatus.OK)
    List<String> getQuestionOptions(@PathVariable("id") int questionId){
        return questionService.getQuestionOptions(questionId);
    }

    @PatchMapping("{id}/options/{optionId}")
    @ResponseStatus(HttpStatus.OK)
    void updateQuestionOption(@PathVariable("id") int questionId,
                              @PathVariable("optionId") int optionId,
                              @RequestBody String newOptionContent){
        questionService.updateQuestionOption(questionId, optionId, newOptionContent);
    }

    @GetMapping("{id}/options/correct")
    @ResponseStatus(HttpStatus.OK)
    String getCorrectAnswer(@PathVariable("id") int questionId){
        return questionService.getCorrectAnswer(questionId);
    }


    @PatchMapping("{id}/options/correct/{optionId}")
    @ResponseStatus(HttpStatus.OK)
    void changeCorrectQuestionAnswer(@PathVariable("id") int questionId,
                                     @PathVariable("optionId") int optionId){
        questionService.changeCorrectAnswer(questionId, optionId);
    }



}