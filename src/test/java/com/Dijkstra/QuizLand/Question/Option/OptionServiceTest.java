package com.Dijkstra.QuizLand.Question.Option;

import com.Dijkstra.QuizLand.Question.Exception.CorrectAnswerNotExistsException;
import com.Dijkstra.QuizLand.Question.Exception.OptionNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OptionServiceTest{

    private final OptionService service;
    private static List<Option> listOfOptions;
    private static Set<Option> setOfOptions;

    @Autowired
    OptionServiceTest(OptionService service){
        this.service = service;
    }

    @BeforeAll
    static void beforeAll(){
        listOfOptions = List.of(
                new Option(),
                new Option()
        );

        listOfOptions.get(0).setId(0);
        listOfOptions.get(0).setOptionContent("Old Question Content");
        listOfOptions.get(0).setCorrect(true);
        listOfOptions.get(1).setId(1);
        listOfOptions.get(1).setOptionContent("Old Question Content");
        listOfOptions.get(1).setCorrect(false);
        setOfOptions = new HashSet<>(listOfOptions);
    }

    @Test
    void hasOneCorrectAnswer_zeroCorrectAnswers_throwCorrectAnswerNotExists(){
        //when then
        Assertions.assertThrows(CorrectAnswerNotExistsException.class,
                () -> service.hasOneCorrectAnswer(listOfOptions));
    }

    @Test
    void hasOneCorrectAnswer_oneCorrectAnswers_throwCorrectAnswerNotExists(){
        //given
        listOfOptions.get(0).setCorrect(true);

        //when then
        Assertions.assertDoesNotThrow(() -> service.hasOneCorrectAnswer(listOfOptions));
    }

    @Test
    void hasOneCorrectAnswer_multipleCorrectAnswers_throwCorrectAnswerNotExists(){
        //given
        listOfOptions.get(1).setCorrect(true);
        listOfOptions.get(0).setCorrect(true);

        //when then
        Assertions.assertThrows(CorrectAnswerNotExistsException.class,
                () -> service.hasOneCorrectAnswer(listOfOptions));
    }

    @Test
    void updateQuestionOption_successfullyUpdate_optionContentHasBeenUpdated(){
        //given
        int optionId = 0;
        String newOptionContent = "new Option content";

        //when
        service.updateQuestionOption(setOfOptions, optionId, newOptionContent);
        long count = setOfOptions
                .stream()
                .filter(option -> option
                        .getOptionContent()
                        .equals(newOptionContent))
                .count();

        //then
        Assertions.assertEquals(1, count);
    }

    @Test
    void updateQuestionOption_optionIdDoesNotExists_throwOptionNotFoundException(){
        // given
        int optionId = 2;
        String newOptionContent = "new Option content";

        // when then
        Assertions.assertThrows(OptionNotFoundException.class,
                () -> service.updateQuestionOption(setOfOptions, optionId, newOptionContent));
    }

    @Test
    void changeCorrectAnswer_successfullyUpdate_correctAnswerHasBeenChanged(){
        //given
        int optionId = 1;

        //when
        service.changeCorrectAnswer(setOfOptions, optionId);
        long result = setOfOptions
            .stream()
            .filter(option -> option.isCorrect() && option.getId() == optionId)
            .count();

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void changeCorrectAnswer_optionIdDoesNotExists_throwOptionNotFoundException(){
        //given
        int optionId = 2;

        // when then
        Assertions.assertThrows(OptionNotFoundException.class,
                () -> service.changeCorrectAnswer(setOfOptions,optionId));
    }
}