package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Audit;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private Question question;
    private boolean correct;
    @Embedded
    @Getter(AccessLevel.NONE)
    private Audit audit = new Audit();

    Option(Option optionSource, Question question){
        this.optionContent = optionSource.getOptionContent();
        this.question = question;
        this.correct = optionSource.isCorrect();
    }

}
