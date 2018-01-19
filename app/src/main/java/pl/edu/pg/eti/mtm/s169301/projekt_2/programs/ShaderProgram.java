package pl.edu.pg.eti.mtm.s169301.projekt_2.programs;

import android.content.Context;

import pl.edu.pg.eti.mtm.s169301.projekt_2.util.ShaderHelper;
import pl.edu.pg.eti.mtm.s169301.projekt_2.util.TextResourceReader;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by Michał Zabłotny on 19.01.2018.
 */

public class ShaderProgram {

    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    protected final int program;
    protected ShaderProgram (Context context, int vertexShaderResourceId,
                            int fragmentShaderResourceId) {

        program = ShaderHelper.buildProgram(
                TextResourceReader.readTextFileFromResource(
                        context, vertexShaderResourceId),
                TextResourceReader.readTextFileFromResource(
                        context, fragmentShaderResourceId));
    }
    public void useProgram() {

        glUseProgram(program);
    }

}
