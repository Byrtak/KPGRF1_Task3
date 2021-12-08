package model3d;

import transforms.Point3D;

import java.awt.*;

public class Cube extends Solid {
    public Cube() {
        color = Color.YELLOW.getRGB();

        vertexBuffer.add(new Point3D(1,1,1));
        vertexBuffer.add(new Point3D(-1,1,1));
        vertexBuffer.add(new Point3D(-1,-1,1));
        vertexBuffer.add(new Point3D(1,-1,1));

        vertexBuffer.add(new Point3D(1,1,-1));
        vertexBuffer.add(new Point3D(-1,1,-1));
        vertexBuffer.add(new Point3D(-1,-1,-1));
        vertexBuffer.add(new Point3D(1,-1,-1));



    }
}
