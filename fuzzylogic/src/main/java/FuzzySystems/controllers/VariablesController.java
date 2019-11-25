package FuzzySystems.controllers;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.DTOs.VariableDTO;
import FuzzySystems.DTOs.VariableDetailsDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.services.VariablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VariablesController {
    @Autowired
    VariablesService variablesService;

    @GetMapping("systems/{systemId}/variables")
    public List<VariableDTO> getVariables(@PathVariable long systemId) {
        return variablesService.getVariables(systemId);
    }

    @PostMapping("systems/{systemId}/variables")
    public VariableDTO createVariable(@PathVariable long systemId, @RequestBody VariableDTO variable) throws NotFoundException {
        return variablesService.createVariable(systemId, variable.getName(), variable.isInput());
    }

    @GetMapping("variables/{variableId}")
    public VariableDetailsDTO getVariableDetails(@PathVariable long variableId) throws NotFoundException {
        return variablesService.getVariableDetails(variableId);
    }

    @PutMapping("variables/{variableId}")
    public void updateVariable(@PathVariable long variableId, @RequestBody VariableDTO variable) throws NotFoundException {
        variablesService.updateVariable(variableId, variable.getName(), variable.isInput());
    }

    @DeleteMapping("variables/{variableId}")
    public ResponseEntity deleteVariable(@PathVariable long variableId) {
        try{
            variablesService.deleteVariable(variableId);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("variables/{variableId}/plot")
    public List<PlotDTO> getPlot(@PathVariable long variableId) throws NotFoundException {
        return variablesService.getPlot(variableId);
    }
}
