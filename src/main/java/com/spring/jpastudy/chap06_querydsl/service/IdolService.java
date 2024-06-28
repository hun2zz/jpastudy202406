package com.spring.jpastudy.chap06_querydsl.service;


import com.spring.jpastudy.chap06_querydsl.entity.Idol;
import com.spring.jpastudy.chap06_querydsl.repository.IdolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional // JPA , QueryDsl 쓸 때 잊지 말
public class IdolService {

    IdolRepository idolRepository;

    public List<Idol> getIdols() {
//        List<Idol> idolList = idolRepository.findAll();
        List<Idol> allBySorted = idolRepository.findAllBySorted();
        return allBySorted;
    }
}
