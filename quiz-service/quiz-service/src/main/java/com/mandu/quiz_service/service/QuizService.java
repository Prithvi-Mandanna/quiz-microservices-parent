package com.mandu.quiz_service.service;


import com.mandu.quiz_service.entity.QuestionWrapper;
import com.mandu.quiz_service.entity.Quiz;
import com.mandu.quiz_service.entity.Response;
import com.mandu.quiz_service.feign.QuizInterface;
import com.mandu.quiz_service.repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
//    @Autowired
//    QuestionDb questionDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {

        //List<Integer> questionList = questionDao.findRandomQuestionsByCategory(category, numQ);
        List<Integer> questionList = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questionList);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizDao.save(quiz));
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByQuizId(Integer quizId) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Integer> questionsFromDB = quiz.get().getQuestionList();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();

        try {
            questionsForUsers = quizInterface.getQuestionsForQuestionIds(questionsFromDB).getBody();
            return ResponseEntity.status(HttpStatus.OK).body(questionsForUsers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Integer> calculateResult(List<Response> responses) {

        return ResponseEntity.status(HttpStatus.OK).body(quizInterface.getResult(responses).getBody());
    }
}
