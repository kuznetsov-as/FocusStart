package cft.focus.display;

import cft.focus.model.Triangle;
import cft.focus.model.Circle;
import cft.focus.model.Rectangle;

public interface ShapeDisplay {

    void print(Circle circle);

    void print(Rectangle rectangle);

    void print(Triangle triangle);

}