package com.bic.project.controller;


import com.bic.project.model.Circle;
import com.bic.project.model.Container;
import com.bic.project.service.ContainerService;
import com.bic.project.utils.ContainerUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@Controller
public class ContainerController {

    private ContainerService containerService;
    private ContainerUtils containerUtils;

    @GetMapping("/")
    public String getAll(Model model) {
        Iterable<Container> containerList = containerService.getAll();

        Map<Integer, String> currentLocationMap = new HashMap<>();
        Map<Integer, Integer> maxCircleMap = new HashMap<>();

        for (Container container : containerList) {
            currentLocationMap.put(container.getId(), ContainerUtils.getCurrentLocation(container));
            maxCircleMap.put(container.getId(), ContainerUtils.getMaxCircle(container));
        }

        model.addAttribute("containers", containerList);
        model.addAttribute("locationMap", currentLocationMap);
        model.addAttribute("maxCircleMap", maxCircleMap);

        return "main_test";
    }

    @GetMapping("/details/{id}/{circle_number}")
    public String getCurrCircle(@PathVariable Integer id, @PathVariable Integer circle_number, Model model) {
        Optional<Container> containerOptional = containerService.getById(id);

        if(containerOptional.isPresent()) {
            Container container = containerOptional.get();
            Circle circle = ContainerUtils.getCircleByNumber(container, circle_number);

            model.addAttribute("container", container);
            model.addAttribute("circle", circle);
            model.addAttribute("containerUtils", containerUtils);

            return "test";
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
    public void addData(@RequestBody String number, Integer price, String type) {
        containerService.add(new Container(number, price, type));
    }

    @PostMapping("/update-row")
    @ResponseBody
    public void updateData(@RequestBody String number, Integer price, String type) {
        containerService.updateOrInsert(new Container(number, price, type));
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Iterable<Container>> search(@RequestParam String keyword) {
        Iterable<Container> searchResults = containerService.searchByKeyword(keyword);
        return ResponseEntity.ok().body(searchResults);
    }
}
