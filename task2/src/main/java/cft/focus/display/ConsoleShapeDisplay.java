package cft.focus.display;

import cft.focus.model.Shape;

public class ConsoleShapeDisplay implements ShapeDisplay {

    @Override
    public void print(Shape shape) {
        System.out.println(shape.generateDetailedInformation());
    }
}
