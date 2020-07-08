package edu.ahs.robotics.java;

public class Main {
    public static void main(String[] args) {
        Pointiotnton a = new Pointiotnton(1,2);
        System.out.println(a.getX());
        System.out.println(a.getY());
        System.out.println(a);
        System.out.println(a.distanceFromOrigin());

    }
}