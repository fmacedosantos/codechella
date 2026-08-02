package br.com.alura.codechella;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EventoService {
    private EventoRepository repositorio;

    public EventoService(EventoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Flux<EventoDto> obterTodos() {
        return repositorio.findAll()
                .map(EventoDto::toDto);
    }
}
