package cft.focus.display;

import cft.focus.model.Shape;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleShapeDisplay implements ShapeDisplay {

    @Override
    public void print(Shape shape) {
        System.out.println(shape.generateDetailedInformation());
        log.info("Произведен вывод результата в консоль");
    }
}
