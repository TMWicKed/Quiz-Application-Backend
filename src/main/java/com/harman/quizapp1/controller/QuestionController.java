package com.harman.quizapp1.controller;

import com.harman.quizapp1.model.Question;
import com.harman.quizapp1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController
{
    @Autowired
    QuestionService service;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(service.getAllQuestions(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(service.getQuestionById(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question)
    {
        try {
            return new ResponseEntity<>(service.addQuestion(question), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category)
    {
        try {
            return new ResponseEntity<>(service.getQuestionByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.updateQuestion(question, id), HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id)
    {
        try {
            return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addAll")
    public ResponseEntity<String> saveAllQuestion(@RequestBody List<Question> questions)
    {
        String result=service.saveAllQuestions(questions);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
