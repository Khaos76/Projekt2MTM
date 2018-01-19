package pl.edu.pg.eti.mtm.s169301.projekt_2; /**
 * Created by Khaos on 18.01.2018.
 */

import android.opengl.GLSurfaceView.Renderer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;

import pl.edu.pg.eti.mtm.s169301.projekt_2.objects.Ball;
import pl.edu.pg.eti.mtm.s169301.projekt_2.objects.Table;
import pl.edu.pg.eti.mtm.s169301.projekt_2.programs.ColorShaderProgram;
import pl.edu.pg.eti.mtm.s169301.projekt_2.programs.TextureShaderProgram;
import pl.edu.pg.eti.mtm.s169301.projekt_2.util.TextureHelper;

import android.content.Context;
import static android.opengl.GLES20.*;


public class ProjektRenderer implements Renderer {

    private final Context context;
    private final float[] projectionMatrix = new float[16];
    private final float[] modelMatrix = new float[16];
    private Table table;
    private Ball ball;
    private TextureShaderProgram textureProgram;
    private ColorShaderProgram colorProgram;
    private int texture;


    public ProjektRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        table = new Table();
        ball = new Ball();

        textureProgram = new TextureShaderProgram(context);
        colorProgram = new ColorShaderProgram(context);

        texture = TextureHelper.loadTexture(context, R.drawable.grass2);
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {

        glClear(GL_COLOR_BUFFER_BIT);
        // Draw the table.
        textureProgram.useProgram();
        textureProgram.setUniforms(projectionMatrix, texture);
        table.bindData(textureProgram);
        table.draw();
        //Draw Ball
        colorProgram.useProgram();
        colorProgram.setUniforms(projectionMatrix);
        ball.bindData(colorProgram);
        ball.draw();
    }

    @Override
    public void onSurfaceChanged(GL10 nieUzywana, int width, int height){
        glViewport(0,0, width, height);

    }


}
