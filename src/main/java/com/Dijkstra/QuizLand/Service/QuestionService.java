package com.Dijkstra.QuizLand.Service;

import com.Dijkstra.QuizLand.Component.QuestionModelMapper;
import com.Dijkstra.QuizLand.DTO.QuestionCreateDTO;
import com.Dijkstra.QuizLand.DTO.QuestionReadDTO;
import com.Dijkstra.QuizLand.DTO.QuestionUpdateDTO;
import com.Dijkstra.QuizLand.Exception.QuestionNotFoundException;
import com.Dijkstra.QuizLand.Model.Option;
import com.Dijkstra.QuizLand.Model.Question;
import com.Dijkstra.QuizLand.Repository.QuestionRepository;
import jakarta.transaction.Transactional;
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
    private final QuestionModelMapper modelMapper;

    public Question createQuestion(QuestionCreateDTO questionSource){
        optionService.hasOneCorrectAnswer(questionSource.getOptions());
        Question questionToSave = new Question(questionSource);
        Set<Option> questionOptions = questionToSave.getOptions();
        questionSource
                .getOptions()
                .forEach(option ->
                        questionOptions.add(new Option(option, questionToSave)));
        return repository.save(questionToSave);
    }

    public List<QuestionReadDTO> readAllQuestion(){
        List<Question> allQuestion = repository.findAll();
        return allQuestion
                .stream()
                .map(QuestionReadDTO::new)
                .toList();
    }

    public QuestionReadDTO readQuestion(int questionId){
        Optional<Question> questionById = repository.findById(questionId);
        return new QuestionReadDTO(questionById.orElseThrow(() ->
                new QuestionNotFoundException("Question not found with id: " + questionId)));
    }

    public void deleteQuestion(int questionId){
        isQuestionExists(questionId);
        repository.deleteById(questionId);
    }

    public void toggleActive(int questionId){
        Question questionToToggle = isQuestionExists(questionId);
        questionToToggle.setActive(!questionToToggle.isActive());
        repository.save(questionToToggle);
    }

    @Transactional
    public void updateQuestion(int questionId, QuestionUpdateDTO questionToUpdate){
        Question questionFromDb = isQuestionExists(questionId);
        modelMapper.questionUpdateDTOToQuestion(questionFromDb, questionToUpdate);
    }

     public List<String> getQuestionOptions(int questionId){
        Question questionFromDb = isQuestionExists(questionId);
        return optionService.getAllOptions(questionFromDb.getOptions());
    }

    @Transactional
    public void updateQuestionOption(int questionId, int optionId, String newOptionContent){
        Question questionFromDb = isQuestionExists(questionId);
        optionService.updateQuestionOption(questionFromDb.getOptions(), optionId, newOptionContent);
    }

    public void changeCorrectAnswer(int questionId, int optionId){
        Question questionFromDb = isQuestionExists(questionId);
        optionService.changeCorrectAnswer(questionFromDb.getOptions(), optionId);
    }

    public String getCorrectAnswer(int questionId){
        Question questionFromDb = isQuestionExists(questionId);
        return questionFromDb
                .getOptions()
                .stream()
                .filter(Option::isCorrect)
                .findFirst()
                .get()
                .getOptionContent();
    }

    private Question isQuestionExists(int questionId){
         return repository.findById(questionId)
                .orElseThrow(()->new QuestionNotFoundException("Question not found with id: " + questionId));
    }
}
