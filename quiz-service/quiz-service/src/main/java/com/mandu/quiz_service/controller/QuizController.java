package com.mandu.quiz_service.controller;

import com.mandu.quiz_service.entity.QuestionWrapper;
import com.mandu.quiz_service.entity.Quiz;
import com.mandu.quiz_service.entity.QuizDto;
import com.mandu.quiz_service.entity.Response;
import com.mandu.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto){
      return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumOfQuestions(), quizDto.getTitle());
    };

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsbyQuizId(@PathVariable Integer quizId){
        return quizService.getQuizQuestionsByQuizId(quizId);
    };

    @PostMapping("submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> responses) {
        return quizService.calculateResult(responses);
    }

}
