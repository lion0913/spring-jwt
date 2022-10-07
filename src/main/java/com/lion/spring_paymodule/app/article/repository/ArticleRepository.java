package com.lion.spring_paymodule.app.article.repository;

import com.lion.spring_paymodule.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
