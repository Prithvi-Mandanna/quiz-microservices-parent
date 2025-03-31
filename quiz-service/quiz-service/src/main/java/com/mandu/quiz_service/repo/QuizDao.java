package com.mandu.quiz_service.repo;


import com.mandu.quiz_service.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository <Quiz, Integer>{
}
