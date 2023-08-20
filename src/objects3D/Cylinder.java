package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments ) {

		GL11.glBegin(GL11.GL_TRIANGLES);

		// The work is split into many segments. According to the Radian Measure, we move forward 1 for each step.
		// Along the central axis of the cylinder which cross the center of the circle top and bottom, coordinates
		// of x,y are zero perpetually. With the cos and sin of angle pieces, the point goes along the circle and
		// draw the rectangle.
		for (float i = 0.0f; i < nSegments; i += 1.0f) {
			float angle = (float) (2.0 * Math.PI * i / nSegments);
			float nextAngle = (float) (2.0 * Math.PI * (i + 1) / nSegments);

			float x1 = (float) Math.sin(angle) * radius;
			float y1 = (float) Math.cos(angle) * radius;
			float x2 = (float) Math.sin(nextAngle) * radius;
			float y2 = (float) Math.cos(nextAngle) * radius;

			/*
				A: (0, 0, height, 0)
				B: (x2, y2, height, 0)
				C: (x2, y2, height, 0)
				D: (x2, y2, 0, 0)
				E: (x1, y1, 0, 0)
				F: (0, 0, 0, 0)

					              A
					             /\
					           /    \
					         /  top   \
					       /            \
					    B ---------------- C
				    	  |\              |
				    	  |  \    green   |
				    	  |    \          |
				    	  |      \        |
				    	  | red    \      |
				      	  |          \    |
				      	  |            \  |
				        D ---------------- E
				           \   bottom   /
				             \        /
				               \    /
				                 \/
				                 F             		*/

			// draw the green side triangle
			GL11.glNormal3f(x1, y1, 0.0f);
			GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glNormal3f(x1, y1, 0.0f);
			GL11.glVertex3f(x1, y1, height);
			GL11.glNormal3f(x2, y2, 0.0f);
			GL11.glVertex3f(x2, y2, height);

			// draw the red side triangle
			GL11.glNormal3f(x2, y2, 0.0f);
			GL11.glVertex3f(x2, y2, 0.0f);
			GL11.glNormal3f(x1, y1, 0.0f);
			GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glNormal3f(x2, y2, 0.0f);
			GL11.glVertex3f(x2, y2, height);

			// draw the top triangle
			// Imitating the method to draw side triangles, we draw segment triangles gradually, combining them into
			// the whole peak circle of the cylinder.
			GL11.glNormal3f(0.0f, 0.0f, height);
			GL11.glVertex3f(0.0f, 0.0f, height);
			GL11.glNormal3f(x2, y2, height);
			GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1, height);
			GL11.glVertex3f(x1, y1, height);

			// draw the bottom triangle
			// Imitating the method to draw side triangles, we draw segment triangles gradually, combining them into
			// the whole base circle of the cylinder.
			GL11.glNormal3f(0.0f, 0.0f, -height);
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);
			GL11.glNormal3f(x1, y1, -height);
			GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glNormal3f(x2, y2, -height);
			GL11.glVertex3f(x2, y2, 0.0f);

		}

		GL11.glEnd();
	}
}
