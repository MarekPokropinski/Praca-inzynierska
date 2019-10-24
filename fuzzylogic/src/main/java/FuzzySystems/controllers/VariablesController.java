package FuzzySystems.controllers;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.DTOs.VariableDTO;
import FuzzySystems.DTOs.VariableDetailsDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.services.VariablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("variables")
public class VariablesController {
    @Autowired
    VariablesService variablesService;

    @GetMapping("/")
    public List<VariableDTO> getVariables() {
        return variablesService.getVariables();
    }

    @PostMapping("/")
    public VariableDTO createVariable(@RequestBody String variableName) {
        return variablesService.createVariable(variableName);
    }

    @GetMapping("/{variableId}")
    public VariableDetailsDTO getVariableDetails(@PathVariable long variableId) throws NotFoundException {
        return variablesService.getVariableDetails(variableId);
    }

    @GetMapping("/{variableId}/plot")
    public List<PlotDTO> getPlot(@PathVariable long variableId) throws NotFoundException {
        return variablesService.getPlot(variableId);
    }
}
