package com.bic.project.controller;

import com.bic.project.model.Circle;
import com.bic.project.service.CircleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
public class CircleController {

    private CircleService circleService;

    @GetMapping("/")
    public String getAll(Model model) {
        Iterable<Circle> taskList = circleService.getAll();
        model.addAttribute("circles", taskList);
        return "circles";
    }

    @PostMapping("/delete-rows")
    @ResponseBody
    public void deleteRows(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            circleService.delete(id);
        }
    }
    @PostMapping("/add-data")
    @ResponseBody
    public void addData(@RequestBody Circle circle) {
        circleService.add(circle);
    }

    @PostMapping("/update-row")
    @ResponseBody
    public void updateData(@RequestBody Circle circle) {
        circleService.updateOrInsert(circle);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Iterable<Circle>> search(@RequestParam String keyword) {
        Iterable<Circle> searchResults = circleService.searchByKeyword(keyword);
        return ResponseEntity.ok().body(searchResults);
    }
}
