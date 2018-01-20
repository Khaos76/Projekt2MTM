package pl.edu.pg.eti.mtm.s169301.projekt_2.programs;

import android.content.Context;

import pl.edu.pg.eti.mtm.s169301.projekt_2.R;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static pl.edu.pg.eti.mtm.s169301.projekt_2.R.*;

/**
 * Created by Michał Zabłotny on 19.01.2018.
 */

public class ColorShaderProgram extends ShaderProgram {

    private final int uMatrixLocation;
    private final int aPositionLocation;
    private final int aColorLocation;

    public ColorShaderProgram(Context context) {
        super(context, raw.vertex_shader, raw.fragment_shader);

        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aColorLocation = glGetAttribLocation(program, A_COLOR);
    }

    public void setUniforms(float[] matrix) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }
    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }
    public int getColorAttributeLocation() {
        return aColorLocation;
    }
}
