package edu.ahs.robotics.java;
import java.util.ArrayList;

public class Path<y> {




    public static class WayPoint {
        public Pointiotnton point;
        private double deltaXFromPrevious;
        private double deltaYFromPrevious;
        private double distanceFromPrevious;
        private double slopeFromPrevious;
        private double angleFromPrevious;

        private WayPoint(Pointiotnton point, double deltaXFromPrevious, double deltaYFromPrevious, double distanceFromPrevious) {
            this.point = point;
            this.deltaXFromPrevious = deltaXFromPrevious;
            this.deltaYFromPrevious = deltaYFromPrevious;
            this.distanceFromPrevious = distanceFromPrevious;
            slopeFromPrevious = deltaYFromPrevious/deltaXFromPrevious;
            if(deltaXFromPrevious!=0) {
                angleFromPrevious = Math.atan2(deltaYFromPrevious, deltaXFromPrevious);
            }else{
                angleFromPrevious = Math.PI/2;
            }
        }

        public double getDistanceFromPrevious() {
            return distanceFromPrevious;
        }

        public String toString() {
            return "Point{" + "x=" + point.getX() + ", y=" + point.getY() + "distance from previous=" + distanceFromPrevious + '}';
        }

        public double getSlopeFromPrevious() {
            return slopeFromPrevious;
        }

        public double getAngleFromPrevious() {
            return angleFromPrevious;
        }

        public double getX(){
            return point.getX();
        }

        public double getY(){
            return point.getY();
        }

        /**
         * @return a point at the supplied look-ahead distance along the path from the supplied current position
         * Note that the point will usually be interpolated between the points that originally defined the Path
         */


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
    Pointiotnton j = new Pointiotnton(0,0);
    int counter= 0;
    double totalDistance = 0.0;



    public Path(Pointiotnton[] rawPoints) {
        double deltaXFromPrevious = 0.0;
        double deltaYFromPrevious = 0.0;
        double distanceFromPrevious = 0.0;

        wayPoints = new ArrayList<>();
        j = rawPoints[0];
        wayPoints.add(new WayPoint(rawPoints[0], 0, 0, 0));


        wayPoints=new ArrayList<>();
        for(int i=1; i<rawPoints.length; i++){
            deltaXFromPrevious = rawPoints[i].getX() - j.getX();
            deltaYFromPrevious = rawPoints[i].getY() - j.getY();
            distanceFromPrevious = Math.sqrt(deltaXFromPrevious*deltaXFromPrevious + deltaYFromPrevious*deltaYFromPrevious);

            if(rawPoints[i].getX() != j.getX() || rawPoints[i].getY() != j.getY()){
                wayPoints.add(new WayPoint(rawPoints[i], deltaXFromPrevious, deltaYFromPrevious, distanceFromPrevious));
                totalDistance = totalDistance + distanceFromPrevious;
            }
            j = rawPoints[i];


        }

    }


    public void printWayPoints(){
        for(WayPoint a:wayPoints){
            System.out.println(a);
        }
    }
    public double totalDistance() {
        return totalDistance;
    }

    public void printTargetPoint(Pointiotnton current, double targetDistance){
        System.out.println(targetPoint(current, targetDistance));
    }

    public Path.WayPoint targetPoint(Pointiotnton current, double targetDistance) {
        boolean b = true;
        int i = -1;
        int j = 0;
        double distance = 0;
        double popX = 0;
        double popY = 0;
        double distancePassed = targetDistance;
        double popDistance;
        double distanceX;
        double distanceY;
        double finalDeltaX;
        double finalDeltaY;

        WayPoint a = new WayPoint(new Pointiotnton(0,0),0,0,0);
        WayPoint c = new WayPoint(new Pointiotnton(0,0),0,0,0);
        Pointiotnton targetPoint;

        while(b && i<wayPoints.size()){
            i++;
            a = wayPoints.get(i);
                if(a.componentAlongPath(current) > 0){
                    b = false;
                }
        }

        distance = a.componentAlongPath(current);
        popY = a.componentAlongPath(current)*Math.sin(a.getAngleFromPrevious());
        popX = a.componentAlongPath(current)*Math.cos(a.getAngleFromPrevious());

        popDistance = Math.sqrt(popX*popX+popY*popY);
        WayPoint shadow = new WayPoint(new Pointiotnton(a.getX()-popX, a.getY()-popY),a.deltaXFromPrevious-popX,
                a.deltaYFromPrevious-popY, a.distanceFromPrevious-popDistance);

        if(distance < targetDistance) {
            distancePassed = distancePassed - distance;
        }

        while(distance < targetDistance){
            i++; j++;
            c = wayPoints.get(i);
            distance = distance + c.getDistanceFromPrevious();
            distancePassed = distancePassed - c.getDistanceFromPrevious();

        }

        if(j==0){
            double ratio = a.getDistanceFromPrevious()/distancePassed;
            distanceX = a.deltaXFromPrevious / ratio;
            distanceY = a.deltaYFromPrevious / ratio;
            distanceX = (a.deltaXFromPrevious - shadow.deltaXFromPrevious) - distanceX;
            distanceY = (a.deltaYFromPrevious - shadow.deltaYFromPrevious) - distanceY;
            Pointiotnton target = new Pointiotnton(c.getX()-distanceX, c.getY()-distanceY);
            targetPoint=target;
        }
        else{
            double ratio = c.getDistanceFromPrevious()/distancePassed;
            distanceX = c.deltaXFromPrevious/ratio;
            distanceY = c.deltaYFromPrevious/ratio;
            distanceX = c.deltaXFromPrevious-distanceX;
            distanceY = c.deltaYFromPrevious-distanceY;
            Pointiotnton target = new Pointiotnton(c.getX()-distanceX, c.getY()-distanceY);
            targetPoint=target;
        }

        finalDeltaX = targetPoint.getX() - current.getX();
        finalDeltaY = targetPoint.getY() - current.getY();


        return new WayPoint(targetPoint, finalDeltaX, finalDeltaY, Math.sqrt(finalDeltaX*finalDeltaX + finalDeltaY*finalDeltaY));
    }







}
