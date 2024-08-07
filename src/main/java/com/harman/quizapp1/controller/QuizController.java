package com.harman.quizapp1.controller;

import com.harman.quizapp1.model.Question;
import com.harman.quizapp1.model.QuestionWrapperDto;
import com.harman.quizapp1.model.Response;
import com.harman.quizapp1.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<List<Question>> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title){
        try {
            return new ResponseEntity<>(quizService.createQuiz(category, numQ, title), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapperDto>> getQuizById(@PathVariable Integer id){
        return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response)
    {
        return new ResponseEntity<>(quizService.calculateResult(id, response), HttpStatus.OK);
    }
}
