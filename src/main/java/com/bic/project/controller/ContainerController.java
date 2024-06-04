package com.bic.project.controller;


import com.bic.project.model.Circle;
import com.bic.project.model.Container;
import com.bic.project.service.CircleService;
import com.bic.project.service.ContainerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Controller
public class ContainerController {

    private ContainerService containerService;
    private CircleService circleService;

    @GetMapping("/")
    public String getAll(Model model) {
        Iterable<Container> taskList = containerService.getAll();
        model.addAttribute("containers", taskList);
        return "main";
    }

//    @GetMapping("/details/{id}")
//    public String getCurrContainer(@PathVariable Integer id, Model model) {
//        Optional<Container> containerOptional = containerService.getById(id);
//
//        if(containerOptional.isPresent()) {
//            Container container = containerOptional.get();
//            model.addAttribute("container", container);
//            return "details";
//        } else {
//            return "redirect:/";
//        }
//    }

    @GetMapping("/details/{id}/{circle_number}")
    public String getCurrCircle(@PathVariable Integer id, @PathVariable Integer circle_number, Model model) {
        Optional<Container> containerOptional = containerService.getById(id);

        if(containerOptional.isPresent()) {
            Container container = containerOptional.get();
            model.addAttribute("container", container);
            Circle circle = container.getCircleByNumber(circle_number);
            model.addAttribute("circle", circle);
            return "details";
        }
        return "redirect:/";
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
