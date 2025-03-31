package com.mandu.quiz_service.feign;

import com.mandu.quiz_service.entity.QuestionWrapper;
import com.mandu.quiz_service.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
// @FeignClient("question-service") is used to specify the name of the service that we want to communicate with.
//With @FeignClient load balancing is done automatically.
public interface QuizInterface {
    //generate quiz
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                             @RequestParam Integer noOfQuestions);

    //get questions based on question ids

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuestionIds(@RequestBody List<Integer> questionIds)
;
    //calculate result
    @PostMapping("question/getResult")
    public ResponseEntity<Integer> getResult(@RequestBody List<Response> response);


}
