package com.mandu.quiz_service.entity;

public class QuizDto {
    private String category;
    private Integer numOfQuestions;
    private  String title;

    public QuizDto(String category, String title, Integer numOfQuestions) {
        this.category = category;
        this.title = title;
        this.numOfQuestions = numOfQuestions;
    }

    public QuizDto() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(Integer numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
