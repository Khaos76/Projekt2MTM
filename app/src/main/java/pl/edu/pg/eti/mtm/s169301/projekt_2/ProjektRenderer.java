package pl.edu.pg.eti.mtm.s169301.projekt_2; /**
 * Created by Khaos on 18.01.2018.
 */

import android.opengl.GLSurfaceView.Renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;

import pl.edu.pg.eti.mtm.s169301.projekt_2.util.LoggerConfig;
import pl.edu.pg.eti.mtm.s169301.projekt_2.util.ShaderHelper;
import pl.edu.pg.eti.mtm.s169301.projekt_2.util.TextResourceReader;
import android.content.Context;
import static android.opengl.GLES20.*;
import static android.opengl.GLUtils.*;
import static android.opengl.Matrix.*;




public class ProjektRenderer implements Renderer {
    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexData;
    private final Context context;
    private int program;
    private static final String U_COLOR = "u_Color";
    private int uColorLocation;
    private static final String A_POSITION = "a_Position";
    private int aPositionLocation;
    private static final String U_MATRIX = "u_Matrix";
    private final float[] projectionMatrix = new float[16];
    private int uMatrixLocation;


    public ProjektRenderer(Context context) {

        this.context = context;

        float[] tableVerticles = {
                //podłoże triangle fan
                0f, 0f, 0f,
                -0.9f, -0.9f,0f,
                0.9f, -0.9f,0f,
                0.9f, 0.9f,0f,
                -0.9f, 0.9f,0f,
                -0.9f, -0.9f,0f,

                //Srodek kuli
                0.0f, -0.75f,0f,

                //banda lewa
                -1f,-1f,0f,
                -0.9f,-1f,0f,
                -0.9f, 1f,0f,
                -1f,-1f,0f,
                -0.9f,1f,0f,
                -1f,1f,0f,

                //banda dolna
                -0.9f,-1f,0f,
                -0.9f,-0.9f,0f,
                0.9f,-0.9f,0f,
                0.9f,-0.9f,0f,
                0.9f,-1f,0f,
                -0.9f,-1f,0f,

                //banda prawa
                1f,-1f,0f,
                0.9f,-1f,0f,
                1f,1f,0f,
                0.9f,-1f,0f,
                1f,1f,0f,
                0.9f,1f,0f,

                //banda górna
                0.9f,1f,0f,
                0.9f,0.9f,0f,
                -1f,1f,0f,
                0.9f,0.9f,0f,
                -1f,0.9f,0f,
                -1f,1f,0f,


        };
        vertexData = ByteBuffer.allocateDirect(tableVerticles.length*BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(tableVerticles);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        String vertexShaderSource = TextResourceReader
                .readTextFileFromResource(context, R.raw.vertex_shader );
        String fragmentShaderSource = TextResourceReader
                .readTextFileFromResource(context, R.raw.fragment_shader);

        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);

        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);

        if (LoggerConfig.ON){
            ShaderHelper.validateProgram(program);
        }

        glUseProgram(program);

        uColorLocation = glGetUniformLocation(program, U_COLOR);
        aPositionLocation = glGetAttribLocation(program, A_POSITION);

        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT,
                false, 0, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
    }

    @Override
    public void onSurfaceChanged(GL10 nieUzywana, int width, int height){
        glViewport(0,0, width, height);
        final float aspectRatio = width > height ?
                (float) width / (float)height:
                (float)height / (float)width;
        if (width > height){
            orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio,
                    -1f,1f,-1f,1f);
        }else {
            orthoM(projectionMatrix, 0, -1f, 1f,
                    -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    @Override
    public void onDrawFrame(GL10 nieUzywana){
        glClear(GL_COLOR_BUFFER_BIT);
        glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix,0);

        //podłoże
        glUniform4f(uColorLocation, 0.137255f, 0.556863f, 0.137255f, 1.0f);
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);

        glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
        glDrawArrays(GL_POINTS, 6, 1);
        //bandy
        glUniform4f(uColorLocation, 0.0f, 1.0f, 1.0f, 1.0f);
        glDrawArrays(GL_TRIANGLES,7,24);
    }
}
