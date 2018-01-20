package pl.edu.pg.eti.mtm.s169301.projekt_2.objects;

import pl.edu.pg.eti.mtm.s169301.projekt_2.data.VertexArray;
import pl.edu.pg.eti.mtm.s169301.projekt_2.programs.TextureShaderProgram;

import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;
import static pl.edu.pg.eti.mtm.s169301.projekt_2.Constants.BYTES_PER_FLOAT;

/**
 * Created by Khaos on 19.01.2018.
 */

public class Bands {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT
            + TEXTURE_COORDINATES_COMPONENT_COUNT) * BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {

            //podłoże triangle fun kolejność X, Y, S, T

            //banda lewa
            -0.95f,  0f,  0.05f,  0.5f,
               -1f, -1f,     0f,    0f,
             -0.9f, -1f,   0.1f,    0f,
             -0.9f,  1f,   0.1f,    1f,
               -1f,  1f,     0f,    1f,
               -1f, -1f,     0f,    0f,

            //banda dół
               0f, -0.95f, 0.5f, 0.05f,
              -0.9f,    -1f,   0f,    0f,
               0.9f,    -1f,   1f,    0f,
               0.9f,  -0.9f,   1f,  0.1f,
              -0.9f,  -0.9f,   0f,  0.1f,
              -0.9f,    -1f,   0f,    0f,

            //banda prawa
            0.95f,  0f,  0.95f,  0.5f,
               1f,  1f,     1f,    1f,
               1f, -1f,     1f,    0f,
             0.9f, -1f,   0.9f,    0f,
             0.9f,  1f,   0.9f,    1f,
               1f,  1f,     1f,    1f,

            //banda góra
              0f,  0.95f,  0.5f, 0.95f,
            0.9f,     1f,  0.9f,    1f,
            0.9f,   0.9f,  0.9f,  0.9f,
            -0.9f,  0.9f,  0.1f,  0.9f,
            -0.9f,    1f,  0.1f,    1f,
             0.9f,    1f,  0.9f,    1f,

             };

    private final VertexArray vertexArray;

    public Bands(){
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
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
        glDrawArrays(GL_TRIANGLE_FAN, 6, 6);
        glDrawArrays(GL_TRIANGLE_FAN, 12, 6);
        glDrawArrays(GL_TRIANGLE_FAN, 18, 6);
    }


}
