package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class AthleteOne {

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

    public AthleteOne() {

    }

    public static float chestRotation;

    // Implement using notes  in Animation lecture
    public void DrawAthleteOne(float delta, double ballCount, boolean GoodAnimation, Texture texture_racket) {
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

        // This rotation applies both fore-arm and waist/chest.
        float theta2 = (float) (ballCount * 2 * Math.PI)/5;
        chestRotation = (float) Math.cos(theta2) * 45;


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

                if (ballCount >= 3.7 && ballCount < 6.2) {
                    GL11.glRotatef(chestRotation*1.5f, 0, 1, 0);
                }
                if (ballCount >= 6.2 && ballCount < 8.7) {
                    GL11.glRotatef(chestRotation, 0, 1, 0);
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
                                GL11.glColor3f(blue[0], blue[1], blue[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
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
                                GL11.glColor3f(blue[0], blue[1], blue[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
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

                    /*if (ballCount >= 3.7 && ballCount < 6.2) {
                        GL11.glRotatef(chestRotation*1.5f, 0, 0, 1);
                    }
                    if (ballCount >= 6.2 && ballCount < 8.7) {
                        GL11.glRotatef(chestRotation, 0, 0, 1);
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
                            GL11.glTranslatef(0, 0, 1.0f);
                            sphere.DrawSphere(0.7f, 32, 32);

                            // nose
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.7f, 0, 0);
                                sphere.DrawSphere(0.2f, 32, 32);
                            } GL11.glPopMatrix();

                            // right ear
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0, 0.7f, 0);
                                sphere.DrawSphere(0.15f, 32, 32);
                            } GL11.glPopMatrix();

                            // left ear
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0, -0.7f, 0);
                                sphere.DrawSphere(0.15f, 32, 32);
                            } GL11.glPopMatrix();

                            // right eye base
                            GL11.glColor3f(white[0], white[1], white[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.5f, 0.35f, 0.2f);
                                sphere.DrawSphere(0.2f, 32, 32);

                                // right eyeball
                                GL11.glColor3f(black[0], black[1], black[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.1f, 0f, 0f);
                                    sphere.DrawSphere(0.15f, 32, 32);
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();

                            // left eye base
                            GL11.glColor3f(white[0], white[1], white[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.5f, -0.35f, 0.2f);
                                sphere.DrawSphere(0.2f, 32, 32);

                                // left eyeball
                                GL11.glColor3f(black[0], black[1], black[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.1f, 0f, 0f);
                                    sphere.DrawSphere(0.15f, 32, 32);
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();

                            // hair
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(-0.2f, 0f, 0.6f);
                                GL11.glScalef(0.4f, 0.6f, 0.5f);
                                cube.DrawCube();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();

                    // left shoulder
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
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
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
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
                                    if (ballCount >= 6.2 && ballCount < 8.7) {
                                        GL11.glRotatef(-chestRotation*2, 0, 1, 0);
                                    }
                                    //GL11.glRotatef(15f, 1, 0, 0);
                                    //GL11.glRotatef(15f, 0, 1, 0);
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