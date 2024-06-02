package com.bic.project.service;


import com.bic.project.model.Container;
import com.bic.project.repository.ContainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    public Iterable<Container> getAll() {
        return containerRepository.findAll();
    }

    public Container add(Container container) {
        return containerRepository.save(container);
    }

    public void delete(int id) {
        containerRepository.deleteById(String.valueOf(id));
    }

    public Optional<Container> getById(Integer id) {
        return containerRepository.findById(String.valueOf(id));
    }

    public void updateOrInsert(Container container) {
        containerRepository.updateOrInsert(container);
    }

    public Iterable<Container> searchByKeyword(String keyword) {
        return containerRepository.searchByKeyword(keyword);
    }

}
