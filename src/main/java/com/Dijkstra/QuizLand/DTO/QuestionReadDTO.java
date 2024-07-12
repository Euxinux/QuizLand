package com.Dijkstra.QuizLand.DTO;

import com.Dijkstra.QuizLand.Enum.Category;
import com.Dijkstra.QuizLand.Enum.Difficulty;
import com.Dijkstra.QuizLand.Model.Option;
import com.Dijkstra.QuizLand.Model.Question;
import lombok.Getter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
    DTO used for the displaying particular field of entity for the user.
 */

@Getter
public class QuestionReadDTO{
    private final int id;
    private final String questionContent;
    private final Set<String> options;
    private final List<Category> category;
    private final Difficulty difficulty;
    private final boolean active;

    public QuestionReadDTO(Question questionSource){
        this.id = questionSource.getId();
        this.questionContent = questionSource.getQuestionContent();
        this.options = questionSource
                .getOptions()
                .stream()
                .map(Option::getOptionContent)
                .collect(Collectors.toSet());
        this.category = questionSource.getCategory();
        this.difficulty = questionSource.getDifficulty();
        this.active = questionSource.isActive();
    }
}
