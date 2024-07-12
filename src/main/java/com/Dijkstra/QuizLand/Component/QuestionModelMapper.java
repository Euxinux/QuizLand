package com.Dijkstra.QuizLand.Component;

import com.Dijkstra.QuizLand.DTO.QuestionUpdateDTO;
import com.Dijkstra.QuizLand.Model.Question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionModelMapper{

    @Mapping(source = "questionContent", target = "questionContent")
    Question questionUpdateDTOToQuestion(@MappingTarget Question questionToUpdate, QuestionUpdateDTO newDate);
}
