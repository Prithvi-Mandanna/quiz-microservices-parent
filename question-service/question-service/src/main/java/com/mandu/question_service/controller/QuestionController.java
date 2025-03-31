package com.mandu.question_service.controller;


import com.mandu.question_service.entity.Question;
import com.mandu.question_service.entity.QuestionWrapper;
import com.mandu.question_service.entity.Response;
import com.mandu.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {



    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    };


    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
            return questionService.getQuestionsByCategory(category);
    };

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    };

    //update question
    @PutMapping("update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        Question updatedQuestion = questionService.updateQuestion(question);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuestion);
    };

    //delete question
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).body("Question deleted successfully");
    };

    //generate quiz
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
                                                             @RequestParam Integer noOfQuestions){
        return questionService.getQuestionsForQuiz(categoryName, noOfQuestions);
    };

    //get questions based on question ids

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuestionIds(@RequestBody List<Integer> questionIds){

        return questionService.getQuestionsForIds(questionIds);
    };

    //test comment
    //calculate result
    @PostMapping("getResult")
    public ResponseEntity<Integer> getResult(@RequestBody List<Response> response){
        return questionService.getResult(response);

    }





}
