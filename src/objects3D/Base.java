package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Base {

    public Base() {}
    public void drawGrid(Boolean DRAWGRID, Texture court_grass, Texture net, Texture court_hard, Texture court_clay
    , Texture texture_wood, Texture ground, Texture sky_left, Texture sky_right) {
        if(DRAWGRID)
        {
            // Ruber Ground
            GL11.glPushMatrix();
            TexCubeSimple Cube = new TexCubeSimple();
            GL11.glTranslatef(600,400,0 );
            GL11.glScalef(5000f, 8f, 5000f);
            Color.white.bind();
            ground.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(ground);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // Sky parallel to audience (back)
            GL11.glPushMatrix();
            GL11.glTranslatef(5600,2900,0 );
            GL11.glRotatef(90, 0, 0, 1);
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glScalef(5000f, 8f, 5000f);
            Color.white.bind();
            sky_left.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(sky_left);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // Sky parallel to audience (front)
            GL11.glPushMatrix();
            GL11.glTranslatef(-4400,2900,0 );
            GL11.glRotatef(90, 0, 0, 1);
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glScalef(5000f, 8f, 5000f);
            Color.white.bind();
            sky_left.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(sky_left);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // Sky vertical to audience (right)
            GL11.glPushMatrix();
            GL11.glTranslatef(600,2900,5000 );
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glScalef(5000f, 8f, 5000f);
            Color.white.bind();
            sky_right.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(sky_right);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // Sky vertical to audience (left)
            GL11.glPushMatrix();
            GL11.glTranslatef(600,2900,-5000 );
            GL11.glRotatef(90, 1, 0, 0);
            //GL11.glRotatef(-90, 0, 1, 0);
            GL11.glScalef(5000f, 8f, 5000f);
            Color.white.bind();
            sky_right.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(sky_right);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // Tennis Court -- Grass
            GL11.glPushMatrix();
            GL11.glTranslatef(600,400,0 );
            GL11.glScalef(1536f, 10f, 1050f);
            Color.white.bind();
            court_grass.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(court_grass);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
            // Tennis Net -- first half
            GL11.glPushMatrix();
            GL11.glTranslatef(597.5f,500,0 );
            GL11.glRotatef(90f, -1, 0, 0);
            GL11.glRotatef(90f, 0, 0, 1);
            GL11.glScalef(900f, 1.25f, 100f);
            Color.white.bind();
            net.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(net);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
            // Tennis Net -- second half
            GL11.glPushMatrix();
            GL11.glTranslatef(602.5f,500,0 );
            GL11.glRotatef(90f, -1, 0, 0);
            GL11.glRotatef(90f, 0, 0, 1);
            GL11.glRotatef(180f, 1, 0, 0);
            GL11.glScalef(900f, 1.25f, 100f);
            Color.white.bind();
            net.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(net);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // Tennis Court -- Hard
            GL11.glPushMatrix();
            GL11.glTranslatef(600,400,2500 );
            GL11.glScalef(1536f, 10f, 1050f);
            Color.white.bind();
            court_hard.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(court_hard);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
            // Tennis Net -- first half
            GL11.glPushMatrix();
            GL11.glTranslatef(597.5f,500,2500 );
            GL11.glRotatef(90f, -1, 0, 0);
            GL11.glRotatef(90f, 0, 0, 1);
            GL11.glScalef(900f, 2.5f, 120f);
            Color.white.bind();
            net.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(net);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
            // Tennis Net -- second half
            GL11.glPushMatrix();
            GL11.glTranslatef(602.5f,500,2500 );
            GL11.glRotatef(90f, -1, 0, 0);
            GL11.glRotatef(90f, 0, 0, 1);
            GL11.glRotatef(180f, 1, 0, 0);
            GL11.glScalef(900f, 2.5f, 120f);
            Color.white.bind();
            net.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(net);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // Tennis Court -- Clay
            GL11.glPushMatrix();
            GL11.glTranslatef(600,400,-2500 );
            GL11.glScalef(1536f, 10f, 1050f);
            Color.white.bind();
            court_clay.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(court_clay);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
            // Tennis Net -- first half
            GL11.glPushMatrix();
            GL11.glTranslatef(597.5f,500,-2500 );
            GL11.glRotatef(90f, -1, 0, 0);
            GL11.glRotatef(90f, 0, 0, 1);
            GL11.glScalef(900f, 2.5f, 120f);
            Color.white.bind();
            net.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(net);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
            // Tennis Net -- second half
            GL11.glPushMatrix();
            GL11.glTranslatef(602.5f,500,-2500 );
            GL11.glRotatef(90f, -1, 0, 0);
            GL11.glRotatef(90f, 0, 0, 1);
            GL11.glRotatef(180f, 1, 0, 0);
            GL11.glScalef(900f, 2.5f, 120f);
            Color.white.bind();
            net.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(net);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // audience seat first
            GL11.glPushMatrix();
            GL11.glTranslatef(3000f,500f,0 );
            GL11.glScalef(500f, 100f, 2500f);
            Color.white.bind();
            texture_wood.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(texture_wood);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // audience seat second
            GL11.glPushMatrix();
            GL11.glTranslatef(3200f,700f,0 );
            GL11.glScalef(300f, 100f, 2500f);
            Color.white.bind();
            texture_wood.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(texture_wood);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // audience seat third
            GL11.glPushMatrix();
            GL11.glTranslatef(3400f,900f,0 );
            GL11.glScalef(100f, 100f, 2500f);
            Color.white.bind();
            texture_wood.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(texture_wood);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // camera cable parallel to audience seat
            GL11.glPushMatrix();
            GL11.glTranslatef(3500f,1900f,0 );
            GL11.glScalef(10f, 10f, 2500f);
            Color.white.bind();
            texture_wood.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(texture_wood);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();

            // camera cable vertical to audience seat
            GL11.glPushMatrix();
            GL11.glTranslatef(1500f,1900f,2500f );
            GL11.glScalef(2000f, 10f, 10f);
            Color.white.bind();
            texture_wood.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
            Cube.DrawTexCubeSimple(texture_wood);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }
    }
}
