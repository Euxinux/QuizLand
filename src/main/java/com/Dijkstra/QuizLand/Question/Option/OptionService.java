package com.Dijkstra.QuizLand.Question.Option;

import com.Dijkstra.QuizLand.Question.Exception.CorrectAnswerNotExistsException;
import com.Dijkstra.QuizLand.Question.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OptionService{

        public void hasOneCorrectAnswer(List<Option> options) {
            if(options.stream().filter(Option::isCorrect).count() != 1) {
                throw new CorrectAnswerNotExistsException("One option should be true!");
            }
        }

    public List<String> getAllOptions(Set<Option> questionExists){
        return questionExists
                .stream()
                .map(Option::getOptionContent)
                .toList();
    }
}
