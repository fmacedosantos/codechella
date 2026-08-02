package br.com.alura.codechella.dtos;

import br.com.alura.codechella.enums.EventType;
import br.com.alura.codechella.models.Event;

import java.time.LocalDate;

public record EventDTO(
        Long id,
        EventType type,
        String name,
        String description,
        LocalDate date
) {

    public static EventDTO toDto(Event event) {
        return new EventDTO(event.getId(), event.getType(), event.getName(),
                event.getDescription(), event.getDate());
    }

    public Event toEntity() {
        Event event = new Event();

        event.setId(this.id);
        event.setName(this.name);
        event.setType(this.type);
        event.setDescription(this.description);
        event.setDate(this.date);

        return event;
    }
}
