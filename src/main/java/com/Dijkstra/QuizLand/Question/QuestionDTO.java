package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Enum.Category;
import com.Dijkstra.QuizLand.Question.Enum.Difficulty;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
class QuestionDTO{
    private String questionContent;
    @Size(min = 2, max = 6)
    private List<Option> options;
    private List<Category> category;
    @Enumerated
    private Difficulty difficulty;
    private boolean active;
}
