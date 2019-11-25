package FuzzySystems.controllers;

import FuzzySystems.DTOs.FuzzySystemToGenerateDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;
import FuzzySystems.FuzzySets.FuzzySystem;
import FuzzySystems.services.FuzzySystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("systems")
public class FuzzySystemsController {

    @Autowired
    private FuzzySystemService fuzzySystemService;

    @GetMapping("/")
    public List<FuzzySystem> getSystems() {
        return fuzzySystemService.getSystems();
    }

    @GetMapping("/{systemId}")
    public FuzzySystem getSystemDetails(@PathVariable long systemId) throws NotFoundException {
        return fuzzySystemService.getSystem(systemId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/")
    public FuzzySystem createSystem(@RequestBody String name) {
        return fuzzySystemService.createSystem(name);
    }

    @PatchMapping("/{systemId}")
    public FuzzySystem updateSystem(@PathVariable long systemId, @RequestBody FuzzySystem fuzzySystem) throws NotFoundException {
        return fuzzySystemService.updateSystem(systemId, fuzzySystem);
    }

    @DeleteMapping("/{systemId}")
    public void deleteSystem(@PathVariable long systemId) {
        fuzzySystemService.deleteSystem(systemId);
    }

    @PostMapping("/learn")
    public FuzzySystem buildSystem(@RequestBody FuzzySystemToGenerateDTO fuzzySystemToGenerateDTO) {
        return fuzzySystemService.buildSystem(
                fuzzySystemToGenerateDTO.getName(),
                fuzzySystemToGenerateDTO.getVariablesNames(),
                fuzzySystemToGenerateDTO.getData(),
                fuzzySystemToGenerateDTO.getFuzzyNumbersPerVariable(),
                fuzzySystemToGenerateDTO.getOutput());
    }

}
