package objects3D;

import org.lwjgl.opengl.GL11;

public class Sphere {
	private float x1, x2, x3, x4, y1, y2, y3, y4, z1, z2, inctheta, incphi;
	
	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawSphere(float radius,float nSlices,float nSegments) {
		inctheta = (float) (2.0f * Math.PI) / nSlices;
		incphi = (float) Math.PI / nSegments;

		// GL11.glBegin(GL11.GL_QUADS);
		// Initially, I want to implement by GL_QUADS. However, the final sphere comprised by rectangles owns slight
		// lines which make the sphere surface not perfect enough. Eventually, I decide to use GL_TRIANGLES to create
		// the sphere and luckily, it looks very natural and smooth.
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float theta = -(float) Math.PI; theta < (float) Math.PI; theta += inctheta) {
			for (float phi = -(float) (Math.PI / 2.0f); phi < (float) (Math.PI / 2.0f); phi += incphi) {

				// Here declares the x-coordinates of the four points. To follow an anti-clockwise sequence to draw
				// two small triangles to comprise a rectangle segment.
				// The below rectangle is a small fragment instance.
				/*	(x4, y4, z2, 0)  (x3, y3, z2, 0)
					      ----------------
				    	  |              |
				      	  |              |
				      	  |              |
				          ----------------
					(x1, y1, z1, 0)	 (x2, y2, z1, 0)	*/

				x1 = (float) (radius * Math.cos(phi) * Math.cos(theta));
				x2 = (float) (radius * Math.cos(phi) * Math.cos(theta + inctheta));
				x3 = (float) (radius * Math.cos(phi + incphi) * Math.cos(theta + inctheta));
				x4 = (float) (radius * Math.cos(phi + incphi) * Math.cos(theta));

				y1 = (float) (radius * Math.cos(phi) * Math.sin(theta));
				y2 = (float) (radius * Math.cos(phi) * Math.sin(theta + inctheta));
				y3 = (float) (radius * Math.cos(phi + incphi) * Math.sin(theta + inctheta));
				y4 = (float) (radius * Math.cos(phi + incphi) * Math.sin(theta));

				z1 = (float) (radius * Math.sin(phi));
				z2 = (float) (radius * Math.sin(phi + incphi));


				// Here I use two triangles to comprise a rectangle
				// Method "GL11.glNormal3f" is to attract light radiations onto this point.
				// Method "GL11.glVertex3f" is to signpost where the vertex locates.
				// Drawing the lower-triangle.
				GL11.glNormal3f(x1, y1, z1);
				GL11.glVertex3f(x1, y1, z1);
				GL11.glNormal3f(x2, y2, z1);
				GL11.glVertex3f(x2, y2, z1);
				GL11.glNormal3f(x4, y4, z2);
				GL11.glVertex3f(x4, y4, z2);

				// Drawing the higher-triangle.
				GL11.glNormal3f(x4, y4, z2);
				GL11.glVertex3f(x4, y4, z2);
				GL11.glNormal3f(x2, y2, z1);
				GL11.glVertex3f(x2, y2, z1);
				GL11.glNormal3f(x3, y3, z2);
				GL11.glVertex3f(x3, y3, z2);

			}
		}
		GL11.glEnd();
	}
}

 