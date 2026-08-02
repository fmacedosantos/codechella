package br.com.alura.codechella.controllers;

import br.com.alura.codechella.dtos.EventDTO;
import br.com.alura.codechella.services.EventService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/v1/events")
public class EventController {
    private final EventService service;
    private final Sinks.Many<EventDTO> eventSink;

    public EventController(EventService service) {
        this.service = service;
        this.eventSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(params = "type", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDTO> getByType(@RequestParam("type") String type) {
        return Flux.merge(service.getByType(type), eventSink.asFlux());
    }

    @GetMapping("/{id}")
    public Mono<EventDTO> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Mono<EventDTO> register(@RequestBody EventDTO dto) {
        return service.register(dto)
                .doOnSuccess(e -> eventSink.tryEmitNext(e));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
        return service.update(id, dto);
    }
}
