package ink.flyird.fcommon.math;

import org.joml.Matrix4d;

/**
 * simple rotation constants to rotate.
 *
 * @author GrassBlock2022
 */
public class RotationMatrixConstants {
    public static final Matrix4d FACE_LEFT=new Matrix4d().rotateXYZ(Math.toRadians(-90),0,0);
    public static final Matrix4d FACE_RIGHT=new Matrix4d().rotateXYZ(Math.toRadians(90),0,0);
    public static final Matrix4d FACE_FRONT=new Matrix4d().rotateXYZ(0,0,Math.toRadians(-90));
    public static final Matrix4d FACE_BACK=new Matrix4d().rotateXYZ(0,0,Math.toRadians(90));
    public static final Matrix4d FACE_TOP=new Matrix4d().rotateXYZ(0,0,0);
    public static final Matrix4d FACE_BOTTOM=new Matrix4d().rotateXYZ(0,0,Math.toRadians(180));

}
