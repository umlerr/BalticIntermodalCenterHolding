package com.bic.coffee_project.controller;

import com.bic.coffee_project.model.Coffee;
import com.bic.coffee_project.service.CoffeeService;
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

//    @GetMapping("/search")
//    public String search(@RequestParam String keyword, Model model) {
//        Iterable<Coffee> searchResults = coffeeService.searchByKeyword(keyword);
//        model.addAttribute("searchResults", searchResults);
//        return "search-results";
//    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Coffee>> search(@RequestParam String keyword) {
        Iterable<Coffee> searchResults = coffeeService.searchByKeyword(keyword);
        return ResponseEntity.ok().body(searchResults);
    }
}
