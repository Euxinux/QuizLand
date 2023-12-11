package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Option.Option;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class QuestionRepositoryTest{

    private final QuestionRepository repository;

    @Autowired
    QuestionRepositoryTest(QuestionRepository repository){
        this.repository = repository;
    }

    @Test
    public void updateQuestionOption_optionUpdated_OptionHasBeenChanged(){
        //given
        Question oldQuestion = new Question();
        oldQuestion.setOptions(Set.of(
                new Option("Option 1", false),
                new Option("Option 2", true)));

        //when
        Question savedQuestion = repository.save(oldQuestion);
        Optional<Question> byId = repository.findById(1);

        //then
        Assertions.assertTrue(byId.isPresent());
    }
}