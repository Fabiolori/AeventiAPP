package com.unicam.it.AEventi.Controllers;

import com.unicam.it.AEventi.Models.EventType;
import com.unicam.it.AEventi.Services.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
public class EventTypeController {
    @Autowired
    EventTypeService service;

    public EventTypeController(EventTypeService service) {
        this.service = service;
    }

    // SELEZIONARE TUTTI  GLI Eventi
    @GetMapping("/eventtypes")
    public List<EventType> all() {
        return service.getEventType();
    }


    // AGGIUNGERE UN Evento
    @PostMapping("/eventtypes")
    public EventType newEventType(@RequestBody EventType newEventType) {
        return service.createEventType(newEventType);

    }

    // SELEZIONARE UN Evento
    @GetMapping("/eventtypes/{id}")
    public EventType one(@PathVariable Long id) {

        return service.getEventTypebyID(id);
    }



    //AGGIORNARE UN Evento
    @RequestMapping( value = "/eventtypes/{id}", method = RequestMethod.PUT)
    public EventType replaceEvent(@RequestBody EventType newEventType, @PathVariable Long id) {

        return service.updateEventType(newEventType,id);
    }

    //RIMUOVERE UN Evento
    @DeleteMapping("/eventtypes/{id}")
    public void deleteEventType(@PathVariable Long id) {
        service.deleteEventType(id);
    }
}


