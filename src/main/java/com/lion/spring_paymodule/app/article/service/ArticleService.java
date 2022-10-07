package com.lion.spring_paymodule.app.article.service;

import com.lion.spring_paymodule.app.article.entity.Article;
import com.lion.spring_paymodule.app.article.repository.ArticleRepository;
import com.lion.spring_paymodule.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article write(Member author, String subject, String content) {
        Article article = Article.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        return article;
    }

    public List<Article> findAll() {
        return articleRepository.findAllByOrderByIdDesc();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
}
