package pl.edu.pg.eti.mtm.s169301.projekt_2;
/**
 * Created by Khaos on 18.01.2018.
 */

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import pl.edu.pg.eti.mtm.s169301.projekt_2.objects.Ball;
import pl.edu.pg.eti.mtm.s169301.projekt_2.objects.Ball3D;
import pl.edu.pg.eti.mtm.s169301.projekt_2.objects.Bands;
import pl.edu.pg.eti.mtm.s169301.projekt_2.objects.Cube;
import pl.edu.pg.eti.mtm.s169301.projekt_2.objects.Table;
import pl.edu.pg.eti.mtm.s169301.projekt_2.programs.ColorShaderProgram;
import pl.edu.pg.eti.mtm.s169301.projekt_2.programs.TextureShaderProgram;
import pl.edu.pg.eti.mtm.s169301.projekt_2.util.MatrixHelper;
import pl.edu.pg.eti.mtm.s169301.projekt_2.util.TextureHelper;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;


public class ProjektRenderer implements Renderer {

    private final Context context;
    private final float[] projectionMatrix = new float[16];
    private final float[] modelMatrix = new float[16];
    private Table table;
    private Ball ball;
    private Bands bands;
    private Ball3D ball3D;
    private Cube cube;
    private TextureShaderProgram textureProgram;
    private ColorShaderProgram colorProgram;
    private int texture1 ,texture2;
    public ProjektRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        table = new Table();
        bands = new Bands();
        ball = new Ball();
        ball3D = new Ball3D();
        cube = new Cube();


        textureProgram = new TextureShaderProgram(context);
        colorProgram = new ColorShaderProgram(context);
        texture1 = TextureHelper.loadTexture(context, R.drawable.grass2);
        texture2 = TextureHelper.loadTexture(context, R.drawable.wall_teture);
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {

        glClear(GL_COLOR_BUFFER_BIT);

        textureProgram.useProgram();
        textureProgram.setUniforms(projectionMatrix, texture1);
        table.bindData(textureProgram);
        table.draw();

        textureProgram.useProgram();
        textureProgram.setUniforms(projectionMatrix, texture2);
        bands.bindData(textureProgram);
        bands.draw();

        //textureProgram.useProgram();
        //textureProgram.setUniforms(projectionMatrix, texture2);
        //ball3D.DrawBall(100);
       // ball3D.bindData(textureProgram);
       // ball3D.draw();

        colorProgram.useProgram();
        colorProgram.setUniforms(projectionMatrix);
        ball.bindData(colorProgram);
        ball.draw();

        colorProgram.useProgram();
        colorProgram.setUniforms(projectionMatrix);
        cube.bindData(textureProgram);
        cube.draw();



    }

    @Override
    public void onSurfaceChanged(GL10 nieUzywana, int width, int height){
        glViewport(0,0, width, height);

        MatrixHelper.perspectiveM(projectionMatrix, 75, (float) width
                / (float) height, 1f, 10f);
        setIdentityM(modelMatrix, 0);
        translateM(modelMatrix, 0, 0f, 0f, -2f);
        rotateM(modelMatrix, 0, 0f, 1f, 0f, 0f);

        final float[] temp = new float[16];
        multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);

    }


}
