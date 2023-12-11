package com.Dijkstra.QuizLand.Question.Option;

import com.Dijkstra.QuizLand.Question.Exception.CorrectAnswerNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OptionServiceTest{
    @InjectMocks
    OptionService service;

    List<Option> listOfoOptions = List.of(
            new Option("Option 1", false),
            new Option("Option 2", false)
    );

    @Test
    void hasOneCorrectAnswer_zeroCorrectAnswers_throwCorrectAnswerNotExists(){
        //when then
        Assertions.assertThrows(CorrectAnswerNotExistsException.class,
                () -> service.hasOneCorrectAnswer(listOfoOptions));
    }

    @Test
    void hasOneCorrectAnswer_oneCorrectAnswers_throwCorrectAnswerNotExists(){
        //given
        listOfoOptions.get(0).setCorrect(true);
        //when then
        Assertions.assertDoesNotThrow(() -> service.hasOneCorrectAnswer(listOfoOptions));
    }

    @Test
    void hasOneCorrectAnswer_multipleCorrectAnswers_throwCorrectAnswerNotExists(){
        //given
        listOfoOptions.get(1).setCorrect(true);
        listOfoOptions.get(0).setCorrect(true);
        //when then
        Assertions.assertThrows(CorrectAnswerNotExistsException.class,
                () -> service.hasOneCorrectAnswer(listOfoOptions));
    }



    //  TESTING getOption method

    @Test
    void updateQuestionOption_optionIdDoesNotExists_throwOptionNotFoundException(){
        //given
        Set<Option> setOfOptions = new HashSet<>(listOfoOptions);
        int questionId = 1;
        String newQuestionContent = "new Option content";
        //when
        service.updateQuestionOption(setOfOptions, questionId, newQuestionContent);

    }
}