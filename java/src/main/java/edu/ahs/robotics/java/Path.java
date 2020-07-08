package edu.ahs.robotics.java;
import java.util.ArrayList;

public class Path {


    public double totalDistance() {
        return 0.0;
    }

    public static class WayPoint {
        public Pointiotnton point;
        private double deltaXFromPrevious;
        private double deltaYFromPrevious;
        private double distanceFromPrevious;

        private WayPoint(Pointiotnton point, double deltaXFromPrevious, double deltaYFromPrevious, double distanceFromPrevious) {
            this.point = point;
            this.deltaXFromPrevious = deltaXFromPrevious;
            this.deltaYFromPrevious = deltaYFromPrevious;
            this.distanceFromPrevious = distanceFromPrevious;
        }

        private WayPoint(Pointiotnton point){
            this.point=point;
        }

        public String toString() {
            return "Point{" + "x=" + point.getX() + ", y=" + point.getY() + '}';
        }


        /**
         * @return a point at the supplied look-ahead distance along the path from the supplied current position
         * Note that the point will usually be interpolated between the points that originally defined the Path
         */

        public Path.WayPoint targetPoint(Pointiotnton current, double lookAheadDistance) {
        return new WayPoint(new Pointiotnton(0,0));
    }
        /**
         * Calculates the projection of the vector Vcurrent leading from the supplied current
         * point to this WayPoint onto the vector Vpath leading from the previous point on the path
         * to this WayPoint.  If the return value is positive, it means that the WayPoint is
         * farther along the path from the current point.  If the return value is negative, it means
         * that the WayPoint is before the current point (earlier on the path).
         *The magnitude of the value tells the
         * distance along the path.  The value is computed as the dot product between Vcurrent and
         * Vpath, normalized by the length of vPath
         * @param current The source point to compare to the WayPoint
         */
        private double componentAlongPath(Pointiotnton current) {
            double deltaXFromCurrent = point.getX() - current.getX();
            double deltaYFromCurrent = point.getY() - current.getY();

            double dp = deltaXFromCurrent * deltaXFromPrevious + deltaYFromCurrent * deltaYFromPrevious;
            return dp / distanceFromPrevious;
        }
    }
    private ArrayList<WayPoint> wayPoints;

    public Path(Pointiotnton[] rawPoints) {
        wayPoints=new ArrayList<>();
        for(Pointiotnton i:rawPoints){
            wayPoints.add(new WayPoint(i));
        }

    }
    public void printArrayPoints(){
        for(WayPoint a:wayPoints){
            System.out.println(a);
        }
    }




}
