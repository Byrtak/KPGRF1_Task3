package model3d;

import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;

public abstract class Solid {
    List<Point3D> vertexBuffer = new ArrayList<>();
    List<Integer> indexBufer = new ArrayList<>();
    int color;

}
