package com.ksb.repository;

import com.ksb.domain.Article;
import com.ksb.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
   default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true); //true = Aticle 의 필드에대헤서 선택적으로 검색 가능
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy); //검색 필터로 원하는 필드 선택
        bindings.bind(root.title).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.content).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.hashtag).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.createdAt).first((DateTimeExpression::eq));
        bindings.bind(root.createdBy).first((StringExpression::eq));
    }
}
