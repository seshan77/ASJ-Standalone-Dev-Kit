package edu.ahs.robotics.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {
    @Test
    public void testDuplicatesRemoved() {
        // Make some points
        Pointiotnton[] points1 = new Pointiotnton[]{new Pointiotnton(0, 0), new Pointiotnton(3, 4), new Pointiotnton(3, 4), new Pointiotnton(5, 5)};
        Pointiotnton[] path1 = new Pointiotnton[0];
        Pointiotnton[] points2 = path1;

        boolean foo = true;


        for (int i= 0; i < points1.length; i++) {
            for(int j = i + 1; j<points2.length; j++) {
                if(points2[i] == points2[j]){
                  foo = false;
                }else{foo = true;}

                }


            }
        }


    }
