package com.springio.springtest;

import com.springio.springtest.repository.*;
import com.springio.springtest.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
