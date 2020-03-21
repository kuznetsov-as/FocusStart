package cft.focus.model;

public enum ShapeType {
    CIRCLE("Круг"),
    RECTANGLE("Прямоугольник"),
    TRIANGLE("Треугольник");

    private String shapeType;

    ShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public String getShapeType() {
        return shapeType;
    }
}
