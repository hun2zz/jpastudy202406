package com.spring.jpastudy.event.controller;

import com.spring.jpastudy.event.dto.request.EventSaveDto;
import com.spring.jpastudy.event.dto.response.EventDetailDto;
import com.spring.jpastudy.event.dto.response.EventOneDto;
import com.spring.jpastudy.event.entity.Event;
import com.spring.jpastudy.event.repository.EventRepository;
import com.spring.jpastudy.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    // 전체 조회 요청
    @GetMapping("/page/{pageNo}")
    public ResponseEntity<?> getList(@RequestParam(required = false) String sort
                                     ,@PathVariable int pageNo) {
        if (sort ==null){
            return ResponseEntity.badRequest().body("sort 파라미터가 없습니다.");
        }
        List<EventDetailDto> events = eventService.getEvents(pageNo, sort);
        return ResponseEntity.ok().body(events);
    }

    // 등록 요청
    @PostMapping
    public ResponseEntity<?> register(@RequestBody EventSaveDto dto) {
        eventService.saveEvent(dto);
        return ResponseEntity.ok().body("event Saved!");
    }

    // 단일 조회 요청
    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable Long eventId) {

        if (eventId == null || eventId < 1) {
            String errorMessage = "eventId가 정확하지 않습니다.";
            log.warn(errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        EventOneDto eventDetail = eventService.getEventDetail(eventId);

        return ResponseEntity.ok().body(eventDetail);
    }



    //이벤트 삭제
    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> delete(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);

        return ResponseEntity.ok().body("event deleted!");
    }


    @PatchMapping("{eventId}")
    public ResponseEntity<?> modify(@RequestBody EventSaveDto dto, @PathVariable Long eventId) {
        eventService.modifyEvent(eventId, dto);
        return ResponseEntity.ok().body("modify event success!");
    }
}
