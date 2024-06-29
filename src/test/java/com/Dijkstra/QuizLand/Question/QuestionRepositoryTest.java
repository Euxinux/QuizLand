package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Model.Option;
import com.Dijkstra.QuizLand.Question.Model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
class QuestionRepositoryTest{

    private final QuestionRepository repository;

    @Autowired
    QuestionRepositoryTest(QuestionRepository repository){
        this.repository = repository;
    }

    @Test
    public void updateQuestionContent_correctUpdate_NoErrors(){
        //given
        Question oldQuestion = new Question();
        oldQuestion.setOptions(Set.of(
                new Option(),
                new Option()));
        String newQuestionContent = "New question content";

        //when
        Question savedQuestion = repository.save(oldQuestion);
        repository.updateQuestionContent(newQuestionContent, savedQuestion.getId());
        Optional<Question> newQuestion = repository.findById(savedQuestion.getId());

        //then
        Assertions.assertEquals(newQuestionContent, newQuestion.get().getQuestionContent());
    }




}