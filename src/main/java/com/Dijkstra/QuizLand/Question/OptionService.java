package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Exception.CorrectAnswerNotExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService{

        void hasAtLeastOneCorrectAnswer(List<Option> options) {
            if(options.stream().noneMatch(Option::isCorrect))
                throw new CorrectAnswerNotExistsException("At least on option should be true");
        }
}
