package com.report.Reporting.Service.event;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getALl(){
        return repository.findAll();
    }

    public List<Event> getByServiceId(Long id){
        return repository.findByServiceId(id);
    }

    public List<Event> getByCustomerId(Long id){
        return repository.findByCustomerId(id);
    }
}
