package FuzzySystems.DTOs;

import FuzzySystems.FuzzySets.FuzzyNumbers.TriangularNumber;

public class TriangularNumberDTO {
    private float a, b, c;

    public TriangularNumberDTO() {
    }

    public TriangularNumberDTO(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static TriangularNumberDTO fromEntity(TriangularNumber triangularNumber) {
        return new TriangularNumberDTO(triangularNumber.getA(), triangularNumber.getB(), triangularNumber.getC());
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public String getType() {
        return "Triangular";
    }
}
