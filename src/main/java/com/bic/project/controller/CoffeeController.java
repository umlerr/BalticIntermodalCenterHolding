package com.bic.project.controller;

import com.bic.project.model.Coffee;
import com.bic.project.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    @ResponseBody
    public void deleteRows(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            coffeeService.delete(id);
        }
    }
    @PostMapping("/add-data")
    @ResponseBody
    public void addData(@RequestBody Coffee coffee) {
        coffeeService.add(coffee);
    }

    @PostMapping("/update-row")
    @ResponseBody
    public void updateData(@RequestBody Coffee coffee) {
        coffeeService.updateOrInsert(coffee);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Iterable<Coffee>> search(@RequestParam String keyword) {
        Iterable<Coffee> searchResults = coffeeService.searchByKeyword(keyword);
        return ResponseEntity.ok().body(searchResults);
    }
}
