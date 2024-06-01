package com.bic.coffee_project.service;


import com.bic.coffee_project.model.Coffee;
import com.bic.coffee_project.repository.CoffeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public Iterable<Coffee> getAll() {
        return coffeeRepository.findAll();
    }

    public Coffee add(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public void delete(int id) {
        coffeeRepository.deleteById(String.valueOf(id));
    }

    public void updateOrInsert(Coffee coffee) {
        coffeeRepository.updateOrInsert(coffee);
    }

    public Iterable<Coffee> searchByKeyword(String keyword) {
        return coffeeRepository.searchByKeyword(keyword);
    }

}
