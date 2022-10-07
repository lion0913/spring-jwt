package com.lion.spring_paymodule.app.article.controller;

import com.lion.spring_paymodule.app.article.entity.Article;
import com.lion.spring_paymodule.app.article.service.ArticleService;
import com.lion.spring_paymodule.app.base.dto.ResultData;
import com.lion.spring_paymodule.app.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<ResultData> getArticleList() {
        List<Article> articleList = articleService.findAll();

        return Util.spring.responseEntityOf(
                ResultData.successOf(
                        Util.mapOf(
                                "articles", articleList
                        )
                )
        );
    }


}
