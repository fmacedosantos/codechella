package br.com.alura.codechella;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    private EventoRepository repositorio;

    public EventoController(EventoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Evento> obterTodos() {
        return repositorio.findAll();
    }
}
