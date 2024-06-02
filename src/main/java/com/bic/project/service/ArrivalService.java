package com.bic.project.service;


import com.bic.project.model.Arrival;
import com.bic.project.repository.ArrivalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ArrivalService {

    private final ArrivalRepository arrivalRepository;

    public Iterable<Arrival> getAll() {
        return arrivalRepository.findAll();
    }

    public Arrival add(Arrival arrival) {
        return arrivalRepository.save(arrival);
    }

    public void delete(int id) {
        arrivalRepository.deleteById(String.valueOf(id));
    }

    public void updateOrInsert(Arrival arrival) {
        arrivalRepository.updateOrInsert(arrival);
    }

    public Iterable<Arrival> searchByKeyword(String keyword) {
        return arrivalRepository.searchByKeyword(keyword);
    }

}
