package model3d;

import transforms.Point3D;

import java.awt.*;

public class Cube extends Solid {



    public Cube() {

        vertexBuffer.add(new Point3D(1,1,1)); //0
        vertexBuffer.add(new Point3D(-1,1,1)); //1
        vertexBuffer.add(new Point3D(-1,-1,1)); //2
        vertexBuffer.add(new Point3D(1,-1,1)); //3

        vertexBuffer.add(new Point3D(1,1,-1));
        vertexBuffer.add(new Point3D(-1,1,-1));
        vertexBuffer.add(new Point3D(-1,-1,-1));
        vertexBuffer.add(new Point3D(1,-1,-1));

        //horni stena
        addIndexes(0, 1, 1, 2, 2, 3, 3, 0);
        //dolni stena
        addIndexes(4, 5, 5, 6, 6, 7, 7, 4);
        //prostedni hrany
        addIndexes(0, 4, 1, 5, 2, 6, 3, 7);

        color = Color.BLUE.getRGB();

    }
}
