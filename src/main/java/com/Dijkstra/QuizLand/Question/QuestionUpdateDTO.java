package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Enum.Category;
import com.Dijkstra.QuizLand.Question.Enum.Difficulty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
/*
    DTO used for updating Question entity.
 */


public record QuestionUpdateDTO(
        @NotBlank String questionContent,
        List<Category> categories,
        Difficulty difficulty){
}
