package com.mandu.question_service.repo;


import com.mandu.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDb extends JpaRepository<Question, Integer> {


    List<Question> findByCategory(String category);


    @Query(value = "select q.id from question q where q.Category = :category order by RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
