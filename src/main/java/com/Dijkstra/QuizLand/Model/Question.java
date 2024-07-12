package com.Dijkstra.QuizLand.Model;

import com.Dijkstra.QuizLand.Component.Audit;
import com.Dijkstra.QuizLand.DTO.QuestionCreateDTO;
import com.Dijkstra.QuizLand.Enum.Category;
import com.Dijkstra.QuizLand.Enum.Difficulty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionContent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Option> options;
    private List<Category> category;
    private Difficulty difficulty;
    private boolean active;
    @Getter(AccessLevel.NONE)
    @Embedded
    private final Audit audit = new Audit();

    public Question(QuestionCreateDTO questionFromUser){
        this.questionContent = questionFromUser.getQuestionContent();
        this.category = questionFromUser.getCategory();
        this.options = new HashSet<>();
        this.difficulty = questionFromUser.getDifficulty();
        this.active = questionFromUser.isActive();
    }
}
