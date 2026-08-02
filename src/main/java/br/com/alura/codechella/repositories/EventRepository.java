package br.com.alura.codechella.repositories;

import br.com.alura.codechella.enums.EventType;
import br.com.alura.codechella.models.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
    Flux<Event> findByType(EventType eventType);
}
