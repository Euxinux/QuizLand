package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Enum.Category;
import com.Dijkstra.QuizLand.Question.Enum.Difficulty;
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
class QuestionDTO{
    @NotEmpty(message = "Question content cannot be empty!")
    private String questionContent;
    @Size(min = 2, max = 6, message = "Correct question should has at least 2 options, but max 6!")
    private List<Option> options;
    @NotNull(message = "Category cannot be empty!")
    @Enumerated(EnumType.STRING)
    private List<Category> category;
    @NotNull(message = "Difficulty cannot be empty!")
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private boolean active;
}
