package com.bic.project.controller;

import com.bic.project.model.Arrival;
import com.bic.project.service.ArrivalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
public class ArrivalController {

    private ArrivalService arrivalService;

    @GetMapping("/arrival")
    public String getAll(Model model) {
        Iterable<Arrival> taskList = arrivalService.getAll();
        model.addAttribute("arrivals", taskList);
        return "arrivals";
    }

    @PostMapping("/arrival/delete-rows")
    @ResponseBody
    public void deleteRows(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            arrivalService.delete(id);
        }
    }
    @PostMapping("/arrival/add-data")
    @ResponseBody
    public void addData(@RequestBody Arrival arrival) {
        arrivalService.add(arrival);
    }

    @PostMapping("/arrival/update-row")
    @ResponseBody
    public void updateData(@RequestBody Arrival arrival) {
        arrivalService.updateOrInsert(arrival);
    }

    @GetMapping("/arrival/search")
    @ResponseBody
    public ResponseEntity<Iterable<Arrival>> search(@RequestParam String keyword) {
        Iterable<Arrival> searchResults = arrivalService.searchByKeyword(keyword);
        return ResponseEntity.ok().body(searchResults);
    }
}
