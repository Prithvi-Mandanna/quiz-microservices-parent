package com.mandu.quiz_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizId;
    private String title;
    // @ElementCollection is used to store a list of values in a single column
    @ElementCollection
    private List<Integer> questionList;

    public Quiz(Integer quizId, String title, List<Integer> questionList) {
        this.quizId = quizId;
        this.title = title;
        this.questionList = questionList;
    }

    public Quiz() {
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Integer> questionList) {
        this.questionList = questionList;
    }
}
