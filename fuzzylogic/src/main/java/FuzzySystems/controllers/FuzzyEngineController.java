package FuzzySystems.controllers;

import FuzzySystems.DTOs.EngineOutputDTO;
import FuzzySystems.DTOs.EngineVariableDTO;
import FuzzySystems.exceptions.NotFoundException;
import FuzzySystems.services.FuzzyEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FuzzyEngineController {
    @Autowired
    private FuzzyEngineService fuzzyEngineService;

    @PostMapping("system/{systemId}/process")
    public EngineOutputDTO process(@RequestBody List<EngineVariableDTO> inputs, @PathVariable long systemId) throws NotFoundException {
        return fuzzyEngineService.process(systemId,inputs);
    }
}
