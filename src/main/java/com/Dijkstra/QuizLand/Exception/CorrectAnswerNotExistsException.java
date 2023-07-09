package com.Dijkstra.QuizLand.Exception;
/*
   This exception is used when the user doesn't set any option for the question as true.
 */
public class CorrectAnswerNotExistsException extends RuntimeException{
    public CorrectAnswerNotExistsException(String message){
        super(message);
    }
}
