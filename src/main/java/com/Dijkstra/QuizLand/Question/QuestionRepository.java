package com.Dijkstra.QuizLand.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface QuestionRepository extends JpaRepository<Question,Integer>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE questions SET question_content = ?1 WHERE id = ?2",
            nativeQuery = true)
    void updateQuestionContent(String newQuestionContent, Integer questionId);
}
