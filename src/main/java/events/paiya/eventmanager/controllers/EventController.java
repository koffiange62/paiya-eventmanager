package events.paiya.eventmanager.controllers;

import events.paiya.eventmanager.domains.Event;
import events.paiya.eventmanager.mappers.EventMapper;
import events.paiya.eventmanager.resources.EventResource;
import events.paiya.eventmanager.services.EventServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/events")
@Slf4j
public class EventController {
    private final EventServiceImpl eventService;
    private final EventMapper eventMapper;

    public EventController(EventServiceImpl eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    public ResponseEntity<EventResource> create(@RequestBody @Valid EventResource resource){
        Event event = eventMapper.toEntity(resource);
        event = eventService.create(event);
        return ResponseEntity.ok(eventMapper.toResource(event));
    }

    @GetMapping("owned-by")
    public ResponseEntity<List<EventResource>> findEventsByOwner(@RequestParam(name = "ownerId") String ownerId){
        List<Event> events = eventService.findEventsByOwner(ownerId);
        return ResponseEntity.ok(eventMapper.toResourceList(events));
    }

    @GetMapping("date-between")
    public ResponseEntity<List<EventResource>> findEventsByStartingDateBetweenAndVisibilityIsTrue(@RequestParam(name = "minDate") String minDate, @RequestParam(name = "maxDate") String maxDate){
        LocalDate minD = LocalDate.parse(minDate);
        LocalDate maxD = LocalDate.parse(maxDate);
        List<Event> events = eventService.findEventsByStartingDateBetweenAndVisibilityIsTrue(minD, maxD);
        return ResponseEntity.ok(eventMapper.toResourceList(events));
    }

    @GetMapping("title-like")
    public ResponseEntity<List<EventResource>> findEventsByTitleLikeIgnoreCaseAndVisibilityIsTrue(@RequestParam(name = "title") String title){
        List<Event> events = eventService.findEventsByTitleLikeIgnoreCaseAndVisibilityIsTrue(title);
        return ResponseEntity.ok(eventMapper.toResourceList(events));
    }

    @GetMapping("town-like")
    public ResponseEntity<List<EventResource>> findEventsByTown(@RequestParam(name = "town") String town){
        List<Event> events = eventService.findEventsByTown(town);
        return ResponseEntity.ok(eventMapper.toResourceList(events));
    }

    @GetMapping("paginated")
    public ResponseEntity<List<EventResource>> findByVisibilityIsTrue(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventPage = eventService.findByVisibilityIsTrue(pageable);
        List<EventResource> eventResourceList = eventMapper.toResourceList(eventPage.stream().toList());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Total-Elements", String.valueOf(eventPage.getTotalElements()));
        httpHeaders.add("Total-Pages", String.valueOf(eventPage.getTotalPages()));
        return ResponseEntity.ok().headers(httpHeaders).body(eventResourceList);
    }

    @GetMapping
    public ResponseEntity<List<EventResource>> findAllByVisibilityIsTrue(){
        return ResponseEntity.ok(eventMapper.toResourceList(eventService.findAllByVisibilityIsTrue()));
    }

    @PutMapping("{id}")
    public ResponseEntity<EventResource> update(@PathVariable String id, @RequestBody EventResource eventResource){
        Event event = eventMapper.toEntity(eventResource);
        event = eventService.update(id, event);
        return ResponseEntity.ok(eventMapper.toResource(event));
    }

    @PutMapping("publish/{id}")
    public ResponseEntity<EventResource> publish(@PathVariable(name = "id") String eventId){
        Event event = eventService.publish(eventId);
        return ResponseEntity.ok(eventMapper.toResource(event));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        this.eventService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
