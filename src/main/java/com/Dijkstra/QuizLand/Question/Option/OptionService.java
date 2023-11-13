package com.Dijkstra.QuizLand.Question.Option;

import com.Dijkstra.QuizLand.Question.Exception.CorrectAnswerNotExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService{

        public void hasOneCorrectAnswer(List<Option> options) {
            if(options.stream().filter(Option::isCorrect).count() != 1) {
                throw new CorrectAnswerNotExistsException("One option should be true!");
            }
        }
}
