package FuzzySystems.DTOs;

import java.util.ArrayList;
import java.util.List;

public class PlotDTO {
    private List<Float> x, y;
    private String shape;
    private String name;

    public PlotDTO() {
        x = new ArrayList<>();
        y = new ArrayList<>();
        name = "";
    }

    public PlotDTO(List<Float> x, List<Float> y, String name, String shape) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.shape = shape;
    }

    public List<Float> getX() {
        return x;
    }

    public void setX(List<Float> x) {
        this.x = x;
    }

    public List<Float> getY() {
        return y;
    }

    public void setY(List<Float> y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
