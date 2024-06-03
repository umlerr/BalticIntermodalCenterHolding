package com.bic.project.service;


import com.bic.project.model.Circle;
import com.bic.project.model.Container;
import com.bic.project.repository.CircleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class CircleService {

    private final CircleRepository circleRepository;

    public Iterable<Circle> getAll() {
        return circleRepository.findAll();
    }

    public Circle add(Circle circle) {
        return circleRepository.save(circle);
    }

    public void delete(int id) {
        circleRepository.deleteById(String.valueOf(id));
    }

    public void updateOrInsert(Circle circle) {
        circleRepository.updateOrInsert(circle);
    }

//    public Iterable<Circle> searchByKeyword(String keyword) {
//        return circleRepository.searchByKeyword(keyword);
//    }
    public Optional<Circle> getById(Integer id) {
        return circleRepository.findById(String.valueOf(id));
    }

}
