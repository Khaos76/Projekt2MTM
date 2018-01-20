package pl.edu.pg.eti.mtm.s169301.projekt_2.util;

/**
 * Created by Khaos on 19.01.2018.
 */

public class Geometry {

    public static class Point {
        public final float x, y, z;
        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public Point translateY(float distance) {
            return new Point(x, y + distance, z);
        }
    }


}
