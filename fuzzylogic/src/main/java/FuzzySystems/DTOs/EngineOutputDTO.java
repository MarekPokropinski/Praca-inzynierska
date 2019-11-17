package FuzzySystems.DTOs;

import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class EngineOutputDTO {
    private class Variable {
        private String name;
        private List<PlotDTO> plots;
        private String fuzzyValueText;
        private double value;
        private Pair<Double, Double> range;

        public Variable(String name, List<PlotDTO> plots, String fuzzyValueText, double value, Pair<Double, Double> range) {
            this.name = name;
            this.plots = plots;
            this.fuzzyValueText = fuzzyValueText;
            this.value = value;
            this.range = range;
        }

        public String getName() {
            return name;
        }

        public List<PlotDTO> getPlots() {
            return plots;
        }

        public String getFuzzyValueText() {
            return fuzzyValueText;
        }

        public double getValue() {
            return value;
        }

        public Pair<Double, Double> getRange() {
            return range;
        }
    }
    private class InputVariable extends EngineOutputDTO.Variable {
        private List<Double> memberships;

        public InputVariable(String name, List<PlotDTO> plots, String fuzzyValueText, List<Double> memberships, Pair<Double, Double> range, double value) {
            super(name, plots, fuzzyValueText, value, range);
            this.memberships = memberships;
        }

        public List<Double> getMemberships() {
            return memberships;
        }
    }

    private class OutputVariable extends EngineOutputDTO.Variable {

        public OutputVariable(String name, List<PlotDTO> plots, String fuzzyValueText, Pair<Double, Double> range, double value) {
            super(name, plots, fuzzyValueText,value,range);
        }
    }

    private List<InputVariable> inputs;
    private List<OutputVariable> outputs;

    public EngineOutputDTO() {
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    public void addInputVariable(String name, List<PlotDTO> plots, String fuzzyValueText, List<Double> memberships, Pair<Double,Double> range, double value) {
        inputs.add(new InputVariable(name, plots, fuzzyValueText, memberships, range, value));
    }

    public void addOutputVariable(String name, List<PlotDTO> plots, String fuzzyValueText, Pair<Double,Double> range, double value) {
        outputs.add(new OutputVariable(name, plots, fuzzyValueText, range, value));
    }

    public List<InputVariable> getInputs() {
        return inputs;
    }

    public List<OutputVariable> getOutputs() {
        return outputs;
    }
}
