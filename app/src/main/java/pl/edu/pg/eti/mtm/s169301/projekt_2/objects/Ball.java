package pl.edu.pg.eti.mtm.s169301.projekt_2.objects;

import pl.edu.pg.eti.mtm.s169301.projekt_2.data.VertexArray;
import pl.edu.pg.eti.mtm.s169301.projekt_2.programs.ColorShaderProgram;

import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.glDrawArrays;
import static pl.edu.pg.eti.mtm.s169301.projekt_2.Constants.BYTES_PER_FLOAT;

/**
 * Created by Michał Zabłotny on 19.01.2018.
 */

public class Ball {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE =
            (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT)
                    * BYTES_PER_FLOAT;


    private static final float[] VERTEX_DATA = {
    // Order of coordinates: X, Y, R, G, B
            0f, -0.6f, 1f, 0f, 0f};

    private final VertexArray vertexArray;

    public Ball() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(
                0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);
        vertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                colorProgram.getColorAttributeLocation(),
                COLOR_COMPONENT_COUNT,
                STRIDE);
    }

    public void draw() {
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
