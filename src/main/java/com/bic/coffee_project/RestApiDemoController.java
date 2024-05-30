package com.bic.coffee_project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
    private final CoffeeRepository coffeeRepository;
    public RestApiDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }
    @GetMapping("/all")
    public ModelAndView getCoffees() {
        ModelAndView mav = new ModelAndView("coffees");
        mav.addObject("coffees",coffeeRepository.findAll());
        return mav;
    }
    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable Integer id) {
        return coffeeRepository.findById(String.valueOf(id));
    }
    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable Integer id,
                                     @RequestBody Coffee coffee) {
        return (coffeeRepository.existsById(String.valueOf(id)))
                ? new ResponseEntity<>(coffeeRepository.save(coffee),
                HttpStatus.OK)
                : new ResponseEntity<>(coffeeRepository.save(coffee),
                HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable Integer id) {
        coffeeRepository.deleteById(String.valueOf(id));
    }
}