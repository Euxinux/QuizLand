package com.Dijkstra.QuizLand.Question.Exception;

/*
   This exception is used when application can't find particular option by id in the database.
 */
public class OptionNotFoundException extends RuntimeException{
    public OptionNotFoundException(String message){
        super(message);
    }
}
