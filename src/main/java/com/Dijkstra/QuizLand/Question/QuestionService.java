package com.Dijkstra.QuizLand.Question;

import com.Dijkstra.QuizLand.Question.Exception.QuestionNotFoundException;
import com.Dijkstra.QuizLand.Question.Option.Option;
import com.Dijkstra.QuizLand.Question.Option.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService{
    private final QuestionRepository repository;
    private final OptionService optionService;

    Question createQuestion(QuestionCreateDTO questionSource){
        optionService.hasOneCorrectAnswer(questionSource.getOptions());
        Question questionToSave = new Question(questionSource);
        Set<Option> questionOptions = questionToSave.getOptions();
        questionSource
                .getOptions()
                .forEach(option ->
                        questionOptions.add(new Option(option, questionToSave)));
        return repository.save(questionToSave);
    }

    List<QuestionReadDTO> readAllQuestion(){
        List<Question> allQuestion = repository.findAll();
        return allQuestion
                .stream()
                .map(QuestionReadDTO::new)
                .toList();
    }

    QuestionReadDTO readQuestion(int questionId){
        Optional<Question> questionById = repository.findById(questionId);
        return new QuestionReadDTO(questionById.orElseThrow(() ->
                new QuestionNotFoundException("Question not found with id: " + questionId)));
    }

    void deleteQuestion(int questionId){
        isQuestionExists(questionId);
        repository.deleteById(questionId);
    }

    void toggleActive(int questionId){
        Question questionToToggle = isQuestionExists(questionId);
        questionToToggle.setActive(!questionToToggle.isActive());
        repository.save(questionToToggle);
    }

    void updateQuestionContent(String newQuestionContent, int questionId){
        isQuestionExists(questionId);
        repository.findById(questionId);
        System.out.println(newQuestionContent);
        repository.updateQuestionContent(newQuestionContent,questionId);
    }


    public void changeCorrectAnswer(int questionId, int optionId){

    }

    private Question isQuestionExists(int questionId){
         return repository.findById(questionId)
                .orElseThrow(()->new QuestionNotFoundException("Question not found with id: " + questionId));
    }

}
