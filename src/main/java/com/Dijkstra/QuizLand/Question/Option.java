package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Audit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
public class Option{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String optionContent;
    @ManyToOne
    @JsonIgnore
    private Question question;
    private boolean correct;
    @Enumerated
    private Audit audit = new Audit();

    Option(Option optionSource, Question question){
        this.optionContent = optionSource.getOptionContent();
        this.question = question;
        this.correct = optionSource.isCorrect();
    }

}
