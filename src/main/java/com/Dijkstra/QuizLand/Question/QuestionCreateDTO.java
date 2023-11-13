package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Enum.Category;
import com.Dijkstra.QuizLand.Question.Enum.Difficulty;
import com.Dijkstra.QuizLand.Question.Option.Option;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
class QuestionCreateDTO{
    @NotEmpty(message = "Question content cannot be empty!")
    private String questionContent;
    @Size(min = 2, max = 6, message = "Correct question should has at least 2 options, but max 6!")
    private List<Option> options;
    @NotNull(message = "Category cannot be empty!")
    @Size(min = 1, max = 3, message = "The category size must be 1 - 3")
    @Enumerated(EnumType.STRING)
    private List<Category> category;
    @NotNull(message = "Difficulty cannot be empty!")
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private boolean active;
}
