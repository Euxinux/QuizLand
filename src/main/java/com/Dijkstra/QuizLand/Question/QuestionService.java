package com.Dijkstra.QuizLand.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService{
    private final QuestionRepository repository;
    private final OptionService optionService;

    Question createQuestion(QuestionDTO questionSource){
        optionService.hasAtLeastOneCorrectAnswer(questionSource.getOptions());
        Question questionToSave = new Question(questionSource);
        Set<Option> questionOptions = questionToSave.getOptions();
        questionSource
                .getOptions()
                .forEach(option -> questionOptions.add(new Option(option, questionToSave)));

        return repository.save(questionToSave);
    }

    public List<QuestionReadDTO> readAllQuestion(){
        List<Question> allQuestion = repository.findAll();
        return allQuestion.stream().map(QuestionReadDTO::new).toList();
    }

}
