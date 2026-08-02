package br.com.alura.codechella.services;

import br.com.alura.codechella.dtos.EventDTO;
import br.com.alura.codechella.repositories.EventRepository;
import br.com.alura.codechella.enums.EventType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Flux<EventDTO> getAll() {
        return repository.findAll()
                .map(EventDTO::toDto);
    }

    public Flux<EventDTO> getByType(String type) {
        EventType eventType = EventType.valueOf(type.toUpperCase());

        return repository.findByType(eventType)
                .map(EventDTO::toDto);
    }

    public Mono<EventDTO> getById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(EventDTO::toDto);
    }

    public Mono<EventDTO> register(EventDTO dto) {
        return repository.save(dto.toEntity())
                .map(EventDTO::toDto);
    }

    public Mono<Void> delete(Long id) {
        return repository.findById(id)
                .flatMap(repository::delete);
    }

    public Mono<EventDTO> update(Long id, EventDTO dto) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(eventoExistente -> {
                    eventoExistente.setType(dto.type());
                    eventoExistente.setName(dto.name());
                    eventoExistente.setDescription(dto.description());
                    eventoExistente.setDate(dto.date());

                    return repository.save(eventoExistente);
                })
                .map(EventDTO::toDto);
    }
}
