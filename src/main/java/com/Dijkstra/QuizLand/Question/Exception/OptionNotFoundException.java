package com.Dijkstra.QuizLand.Question.Exception;

public class OptionNotFoundException extends RuntimeException{
    public OptionNotFoundException(String message){
        super(message);
    }
}