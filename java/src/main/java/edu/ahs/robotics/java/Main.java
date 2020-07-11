package edu.ahs.robotics.java;

import java.awt.Point;

public class Main {
    public static void main(String[] args) {
        Pointiotnton a = new Pointiotnton(1,2);
        Pointiotnton b = new Pointiotnton(3, 4);
        Pointiotnton a2 = new Pointiotnton(3,4);
        Pointiotnton b2 = new Pointiotnton(11,7);
        Pointiotnton current = new Pointiotnton(7,8);

        Pointiotnton[] ab = new Pointiotnton[4];
        ab[0] = a;
        ab[1] = b;
        ab[2] = a2;
        ab[3] = b2;



        System.out.println(a.getX());
        System.out.println(a.getY());
        System.out.println(a);


        Path dummy = new Path(ab);

        dummy.printWayPoints();
        System.out.println(dummy.totalDistance());
        dummy.printTargetPoint(current, .5);

    }
}