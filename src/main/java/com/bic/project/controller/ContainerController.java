package com.bic.project.controller;

import com.bic.project.model.Container;
import com.bic.project.service.ContainerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
public class ContainerController {

    private ContainerService containerService;

    @GetMapping("/")
    public String getAll(Model model) {
        Iterable<Container> taskList = containerService.getAll();
        model.addAttribute("containers", taskList);
        return "containers";
    }

    @PostMapping("/delete-rows")
    @ResponseBody
    public void deleteRows(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            containerService.delete(id);
        }
    }
    @PostMapping("/add-data")
    @ResponseBody
    public void addData(@RequestBody Container container) {
        containerService.add(container);
    }

    @PostMapping("/update-row")
    @ResponseBody
    public void updateData(@RequestBody Container container) {
        containerService.updateOrInsert(container);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Iterable<Container>> search(@RequestParam String keyword) {
        Iterable<Container> searchResults = containerService.searchByKeyword(keyword);
        return ResponseEntity.ok().body(searchResults);
    }
}
