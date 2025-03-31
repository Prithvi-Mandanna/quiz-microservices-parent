package com.mandu.question_service.service;


import com.mandu.question_service.entity.Question;
import com.mandu.question_service.entity.QuestionWrapper;
import com.mandu.question_service.entity.Response;
import com.mandu.question_service.repo.QuestionDb;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDb db;




    public ResponseEntity<List<Question>> getAllQuestions(){

        try {
            return ResponseEntity.status(HttpStatus.OK).body( db.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ArrayList<>());
    };

    public List<Question> getQuestionsByCategory(String category) {
        return db.findByCategory(category);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(db.save(question)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Question());
    };

    public Question updateQuestion(Question question) {
        return db.save(question);
    };

    public void deleteQuestion(int id) {
        db.deleteById(id);
    };

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer noOfQuestions) {
        List<Integer> questionList = db.findRandomQuestionsByCategory(categoryName, noOfQuestions);

        return ResponseEntity.status(HttpStatus.OK).body(questionList);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForIds(List<Integer> questionIds) {
        List<QuestionWrapper> questionsForClient = new ArrayList<>();
        for (Integer questionId: questionIds){
            QuestionWrapper currentQuestion = new QuestionWrapper();
            Optional<Question> question = db.findById(questionId);
            currentQuestion.setId(question.get().getId());
            currentQuestion.setQuestionTitle(question.get().getQuestionTitle());
            currentQuestion.setOption1(question.get().getOption1());
            currentQuestion.setOption2(question.get().getOption2());
            currentQuestion.setOption3(question.get().getOption3());
            currentQuestion.setOption4(question.get().getOption4());
            questionsForClient.add(currentQuestion);
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionsForClient);
    };

    public ResponseEntity<Integer> getResult(List<Response> response) {
        Integer score = 0;
        for (Response currentResponse: response){
            Optional<Question> currentQuestion = db.findById(currentResponse.getId());
            if (currentQuestion.get().getCorrectAnswer().equals(currentResponse.getResponse())){
                score++;
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(score);
    }
}
