package com.bic.coffee_project.controller;

import com.bic.coffee_project.model.Coffee;
import com.bic.coffee_project.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
public class CoffeeController {

    private CoffeeService coffeeService;

    @GetMapping("/")
    public String getAll(Model model) {
        Iterable<Coffee> taskList = coffeeService.getAll();
        model.addAttribute("coffees", taskList);
        return "coffees";
    }

    @PostMapping("/delete-rows")
    public void deleteRows(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            coffeeService.delete(id);
        }
    }
}
