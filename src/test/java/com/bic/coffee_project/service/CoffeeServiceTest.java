package com.bic.coffee_project.service;

import com.bic.project.model.Coffee;
import com.bic.project.repository.CoffeeRepository;
import com.bic.project.service.CoffeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class CoffeeServiceTest {

    @Autowired
    private CoffeeService coffeeService;

    @MockBean
    private CoffeeRepository coffeeRepository;

    @Test
    public void testAddCoffee() {
        Coffee coffee = new Coffee();
        coffee.setName("12345");

        coffeeService.add(coffee);

        verify(coffeeRepository).save(coffee);
    }

}