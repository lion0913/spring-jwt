package com.lion.spring_paymodule.app.article.controller;

import com.lion.spring_paymodule.app.article.dto.ArticleModifyDto;
import com.lion.spring_paymodule.app.article.entity.Article;
import com.lion.spring_paymodule.app.article.service.ArticleService;
import com.lion.spring_paymodule.app.base.dto.ResultData;
import com.lion.spring_paymodule.app.security.MemberContext;
import com.lion.spring_paymodule.app.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<ResultData> getDetail(@PathVariable Long id) {
        Article article = articleService.findById(id);

        if(article == null) {
            return Util.spring.responseEntityOf(
                    ResultData.failOf("해당 게시물은 존재하지 않습니다.")
            );
        }

        return Util.spring.responseEntityOf(
                ResultData.successOf(
                        Util.mapOf("article", article)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultData> delete(@PathVariable Long id, @AuthenticationPrincipal MemberContext memberContext) {
        Article article = articleService.findById(id);

        if(article == null) {
            return Util.spring.responseEntityOf(
                    ResultData.failOf("해당 게시물은 존재하지 않습니다.")
            );
        }

        if(articleService.canBeDeleted(memberContext, article)) {
            return Util.spring.responseEntityOf(
                    ResultData.of("F-2", "삭제 권한이 없습니다.")
            );
        }

        articleService.delete(article);

        return Util.spring.responseEntityOf(
                ResultData.successOf("게시물이 삭제되었습니다.")
        );
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResultData> modify(@PathVariable Long id, @AuthenticationPrincipal MemberContext memberContext, @Valid @RequestBody ArticleModifyDto articleModifyDto) {
        Article article = articleService.findById(id);

        if (article == null) {
            return Util.spring.responseEntityOf(
                    ResultData.of(
                            "F-1",
                            "해당 게시물은 존재하지 않습니다."
                    )
            );
        }

        if (articleService.canModify(memberContext, article) == false) {
            return Util.spring.responseEntityOf(
                    ResultData.of(
                            "F-2",
                            "수정 권한이 없습니다."
                    )
            );
        }

        articleService.modify(article, articleModifyDto);

        return Util.spring.responseEntityOf(
                ResultData.of(
                        "S-1",
                        "해당 게시물이 수정되었습니다."
                )
        );
    }

}
