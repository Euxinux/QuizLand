package com.Dijkstra.QuizLand.Question.Option;

import com.Dijkstra.QuizLand.Question.Exception.CorrectAnswerNotExistsException;
import com.Dijkstra.QuizLand.Question.Exception.OptionNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Transactional
    public void updateQuestionOption(Set<Option> options, int optionId, String newOptionContent){
        Optional<Option> optionToUpdate = options
                .stream()
                .filter(option -> option.getId() == optionId)
                .findFirst();

        if(optionToUpdate.isPresent())
            optionToUpdate.get().setOptionContent(newOptionContent);
        else
            throw new OptionNotFoundException("Option not found with id: " + optionId);

    }
}
