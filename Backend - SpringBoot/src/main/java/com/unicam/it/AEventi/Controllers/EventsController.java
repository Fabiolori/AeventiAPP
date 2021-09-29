package com.unicam.it.AEventi.Controllers;

import com.unicam.it.AEventi.Models.Event;
import com.unicam.it.AEventi.Models.EventType;
import com.unicam.it.AEventi.Services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@RestController
public class EventsController {
        @Autowired
        EventsService service;

        public EventsController(EventsService service) {
            this.service = service;
        }

        // SELEZIONARE TUTTI  GLI EVENTI
        // Aggregate root
        // tag::get-aggregate-root[]
        @GetMapping("/events")
        public List<Event> all() {
            return service.getEvent();
        }
        // end::get-aggregate-root[]

        // AGGIUNGERE UN Evento
        @PostMapping("/events")
        public Event newEvent(@RequestBody Event newEvent) {
            return service.createEvent(newEvent);

        }

        // SELEZIONARE UN ACCOUNT
        @GetMapping("/events/{id}")
        public Event one(@PathVariable Long id) {

            return service.getEventbyID(id);
        }



        //AGGIORNARE UN ACCOUNT
        @RequestMapping( value = "/events/{id}", method = RequestMethod.PUT)
        public Event replaceEvent(@RequestBody Event newEvent, @PathVariable Long id) {

            return service.updateEvent(newEvent,id);
        }

        //RIMUOVERE UN ACCOUNT
        @DeleteMapping("/events/{id}")
        public void deleteEvent(@PathVariable Long id) {
            service.deleteEvent(id);
        }
    }


