package com.ksb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    //createdBy 정보를 주기위한 설정
    @Bean
    public AuditorAware<String> auditorAware() {
        //TODO: 스프링 시큐리티로 인증 기능을 넣어 동적으로 변경 해야 함, 사큐리티 기능 넣을때 수정 예정
        return () -> Optional.of("natty");


    }
}
