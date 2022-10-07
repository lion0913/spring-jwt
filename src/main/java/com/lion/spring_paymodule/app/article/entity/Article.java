package com.lion.spring_paymodule.app.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lion.spring_paymodule.app.base.BaseEntity;
import com.lion.spring_paymodule.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne
    private Member author;

    private String subject;

    private String content;

//    public Article(long id) {
//        super(id);
//    }
}
