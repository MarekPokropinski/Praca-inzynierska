package FuzzySystems.DTOs;

import java.util.ArrayList;
import java.util.List;

public class PlotDTO {
    private List<Float> x, y;
    private String name;

    public PlotDTO() {
        x = new ArrayList<>();
        y = new ArrayList<>();
        name = "";
    }

    public PlotDTO(List<Float> x, List<Float> y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
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
}
