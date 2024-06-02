package com.bic.project.controller;

import com.bic.project.model.Circle;
import com.bic.project.model.Container;
import com.bic.project.service.CircleService;
import com.bic.project.service.ContainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Controller
public class CircleController {

    private CircleService circleService;

    @PostMapping("/circle/delete-rows")
    @ResponseBody
    public void deleteRows(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            circleService.delete(id);
        }
    }
    @PostMapping("/circle/add-data")
    @ResponseBody
    public void addData(@RequestBody Circle circle) {
        circleService.add(circle);
    }

    @PostMapping("/circle/update-row")
    @ResponseBody
    public void updateData(@RequestBody Circle circle) {
        circleService.updateOrInsert(circle);
    }

//    @GetMapping("/circle/search")
//    @ResponseBody
//    public ResponseEntity<Iterable<Circle>> search(@RequestParam String keyword) {
//        Iterable<Circle> searchResults = circleService.searchByKeyword(keyword);
//        return ResponseEntity.ok().body(searchResults);
//    }
}
