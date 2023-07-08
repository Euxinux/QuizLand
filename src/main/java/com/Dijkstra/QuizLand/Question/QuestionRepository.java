package com.Dijkstra.QuizLand.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QuestionRepository extends JpaRepository<Question,Integer>{


}
