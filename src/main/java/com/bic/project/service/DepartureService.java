package com.bic.project.service;


import com.bic.project.model.Departure;
import com.bic.project.repository.DepartureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class DepartureService {

    private final DepartureRepository departureRepository;

    public Iterable<Departure> getAll() {
        return departureRepository.findAll();
    }

    public Departure add(Departure departure) {
        return departureRepository.save(departure);
    }

    public void delete(int id) {
        departureRepository.deleteById(String.valueOf(id));
    }

    public void updateOrInsert(Departure departure) {
        departureRepository.updateOrInsert(departure);
    }

    public Iterable<Departure> searchByKeyword(String keyword) {
        return departureRepository.searchByKeyword(keyword);
    }

}
