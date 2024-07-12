package com.Dijkstra.QuizLand.Service;

import com.Dijkstra.QuizLand.Exception.CorrectAnswerNotExistsException;
import com.Dijkstra.QuizLand.Exception.OptionNotFoundException;
import com.Dijkstra.QuizLand.Model.Option;
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
        Option optionToUpdate = getOption(options, optionId);
        optionToUpdate.setOptionContent(newOptionContent);
    }

    @Transactional
    public void changeCorrectAnswer(Set<Option> options, int optionId){
        Option optionToUpdate = getOption(options, optionId);
        optionToUpdate.setCorrect(true);

        for(Option option : options){
            if(option.getId() != optionId)
                option.setCorrect(true);
        }
    }

    private Option getOption(Set<Option> options, int optionId){
        Optional<Option> optionToUpdate = options
                .stream()
                .filter(option -> option.getId() == optionId)
                .findFirst();
        if(optionToUpdate.isEmpty())
            throw new OptionNotFoundException("Option not found with id: " + optionId);
        else
            return optionToUpdate.get();
    }
}
