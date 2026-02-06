package com.radkovich.module_2_4.mapper;

import com.radkovich.module_2_4.dto.response.EventResponseDto;
import com.radkovich.module_2_4.model.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    private EventMapper() {}

    public static EventResponseDto toDto(Event event) {
        if (event == null) return null;

        return new EventResponseDto(
                event.getId(),
                event.getUser().getId(),
                event.getFileEntity().getId()
        );
    }

    public static List<EventResponseDto> toDtoList(List<Event> events) {
        return events.stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }
}
