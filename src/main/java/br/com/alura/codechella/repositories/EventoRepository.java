package br.com.alura.codechella.repositories;

import br.com.alura.codechella.enums.TipoEvento;
import br.com.alura.codechella.models.Evento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventoRepository extends ReactiveCrudRepository<Evento, Long> {
    Flux<Evento> findByTipo(TipoEvento tipoEvento);
}
