package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Exception.CorrectAnswerNotExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService{

        void hasOneCorrectAnswer(List<Option> options) {
            if(options.stream().filter(Option::isCorrect).count() != 1) {
                throw new CorrectAnswerNotExistsException("One option should be true!");
            }
        }
}
