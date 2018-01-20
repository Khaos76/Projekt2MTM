package pl.edu.pg.eti.mtm.s169301.projekt_2.objects;

import pl.edu.pg.eti.mtm.s169301.projekt_2.data.VertexArray;
import pl.edu.pg.eti.mtm.s169301.projekt_2.programs.TextureShaderProgram;

import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;
import static pl.edu.pg.eti.mtm.s169301.projekt_2.Constants.BYTES_PER_FLOAT;

/**
 * Created by Khaos on 20.01.2018.
 */

public class Ball3D {

    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT
            + TEXTURE_COORDINATES_COMPONENT_COUNT) * BYTES_PER_FLOAT;
    int numberOfVertices = 0;



    private static final float[] VERTEX_DATA = new float[10000000] ;

    public void DrawBall (float step){
        float angleA;
        float angleB;
        float cos, sin;
        float r1, r2;
        float h1, h2;

        int n = 0;

        for (angleA = -90.0f; angleA <= 90.0f; angleA += step) {

            r1 = (float) Math.cos(angleA * Math.PI / 180.0);
            r2 = (float) Math.cos((angleA + step) * Math.PI / 180.0);
            h1 = (float) Math.sin(angleA * Math.PI / 180.0);
            h2 = (float) Math.sin((angleA + step) * Math.PI / 180.0);

            for (angleB = 0.0f; angleB <= 360.0f; angleB += step) {

                cos = (float) Math.cos(angleB * Math.PI / 180.0);
                sin = -(float) Math.sin(angleB * Math.PI / 180.0);

                VERTEX_DATA[n] =(r2*cos);
                VERTEX_DATA[n + 1] = (h2);
                VERTEX_DATA[n + 2] = (r2 * sin);
                VERTEX_DATA[n + 3] = (r2 * cos);
                VERTEX_DATA[n + 4] = (h2);

                VERTEX_DATA[n + 5] = (r1 * cos);
                VERTEX_DATA[n + 5 + 1] = (h1);
                VERTEX_DATA[n + 5 + 2] = (r1 * sin);
                VERTEX_DATA[n + 5 + 3] = (r1 * cos);
                VERTEX_DATA[n + 5 + 4] = (h1);

                n += 10;
                numberOfVertices += 2;

            }
        }
    }



    private final VertexArray vertexArray;

    public Ball3D(){
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(TextureShaderProgram textureProgram){
        vertexArray.setVertexAttribPointer(0,
                textureProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);
        vertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordinatesAttributeLocation(),
                TEXTURE_COORDINATES_COMPONENT_COUNT,
                STRIDE);
    }
    public void draw(){
        glDrawArrays(GL_TRIANGLE_STRIP, 0, numberOfVertices);
    }
}
