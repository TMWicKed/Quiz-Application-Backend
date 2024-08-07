package com.harman.quizapp1.service;

import com.harman.quizapp1.dao.QuestionDao;
import com.harman.quizapp1.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class QuestionService
{
    @Autowired
    QuestionDao dao;


    public List<Question> getAllQuestions() {
        return dao.findAll();
    }

    public Question addQuestion(Question question) {

        return dao.save(question);
    }

    public List<Question> getQuestionByCategory(String category)
    {
        return dao.findByCategory(category);
    }

    public Question updateQuestion(Question newQuestion, Integer id)
    {
        Question oldQuestion=dao.findById(id).orElseThrow(()->new RuntimeException("Question not found!"));
        oldQuestion.setId(id);
        if(newQuestion.getQuestionTitle()!=null)
            oldQuestion.setQuestionTitle(newQuestion.getQuestionTitle());

        if(newQuestion.getOption1()!=null)
            oldQuestion.setOption1(newQuestion.getOption1());

        if(newQuestion.getOption2()!=null)
            oldQuestion.setOption2(newQuestion.getOption2());

        if(newQuestion.getOption3()!=null)
            oldQuestion.setOption3(newQuestion.getOption3());

        if(newQuestion.getOption4()!=null)
            oldQuestion.setOption4(newQuestion.getOption4());

        if(newQuestion.getRightAnswer()!=null)
            oldQuestion.setRightAnswer(newQuestion.getRightAnswer());

        if(newQuestion.getDifficultyLevel()!=null)
            oldQuestion.setDifficultyLevel(newQuestion.getDifficultyLevel());

        if(newQuestion.getCategory()!=null)
            oldQuestion.setCategory(newQuestion.getCategory());

        return dao.save(oldQuestion);
    }

    public String deleteById(Integer id)
    {
        Question oldQuestion=dao.findById(id).orElseThrow(()->new RuntimeException("Question not found!"));
        dao.deleteById(oldQuestion.getId());
        return "Question deleted successfully";
    }

    public String saveAllQuestions(List<Question> questions) {
        dao.saveAll(questions);
        return "Questions ssaved successfully!";
    }

    public Question getQuestionById(Integer id) {
        return dao.findById(id).orElseThrow(()->new RuntimeException("Not found"));
    }
}
