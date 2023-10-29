package com.Dijkstra.QuizLand.Question.Exception;
/*
    Exception thrown when user gives nonexistent question id.
 */
public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message){
        super(message);
    }
}
