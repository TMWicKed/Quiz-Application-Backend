package com.harman.quizapp1.service;

import com.harman.quizapp1.dao.QuestionDao;
import com.harman.quizapp1.dao.QuizDao;
import com.harman.quizapp1.model.Question;
import com.harman.quizapp1.model.QuestionWrapperDto;
import com.harman.quizapp1.model.Quiz;
import com.harman.quizapp1.model.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public List<Question> createQuiz(String category, Integer numQ, String title) {

        List<Question> questions=questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return questions;
    }

    public List<QuestionWrapperDto> getQuizById(Integer id) {
        Quiz quiz = quizDao.findById(id).orElseThrow(()->new RuntimeException("Not found!"));
        List<Question> questionFromDB = quiz.getQuestions();

        List<QuestionWrapperDto> questionForUser=new ArrayList<>();
        for (Question q : questionFromDB){
            QuestionWrapperDto qw=new QuestionWrapperDto(
            q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
//        Quiz quiz = quizDao.findById(id).orElseThrow(()->new RuntimeException("Not found!"));
//        List<Question> questions = quiz.getQuestions();
//        return questions.stream()
//                .map(question -> modelMapper.map(question, QuestionWrapperDto.class))
//                .collect(Collectors.toList());
        return questionForUser;
    }

    public Integer calculateResult(Integer id, List<Response> response) {
        Quiz quiz=quizDao.findById(id).orElseThrow(()->new RuntimeException("Not found!"));
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for (Response response1:response){
            if(response1.getResponse().equals(questions.get(i)));
                right++;
            i++;
        }
        return right;
    }
}
