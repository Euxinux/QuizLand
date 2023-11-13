package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Audit;
import com.Dijkstra.QuizLand.Question.Enum.Category;
import com.Dijkstra.QuizLand.Question.Enum.Difficulty;
import com.Dijkstra.QuizLand.Question.Option.Option;
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

    Question (QuestionCreateDTO questionFromUser){
        this.questionContent = questionFromUser.getQuestionContent();
        this.category = questionFromUser.getCategory();
        this.options = new HashSet<>();
        this.difficulty = questionFromUser.getDifficulty();
        this.active = questionFromUser.isActive();
    }
    

}
