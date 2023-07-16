package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Enum.Category;
import com.Dijkstra.QuizLand.Question.Enum.Difficulty;
import lombok.Getter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class QuestionReadDTO{
    private final int id;
    private final String questionContent;
    private final Set<String> options;
    private final List<Category> category;
    private final Difficulty difficulty;
    private final boolean active;

    QuestionReadDTO(Question questionSource){
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