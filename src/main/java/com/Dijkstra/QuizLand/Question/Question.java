package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Audit;
import com.Dijkstra.QuizLand.Question.Enum.Category;
import com.Dijkstra.QuizLand.Question.Enum.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionContent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Option> options;
    private List<Category> category;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private boolean active;
    @Embedded
    @Getter(AccessLevel.NONE)
    private final Audit audit = new Audit();

    Question (QuestionDTO source){
        this.questionContent = source.getQuestionContent();
        this.category = source.getCategory();
        this.options = new HashSet<>();
        this.difficulty = source.getDifficulty();
        this.active = source.isActive();
    }

}
