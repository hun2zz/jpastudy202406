package com.spring.jpastudy.event.service;


import com.spring.jpastudy.event.dto.request.EventSaveDto;
import com.spring.jpastudy.event.dto.response.EventDetailDto;
import com.spring.jpastudy.event.dto.response.EventOneDto;
import com.spring.jpastudy.event.entity.Event;
import com.spring.jpastudy.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    //전체 조회 서비스
    public List<EventDetailDto> getEvents(int pageNo, String sort) {
        Pageable pageable = PageRequest.of(pageNo - 1, 4);
        Page<Event> eventPage = eventRepository.findEvents(pageable, sort);
        List<Event> events = eventPage.getContent();
        return events.stream().map(EventDetailDto::new).collect(Collectors.toList());
    }

    //이벤트 등록
    public void
    saveEvent(EventSaveDto dto) {
        Event savedEvent = eventRepository.save(dto.toEntity());
//        return getEvents("date");
    }

    //이벤트 단일 조회
    public EventOneDto getEventDetail(Long id){
        Event foundEvent = eventRepository.findById(id).orElseThrow();

        return new EventOneDto(foundEvent);
    }

    //이벤트 삭제
    public void deleteEvent (Long id) {
        eventRepository.deleteById(id);
    }

    //이벤트 수정
    public void modifyEvent(Long id , EventSaveDto dto) {
        Event fooundEvent = eventRepository.findById(id).orElseThrow();
        fooundEvent.changeEvent(dto);

        eventRepository.save(fooundEvent);


    }

}
