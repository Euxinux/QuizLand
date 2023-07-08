package com.Dijkstra.QuizLand.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService{
    private final QuestionRepository repository;

    Question createQuestion(QuestionDTO source){
        Question question = new Question(source);
        Set<Option> questionOptions = question.getOptions();
        for (Option option : source.getOptions()) {
            questionOptions.add(new Option(option, question));
        }
        return repository.save(question);
    }

    public List<Question> readAllQuestion(){
        return repository.findAll();
    }

}
