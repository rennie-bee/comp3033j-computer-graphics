package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class AthleteCube {

    // basic colours
    static float black[] = {0.0f, 0.0f, 0.0f, 1.0f};
    static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

    static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};
    static float spot[] = {0.1f, 0.1f, 0.1f, 0.5f};

    // primary colours
    static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};
    static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

    // secondary colours
    static float yellow[] = {1.0f, 1.0f, 0.0f, 1.0f};
    static float magenta[] = {1.0f, 0.0f, 1.0f, 1.0f};
    static float cyan[] = {0.0f, 1.0f, 1.0f, 1.0f};

    // other colours
    static float orange[] = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float dkgreen[] = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float pink[] = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};

    public static float chestRotation2, chestRotation3;

    public AthleteCube() {

    }

    // Implement using notes  in Animation lecture
    public void DrawAthleteCube(float delta, double ballCount, int loopTime, boolean GoodAnimation, Texture texture_cube) {
        // I multiply the theta with eight to attain greater frequency of rotating limbs, which matches the speed of the
        // little guy.
        float theta = (float) (delta * 2 * Math.PI) * 8;
        // Apart from limbRotation, declare another thighRotation to imitate more authentic running
        float LimbRotation, thighRotation;
/*        if (GoodAnimation) {
            // limbRotation is for arms and small legs
            LimbRotation = (float) Math.cos(theta) * 45;
            // thighRotation is for big legs, so it should be bigger to embody the movement of knees.
            thighRotation = (float) Math.cos(theta) * 100;
        } else {
            LimbRotation = 0;
            thighRotation = 0;
        }*/

        // The 3 rotations here are the rotations that happen on red cube guy.
        float theta2 = (float) (ballCount * 2 * Math.PI)/5;
        float chestRotation = (float) Math.cos(theta2) * 45;
        float theta3 = (float) ((ballCount - 5.8) * 2 * Math.PI)/5;
        // rotates the waist
        chestRotation2 = (float) Math.cos(theta3) * 45;
        float theta4 = (float) ((ballCount + 6.2) * 2 * Math.PI)/5;
        // rotate the small arm
        chestRotation3 = (float) Math.cos(theta4) * 45;

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        Cube cube = new Cube();

        GL11.glPushMatrix();
        {
            // pelvis: a white sphere
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.0f,10.0f,0.0f);

                if (ballCount >= 9.5 && loopTime <= 3) {
                    GL11.glRotatef(chestRotation2*1.5f, 0, 1, 0);
                }
                if (ballCount <= 2.5) {
                    //if (ballCount >= 9.5 || (ballCount >= 0 && ballCount < 2.5)) {
                    GL11.glRotatef(chestRotation3, 0, 1, 0);
                }

                sphere.DrawSphere(0.5f, 32, 32);

                // left hip
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f,-0.25f,0.5f);
                    sphere.DrawSphere(0.3f, 32, 32);

                    // left thigh
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f,0.0f,0.0f);
                        GL11.glRotatef(75f, 1, 0, 0);
                        cylinder.DrawCylinder(0.3f, 1f, 32);

                        // left knee
                        GL11.glPushMatrix();
                        {
                            GL11.glColor3f(pink[0], pink[1], pink[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                            GL11.glTranslatef(0f,0.0f,1f);
                            sphere.DrawSphere(0.3f, 32, 32);

                            // left small leg
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0f,0.0f,0f);
                                cylinder.DrawCylinder(0.25f, 1.25f, 32);

                                // left ankle
                                GL11.glColor3f(red[0], red[1], red[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0f, 0f, 1.25f);
                                    GL11.glRotatef(90f, 1, 0, 0);
                                    GL11.glRotatef(90f, 0, 1, 0);
                                    sphere.DrawSphere(0.3f, 32, 32);

                                    // left foot
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0, 0, 0);
                                        cylinder.DrawCylinder(0.25f, 0.5f, 32);
                                    } GL11.glPopMatrix();
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();


                } GL11.glPopMatrix();

                // right hip
                GL11.glColor3f(white[0], white[1], white[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f,-0.25f,-0.5f);
                    sphere.DrawSphere(0.3f, 32, 32);

                    // right thigh
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f,0.0f,0.0f);
                        GL11.glRotatef(105f, 1, 0, 0);
                        cylinder.DrawCylinder(0.3f, 1f, 32);

                        // right knee
                        GL11.glPushMatrix();
                        {
                            GL11.glColor3f(pink[0], pink[1], pink[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                            GL11.glTranslatef(0f,0.0f,1f);
                            sphere.DrawSphere(0.3f, 32, 32);

                            // right small leg
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0f,0.0f,0f);
                                cylinder.DrawCylinder(0.25f, 1.25f, 32);

                                // right ankle
                                GL11.glColor3f(red[0], red[1], red[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0f, 0f, 1.25f);
                                    GL11.glRotatef(90f, 1, 0, 0);
                                    GL11.glRotatef(90f, 0, 1, 0);
                                    sphere.DrawSphere(0.3f, 32, 32);

                                    // right foot
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0, 0, 0);
                                        cylinder.DrawCylinder(0.25f, 0.5f, 32);
                                    } GL11.glPopMatrix();
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();

                // chest
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0f,0.0f,0f);
                    GL11.glRotatef(-90f, 1, 0, 0 );
                    /*if (ballCount >= 9.5 && loopTime <= 3) {
                        GL11.glRotatef(chestRotation2*1.5f, 0, 0, 1);
                    }
                    if (ballCount <= 2.5) {
                        //if (ballCount >= 9.5 || (ballCount >= 0 && ballCount < 2.5)) {
                        GL11.glRotatef(chestRotation3, 0, 0, 1);
                    }*/
                    cylinder.DrawCylinder(0.65f, 1.75f, 32);

                    // neck
                    GL11.glColor3f(pink[0], pink[1], pink[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0, 0, 1.75f);
                        cylinder.DrawCylinder(0.25f, 0.5f, 32);

                        // head
                        GL11.glPushMatrix();
                        {
                            GL11.glPushMatrix();
                            TexCube Cube = new TexCube();
                            GL11.glTranslatef(0f,0f,1.5f );
                            GL11.glScalef(1f, 1f, 1f);
                            Color.white.bind();
                            texture_cube.bind();
                            GL11.glEnable(GL11.GL_TEXTURE_2D);
                            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
                            Cube.DrawTexCube(texture_cube);
                            GL11.glDisable(GL11.GL_TEXTURE_2D);
                            GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();

                    // left shoulder
                    GL11.glColor3f(red[0], red[1], red[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0,  -0.7f, 1.75f);
                        sphere.DrawSphere(0.4f, 32, 32);

                        // left big arm
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0,  0, 0);
                            GL11.glRotatef(155f, 1, 0, 0);
                            cylinder.DrawCylinder(0.35f, 1f, 32);

                            // left elbow
                            GL11.glColor3f(pink[0], pink[1], pink[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0, 0, 1.1f);
                                sphere.DrawSphere(0.3f, 32, 32);

                                // left small arm
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0,  0, 0);
                                    GL11.glRotatef(90f, 0, 1, 0);
                                    GL11.glRotatef(15f, 1, 0, 0);
                                    GL11.glRotatef(15f, 0, 1, 0);
                                    cylinder.DrawCylinder(0.25f, 1.1f, 32);

                                    // left hand
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0,  0, 1.1f);
                                        sphere.DrawSphere(0.35f, 32, 32);
                                    } GL11.glPopMatrix();
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();

                    // right shoulder
                    GL11.glColor3f(red[0], red[1], red[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0,  0.7f, 1.75f);
                        sphere.DrawSphere(0.4f, 32, 32);

                        // right big arm
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0,  0, 0);
                            GL11.glRotatef(245f, 1, 0, 0);
                            cylinder.DrawCylinder(0.35f, 1f, 32);

                            // right elbow
                            GL11.glColor3f(pink[0], pink[1], pink[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0, 0, 1.1f);
                                sphere.DrawSphere(0.3f, 32, 32);

                                // right small arm
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0,  0, 0);
                                    GL11.glRotatef(30f, 0, 1, 0);
                                    //GL11.glRotatef(15f, 1, 0, 0);
                                    //GL11.glRotatef(15f, 0, 1, 0);
                                    if (ballCount < 2.5) {
                                        GL11.glRotatef(-chestRotation3*2, 0, 1, 0);
                                    }
                                    cylinder.DrawCylinder(0.25f, 1.1f, 32);

                                    // right hand
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0,  0, 1.1f);
                                        sphere.DrawSphere(0.35f, 32, 32);

                                        // racket handler
                                        GL11.glColor3f(white[0], white[1], white[2]);
                                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                        GL11.glPushMatrix();
                                        {
                                            GL11.glTranslatef(0, 0f, -0.25f);
                                            GL11.glRotatef(-15f, -1, 0, 0);
                                            //cylinder.DrawCylinder(0.1f, 1.75f, 32);

                                            // racket bat
                                            GL11.glPushMatrix();
                                            {
                                                GL11.glTranslatef(0, 0f, 2.25f);
                                                GL11.glScalef(0.2f, 1f, 1.5f);
                                                sphere.DrawSphere(0.7f, 32, 32);
                                            } GL11.glPopMatrix();
                                        } GL11.glPopMatrix();

                                        GL11.glColor3f(white[0], white[1], white[2]);
                                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                        GL11.glPushMatrix();
                                        {
                                            GL11.glTranslatef(0, 0f, -0.25f);
                                            GL11.glRotatef(-30f, -1, 0, 0);
                                            cylinder.DrawCylinder(0.1f, 1.75f, 32);
                                        } GL11.glPopMatrix();

                                        GL11.glColor3f(white[0], white[1], white[2]);
                                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                        GL11.glPushMatrix();
                                        {
                                            GL11.glTranslatef(0, 0f, -0.25f);
                                            GL11.glRotatef(0f, -1, 0, 0);
                                            cylinder.DrawCylinder(0.1f, 1.75f, 32);
                                        } GL11.glPopMatrix();
                                    } GL11.glPopMatrix();
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            } GL11.glPopMatrix();

        }
        GL11.glPopMatrix();
    }
}