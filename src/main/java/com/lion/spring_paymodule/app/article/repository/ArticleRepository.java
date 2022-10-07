package com.lion.spring_paymodule.app.article.repository;

import com.lion.spring_paymodule.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByIdDesc();
}
