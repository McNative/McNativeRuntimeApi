package org.mcnative.runtime.api.utils.positioning;

import org.mcnative.runtime.api.McNative;

public interface Position extends Vector {

    float getPitch();

    float getYaw();

    void setPitch(float pitch);

    void setYaw(float yaw);

    Vector getDirection();

    Position setDirection(Vector vector);

    Position clone();

    static Position of(int x, int y, int z){
        return of((double) x, y, z,0,0);
    }

    static Position of(double x, double y, double z){
        return of(x, y, z,0,0);
    }

    static Position of(int x, int y, int z, float pitch, float yaw){
        return of((double) x, y, z, pitch, yaw);
    }

    static Position of(double x, double y, double z, float pitch, float yaw){
        return McNative.getInstance().getObjectFactory().createObject(Position.class,x,y,z,pitch,yaw);
    }

}
