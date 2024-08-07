package com.harman.quizapp1.dao;

import com.harman.quizapp1.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>
{
    List<Question> findByCategory(String category);

    //This is a native query as it is written as text in the database's query language (usually SQL)
    //@Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY q.id ASC LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer numQ);
}
