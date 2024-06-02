package com.bic.project.controller;

import com.bic.project.model.Departure;
import com.bic.project.service.DepartureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
public class DepartureController {

    private DepartureService departureService;

    @GetMapping("/")
    public String getAll(Model model) {
        Iterable<Departure> taskList = departureService.getAll();
        model.addAttribute("departures", taskList);
        return "departures";
    }

    @PostMapping("/delete-rows")
    @ResponseBody
    public void deleteRows(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            departureService.delete(id);
        }
    }
    @PostMapping("/add-data")
    @ResponseBody
    public void addData(@RequestBody Departure departure) {
        departureService.add(departure);
    }

    @PostMapping("/update-row")
    @ResponseBody
    public void updateData(@RequestBody Departure departure) {
        departureService.updateOrInsert(departure);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Iterable<Departure>> search(@RequestParam String keyword) {
        Iterable<Departure> searchResults = departureService.searchByKeyword(keyword);
        return ResponseEntity.ok().body(searchResults);
    }
}
