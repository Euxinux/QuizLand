package com.Dijkstra.QuizLand.Question.Option;

import com.Dijkstra.QuizLand.Audit;
import com.Dijkstra.QuizLand.Question.Question;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

     public Option(Option optionSource, Question question){
        this.optionContent = optionSource.getOptionContent();
        this.question = question;
        this.correct = optionSource.isCorrect();
    }

}
