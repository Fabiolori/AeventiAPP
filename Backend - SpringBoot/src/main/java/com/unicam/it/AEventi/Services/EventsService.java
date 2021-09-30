package com.unicam.it.AEventi.Services;

import com.unicam.it.AEventi.Models.Event;
import com.unicam.it.AEventi.Repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsService {


        @Autowired
        EventRepository repository;

        public Event createEvent(Event event) {
            return repository.save(event);
        }

        public Event updateEvent(Event newEvent, long id) {
            return repository.findById(id)
                    .map(event -> {
                        event.setName(newEvent.getName());
                        event.setDescription(newEvent.getDescription());
                        event.setType(newEvent.getType());
                        event.setTime(newEvent.getTime());
                        return repository.save(event);
                    })
                    .orElseGet(() -> {
                        newEvent.setID(id);
                        return repository.save(newEvent);
                    });

        }



        public void deleteEvent(long id) {
            repository.deleteById(id);

        }

        public List<Event> getEvent() {
            return repository.findAll();
        }

        public Event getEventbyID(long ID) {
            return repository.findById(ID)
                    .orElseThrow(NullPointerException::new);
        }






}
