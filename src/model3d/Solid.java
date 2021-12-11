package model3d;

import transforms.Point3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Solid {
    List<Point3D> vertexBuffer = new ArrayList<>();
    List<Integer> indexBuffer = new ArrayList<>();
    int color;


    final void addIndexes(Integer... indexes) {
        indexBuffer.addAll(Arrays.asList(indexes));

    }

    public List<Point3D> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public int getColor() {
        return color;
    }
}
