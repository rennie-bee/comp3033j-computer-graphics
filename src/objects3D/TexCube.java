package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {

	
	public TexCube() {

	}
	
	// Implement using notes  and looking at TexSphere
	public void DrawTexCube(Texture myTexture) {
		 Point4f[] vertices = {  new Point4f(-1.0f, -1.0f, -1.0f, 0.0f),
								 new Point4f(-1.0f, -1.0f, 1.0f, 0.0f),
								 new Point4f(-1.0f, 1.0f, -1.0f, 0.0f),
								 new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
								 new Point4f(1.0f, -1.0f, -1.0f, 0.0f),
								 new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
								 new Point4f(1.0f, 1.0f, -1.0f, 0.0f),
								 new Point4f(1.0f, 1.0f, 1.0f, 0.0f) };

		 int[][] faces = { 	{ 0, 4, 5, 1 },
						    { 0, 2, 6, 4 },
						    { 0, 1, 3, 2 },
						    { 4, 6, 7, 5 },
						    { 1, 5, 7, 3 },
						    { 2, 3, 7, 6 } };



		// The core of texture adding is to spread a stereo object onto a plane.
		// Thus, we have to convert the coordinates from three-dimensional into two-dimensional.
		// Because using loop will lead to same texture on each surface,
		// I split the loop into 6 parts to add different texture sections on each surface.

		// Besides, the usage of GL11.glTexCoord2f() should be elucidated more.
		// After searching some information and contents, I found that the range of its parameters are from zero to one.
		// This, kinds of means the proportion. How much proportion should we cut from the original image and put it
		// on typical surface?
		// According to the image I use in cube texturing, which is rubic.png in res folder, it needs to be cut into
		// four pieces, so I regard 0.25f as a unit and add proportions on it, like 0.0f, 0.25f, 0.5f, 0.75f and 1.0f.
		// With the help of these parameters, I can accurately cut the image into different sections and add them onto
		// typical cube surfaces.

		/* To connect my comments and codes below well, I draw a simple displaying spreading figure.
		      ______
		      |  6 |
		______|____|_________
		|  3  |  5 |  4 |  2 |
		|_____|____|____|____|
		      |  1 |
		      |____|			*/

		// This surface links the bottom.
		GL11.glBegin(GL11.GL_QUADS);
		Vector4f v1 = vertices[faces[0][1]].MinusPoint(vertices[faces[0][0]]);
		Vector4f w1 = vertices[faces[0][3]].MinusPoint(vertices[faces[0][0]]);
		Vector4f normal1 = v1.cross(w1).Normal();
		GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
		// Texture codes on typical four coordinates are modified below to add textures.
		GL11.glTexCoord2f(0.25f,0);
		GL11.glVertex3f(vertices[faces[0][0]].x, vertices[faces[0][0]].y, vertices[faces[0][0]].z);
		GL11.glTexCoord2f(0.5f,0);
		GL11.glVertex3f(vertices[faces[0][1]].x, vertices[faces[0][1]].y, vertices[faces[0][1]].z);
		GL11.glTexCoord2f(0.5f,0.25f);
		GL11.glVertex3f(vertices[faces[0][2]].x, vertices[faces[0][2]].y, vertices[faces[0][2]].z);
		GL11.glTexCoord2f(0.25f,0.25f);
		GL11.glVertex3f(vertices[faces[0][3]].x, vertices[faces[0][3]].y, vertices[faces[0][3]].z);

		// This surface links the back.
		Vector4f v2 = vertices[faces[1][1]].MinusPoint(vertices[faces[1][0]]);
		Vector4f w2 = vertices[faces[1][3]].MinusPoint(vertices[faces[1][0]]);
		Vector4f normal2 = v2.cross(w2).Normal();
		GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
		// Texture codes on typical four coordinates are modified below to add textures.
		GL11.glTexCoord2f(0.75f,0.25f);
		GL11.glVertex3f(vertices[faces[1][0]].x, vertices[faces[1][0]].y, vertices[faces[1][0]].z);
		GL11.glTexCoord2f(1.0f,0.25f);
		GL11.glVertex3f(vertices[faces[1][1]].x, vertices[faces[1][1]].y, vertices[faces[1][1]].z);
		GL11.glTexCoord2f(1.0f,0.5f);
		GL11.glVertex3f(vertices[faces[1][2]].x, vertices[faces[1][2]].y, vertices[faces[1][2]].z);
		GL11.glTexCoord2f(0.75f,0.5f);
		GL11.glVertex3f(vertices[faces[1][3]].x, vertices[faces[1][3]].y, vertices[faces[1][3]].z);

		// This surface links the left.
		Vector4f v3 = vertices[faces[2][1]].MinusPoint(vertices[faces[2][0]]);
		Vector4f w3 = vertices[faces[2][3]].MinusPoint(vertices[faces[2][0]]);
		Vector4f normal3 = v3.cross(w3).Normal();
		GL11.glNormal3f(normal3.x, normal3.y, normal3.z);
		// Texture codes on typical four coordinates are modified below to add textures.
		GL11.glTexCoord2f(0,0.25f);
		GL11.glVertex3f(vertices[faces[2][0]].x, vertices[faces[2][0]].y, vertices[faces[2][0]].z);
		GL11.glTexCoord2f(0.25f,0.25f);
		GL11.glVertex3f(vertices[faces[2][1]].x, vertices[faces[2][1]].y, vertices[faces[2][1]].z);
		GL11.glTexCoord2f(0.25f,0.5f);
		GL11.glVertex3f(vertices[faces[2][2]].x, vertices[faces[2][2]].y, vertices[faces[2][2]].z);
		GL11.glTexCoord2f(0,0.5f);
		GL11.glVertex3f(vertices[faces[2][3]].x, vertices[faces[2][3]].y, vertices[faces[2][3]].z);

		// This surface links the right.
		Vector4f v4 = vertices[faces[3][1]].MinusPoint(vertices[faces[3][0]]);
		Vector4f w4 = vertices[faces[3][3]].MinusPoint(vertices[faces[3][0]]);
		Vector4f normal4 = v4.cross(w4).Normal();
		GL11.glNormal3f(normal4.x, normal4.y, normal4.z);
		// Texture codes on typical four coordinates are modified below to add textures.
		GL11.glTexCoord2f(0.5f,0.25f);
		GL11.glVertex3f(vertices[faces[3][0]].x, vertices[faces[3][0]].y, vertices[faces[3][0]].z);
		GL11.glTexCoord2f(0.75f,0.25f);
		GL11.glVertex3f(vertices[faces[3][1]].x, vertices[faces[3][1]].y, vertices[faces[3][1]].z);
		GL11.glTexCoord2f(0.75f,0.5f);
		GL11.glVertex3f(vertices[faces[3][2]].x, vertices[faces[3][2]].y, vertices[faces[3][2]].z);
		GL11.glTexCoord2f(0.5f,0.5f);
		GL11.glVertex3f(vertices[faces[3][3]].x, vertices[faces[3][3]].y, vertices[faces[3][3]].z);

		// This surface links the front.
		Vector4f v5 = vertices[faces[4][1]].MinusPoint(vertices[faces[4][0]]);
		Vector4f w5 = vertices[faces[4][3]].MinusPoint(vertices[faces[4][0]]);
		Vector4f normal5 = v5.cross(w5).Normal();
		GL11.glNormal3f(normal5.x, normal5.y, normal5.z);
		// Texture codes on typical four coordinates are modified below to add textures.
		GL11.glTexCoord2f(0.25f,0.25f);
		GL11.glVertex3f(vertices[faces[4][0]].x, vertices[faces[4][0]].y, vertices[faces[4][0]].z);
		GL11.glTexCoord2f(0.5f,0.25f);
		GL11.glVertex3f(vertices[faces[4][1]].x, vertices[faces[4][1]].y, vertices[faces[4][1]].z);
		GL11.glTexCoord2f(0.5f,0.5f);
		GL11.glVertex3f(vertices[faces[4][2]].x, vertices[faces[4][2]].y, vertices[faces[4][2]].z);
		GL11.glTexCoord2f(0.25f,0.5f);
		GL11.glVertex3f(vertices[faces[4][3]].x, vertices[faces[4][3]].y, vertices[faces[4][3]].z);

		// This surface links the top.
		Vector4f v6 = vertices[faces[5][1]].MinusPoint(vertices[faces[5][0]]);
		Vector4f w6 = vertices[faces[5][3]].MinusPoint(vertices[faces[5][0]]);
		Vector4f normal6 = v6.cross(w6).Normal();
		GL11.glNormal3f(normal6.x, normal6.y, normal6.z);
		// Texture codes on typical four coordinates are modified below to add textures.
		GL11.glTexCoord2f(0.25f,0.5f);
		GL11.glVertex3f(vertices[faces[5][0]].x, vertices[faces[5][0]].y, vertices[faces[5][0]].z);
		GL11.glTexCoord2f(0.5f,0.5f);
		GL11.glVertex3f(vertices[faces[5][1]].x, vertices[faces[5][1]].y, vertices[faces[5][1]].z);
		GL11.glTexCoord2f(0.5f,0.75f);
		GL11.glVertex3f(vertices[faces[5][2]].x, vertices[faces[5][2]].y, vertices[faces[5][2]].z);
		GL11.glTexCoord2f(0.25f,0.75f);
		GL11.glVertex3f(vertices[faces[5][3]].x, vertices[faces[5][3]].y, vertices[faces[5][3]].z);

		GL11.glEnd();

	}
	
	
	
}

