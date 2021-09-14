package com.unicam.it.AEventi.Services;

import com.unicam.it.AEventi.Models.EventType;
import com.unicam.it.AEventi.Repo.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeService {


    @Autowired
    EventTypeRepository repository;

    public EventType createEventType(EventType eventType) {

        return repository.save(eventType);
    }

    public EventType updateEventType(EventType newEventType, long id) {
        return repository.findById(id)
                .map(eventType -> {
                    eventType.setName(newEventType.getName());
                    return repository.save(eventType);
                })
                .orElseGet(() -> {
                    newEventType.setId(id);
                    return repository.save(newEventType);
                });

    }



    public void deleteEventType(long id) {
        repository.deleteById(id);

    }

    public List<EventType> getEventType() {
        return repository.findAll();
    }

    public EventType getEventTypebyID(long ID) {
        return repository.findById(ID)
                .orElseThrow(NullPointerException::new);
    }






}
