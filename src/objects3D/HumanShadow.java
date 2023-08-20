package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class HumanShadow {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    // primary colours
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    // secondary colours
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
    static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

    // other colours
    static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };


    public HumanShadow() {

    }

    // Implement using notes  in Animation lecture
    public void DrawHumanShadow(float delta,boolean GoodAnimation,
                          float humanW, float humanS, float humanA, float humanD,
                          boolean humanPickAction) {
        // I multiply the theta with eight to attain greater frequency of rotating limbs, which matches the speed of the
        // little guy.
        float theta = (float) (delta * 2 * Math.PI) * 8;
        // Apart from limbRotation, declare another thighRotation to imitate more authentic running
        float LimbRotation, thighRotation;
        if (GoodAnimation) {
            // limbRotation is for arms and small legs
            LimbRotation = (float) Math.cos(theta) * 45;
            // thighRotation is for big legs, so it should be bigger to embody the movement of knees.
            thighRotation = (float) Math.cos(theta) * 100;
        } else {
            LimbRotation = 0;
            thighRotation = 0;
        }

        Sphere sphere= new Sphere();
        Cylinder cylinder= new Cylinder();
        Cube cube = new Cube();

        GL11.glRotatef(90f, 1, 0, 0);
        GL11.glScalef(1f, 1f, 0.2f);
        GL11.glPushMatrix();
        {
            // pelvis: a magenta sphere
            GL11.glColor3f(black[0], black[1], black[2]);
            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
            GL11.glPushMatrix(); {
            GL11.glTranslatef(0.0f,10.0f,0.0f);
            sphere.DrawSphere(0.5f, 32, 32);

            } GL11.glPopMatrix();

            GL11.glPushMatrix(); {
            GL11.glTranslatef(0.0f,10.0f,0.0f);
            // Rotating the cylinder since initially it lies down, we have to let it stand up.
            GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
            // When the human wants to pick up the ball, he should bow
            if (humanPickAction) {
                GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
            }
            cylinder.DrawCylinder(0.6f, 1.75f, 32);

            GL11.glPushMatrix(); {
                GL11.glTranslatef(0.0f,0.0f, 1.25f);
                // I also add a rotation on the neck to increase fun. The rotating direction is always perpendicular
                // to the moving direction, and it has low rotating scope than limbs in case of horror.
                //GL11.glRotatef(LimbRotation/2, 0.0f, 1.0f, 0.0f);
                //                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f);
                cylinder.DrawCylinder(0.25f,1.25f,32);

                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f,1.5f);
                    sphere.DrawSphere(0.8f, 32, 32);
                    GL11.glPopMatrix();

                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,1.5f);
                        GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
                        cylinder.DrawCylinder(0.20f,0.9f,32);

                    } GL11.glPopMatrix();

                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,1.5f);
                        GL11.glRotatef(-90.0f,0.0f,1.0f,0.0f);
                        cylinder.DrawCylinder(0.20f,0.9f,32);

                    } GL11.glPopMatrix();

                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.8f,1.5f);
                        sphere.DrawSphere(0.2f, 32, 32);

                    } GL11.glPopMatrix();

                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.4f,0.6f,1.8f);
                        sphere.DrawSphere(0.3f, 32, 32);

                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.2f,0.0f);
                            sphere.DrawSphere(0.2f, 32, 32);
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();

                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(-0.4f,0.6f,1.8f);
                        sphere.DrawSphere(0.3f, 32, 32);

                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.2f,0.0f);
                            sphere.DrawSphere(0.2f, 32, 32);
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();

                } GL11.glPopMatrix();

                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.75f,0.0f,1.5f);
                    // When the human wants to pick, he should stretch hand
                    if (humanPickAction) {
                        GL11.glRotatef(90f, 1, 0, 0);
                    }
                    sphere.DrawSphere(0.3f, 32, 32);

                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,0.0f);
                        // Along the x-axis, inverting the directions
                        GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);
                        // The range that left arm rotates is 90 degrees.
                        GL11.glRotatef(LimbRotation,1.0f,0.0f,0.0f);
                        //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);   <-- alternative ways
                        cylinder.DrawCylinder(0.25f,1f,32);

                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,1f);
                            sphere.DrawSphere(0.25f, 32, 32);

                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,0.0f);
                                cylinder.DrawCylinder(0.2f,1.25f,32);

                                GL11.glPushMatrix(); {
                                    GL11.glTranslatef(0.0f,0.0f,1.25f);
                                    sphere.DrawSphere(0.25f, 32, 32);

                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix ();
                } GL11.glPopMatrix ();
                // to chest

                GL11.glPushMatrix(); {
                    GL11.glTranslatef(-0.75f,0.0f,1.5f);
                    // When the human wants to pick, he should stretch hand
                    if (humanPickAction) {
                        GL11.glRotatef(90f, 1, 0, 0);
                    }
                    sphere.DrawSphere(0.3f, 32, 32);

                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,0.0f);
                        // Along the x-axis, inverting the directions
                        GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);

                        // The range that right arm rotates is 90 degrees.
                        // It should be inverse to the left big arm to display common person movement.
                        GL11.glRotatef(-LimbRotation,1.0f,0.0f,0.0f);
                        //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);
                        cylinder.DrawCylinder(0.25f,1f,32);

                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,1f);
                            sphere.DrawSphere(0.25f, 32, 32);

                            // right fore-arm: a pink cylinder
                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,0.0f);
                                cylinder.DrawCylinder(0.2f,1.25f,32);

                                // right hand: a pink sphere
                                GL11.glPushMatrix(); {
                                    GL11.glTranslatef(0.0f,0.0f,1.25f);
                                    sphere.DrawSphere(0.25f, 32, 32);

                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix ();
                } GL11.glPopMatrix ();

                // to chest

            } GL11.glPopMatrix();
            // pelvis

            GL11.glPushMatrix(); {
                GL11.glTranslatef(0.4f,9.8f,0.0f);
                sphere.DrawSphere(0.3f, 32, 32);


                // left high leg: a magenta cylinder
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f,0.0f);
                    //GL11.glRotatef(90f,1.0f,0.0f,0.0f);
                    //GL11.glRotatef(thighRotation,1.0f,0.0f,0.0f);
                    // The thighRotation is applied to present different rotating range compared with small legs.
                    GL11.glRotatef((-thighRotation/2)+90,1.0f,0.0f,0.0f);
                    //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                    cylinder.DrawCylinder(0.25f,1.0f,32);


                    // left knee: a magenta sphere
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,1.0f);
                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                        sphere.DrawSphere(0.3f, 32, 32);

                        //left low leg: a magenta cylinder
                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,0.0f);
                            //GL11.glRotatef(-90f,1.0f,0.0f,0.0f);
                            // If we always use limbRotation, the knee will be distorted.
                            // Thus, I use inverse limbRotation to let small leg move naturally, following the
                            // rhythm of big leg.
                            if ( thighRotation < 0 ) {
                                GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            } else {
                                GL11.glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
                            }
                            //GL11.glRotatef((-thighRotation/2)+90,1.0f,0.0f,0.0f);
                            //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                            cylinder.DrawCylinder(0.25f,1.0f,32);

                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,1.0f);
                                sphere.DrawSphere(0.3f, 32, 32);

                                // left foot: a white cylinder
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                                    cylinder.DrawCylinder(0.25f, 0.75f, 32);
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            } GL11.glPopMatrix();

            GL11.glPushMatrix(); {
                GL11.glTranslatef(-0.4f,9.8f,0.0f);
                sphere.DrawSphere(0.3f, 32, 32);

                // right high leg: a magenta cylinder
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f,0.0f);
                    //GL11.glRotatef(90f,1.0f,0.0f,0.0f);
                    // The thighRotation is applied to present different rotating range compared with small legs.
                    GL11.glRotatef(-(-thighRotation/2)+90,1.0f,0.0f,0.0f);
                    //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                    cylinder.DrawCylinder(0.25f,1.0f,32);


                    // right knee: a magenta sphere
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,1.0f);
                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                        sphere.DrawSphere(0.3f, 32, 32);

                        // right low leg: a magenta cylinder
                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,0.0f);
                            //GL11.glRotatef(-90f,1.0f,0.0f,0.0f);
                            // If we always use limbRotation, the knee will be distorted.
                            // Thus, I use inverse limbRotation to let small leg move naturally, following the
                            // rhythm of big leg.
                            if ( thighRotation < 0 ) {
                                GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            } else {
                                GL11.glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
                            }
                            //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                            cylinder.DrawCylinder(0.25f,1.0f,32);

                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,1.0f);
                                sphere.DrawSphere(0.3f, 32, 32);

                                // right foot: a white cylinder
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                                    cylinder.DrawCylinder(0.25f, 0.75f, 32);
                                } GL11.glPopMatrix();
                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            } GL11.glPopMatrix();

        } GL11.glPopMatrix();

        }

    }



}

	/*


}

	*/
