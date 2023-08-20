package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Referee {

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
	
	
	public Referee() {

	}
	
	// Implement using notes  in Animation lecture  
	public void DrawReferee(float delta,boolean GoodAnimation, double ballCount, int loopTime) {
		// I multiply the theta with eight to attain greater frequency of rotating limbs, which matches the speed of the
		// little guy.
		float theta = (float) (delta * 2 * Math.PI) * 8;
		// Apart from limbRotation, declare another thighRotation to imitate more authentic running
		float LimbRotation, thighRotation;

		/*if (GoodAnimation) {
			// limbRotation is for arms and small legs
			LimbRotation = (float) Math.cos(theta) * 45;
			// thighRotation is for big legs, so it should be bigger to embody the movement of knees.
			thighRotation = (float) Math.cos(theta) * 100;
		} else {
			LimbRotation = 0;
			thighRotation = 0;
		}*/
		LimbRotation = 0;
		thighRotation = 0;
		  
		Sphere sphere= new Sphere();
		Cylinder cylinder= new Cylinder();
		Cube cube = new Cube();

		GL11.glPushMatrix();

		{
			// pelvis: a black sphere
			GL11.glColor3f(black[0], black[1], black[2]);
			GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
			GL11.glPushMatrix(); {
				GL11.glTranslatef(0.0f,10.0f,0.0f);
				sphere.DrawSphere(0.5f, 32, 32);
				GL11.glPushMatrix(); {
					//  seat for the referee
					GL11.glColor3f(orange[0], orange[1], orange[2]);
					GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
					GL11.glTranslatef(0.0f,-0.75f,0.25f);
					GL11.glScalef(0.75f, 0.25f, 0.75f);
					cube.DrawCube();
					GL11.glPushMatrix();{
						// seat back
						GL11.glTranslatef(0f, 2f, 1.25f);
						GL11.glScalef(1f, 3f, 0.3f);
						cube.DrawCube();
					} GL11.glPopMatrix();
					// seat pole
					GL11.glTranslatef(0f, 0f, 0.25f);
					GL11.glRotatef(90f,1f, 0f, 0f);
					cylinder.DrawCylinder(0.25f, 28f, 32);
				} GL11.glPopMatrix();
			} GL11.glPopMatrix();



			// chest: a white cylinder
			GL11.glColor3f(white[0], white[1], white[2]);
			GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
			GL11.glPushMatrix(); {
				GL11.glTranslatef(0.0f,10.0f,0.0f);
				// Rotating the cylinder since initially it lies down, we have to let it stand up.
				GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
				cylinder.DrawCylinder(0.6f, 1.75f, 32);


				// neck: a pink cylinder
				GL11.glColor3f(pink[0], pink[1], pink[2]);
				GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
				GL11.glPushMatrix(); {
					GL11.glTranslatef(0.0f,0.0f, 1.25f);
					// I also add a rotation on the neck to increase fun. The rotating direction is always perpendicular
					// to the moving direction, and it has low rotating scope than limbs in case of horror.
					//GL11.glRotatef(LimbRotation/2, 0.0f, 1.0f, 0.0f);
					//                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f);
					cylinder.DrawCylinder(0.25f,1.25f,32);


					// head: a pink sphere
					GL11.glColor3f(pink[0], pink[1], pink[2]);
					GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
					GL11.glPushMatrix(); {
						GL11.glTranslatef(0.0f,0.0f,1.5f);
						sphere.DrawSphere(0.8f, 32, 32);
						GL11.glPopMatrix();


						/*// Here I add some human textures.
						// Add a cube above the head of human
						GL11.glPushMatrix();
						TexCube Cube = new TexCube();
						GL11.glTranslatef(0f,0f,3f );
						//GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);
						GL11.glScalef(0.5f, 0.5f, 0.5f);
						Color.white.bind();
						texture_rubic.bind();
						GL11.glEnable(GL11.GL_TEXTURE_2D);
						GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
						Cube.DrawTexCube(texture_rubic);
						GL11.glDisable(GL11.GL_TEXTURE_2D);
						GL11.glPopMatrix();

						// Add a cube on the left of human
						GL11.glPushMatrix();
						TexCube Cube2 = new TexCube();
						GL11.glTranslatef(2f,0f,0f );
						//GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);
						GL11.glScalef(0.5f, 0.5f, 0.5f);
						Color.white.bind();
						texture_rubic.bind();
						GL11.glEnable(GL11.GL_TEXTURE_2D);
						GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
						Cube2.DrawTexCube(texture_rubic);
						GL11.glDisable(GL11.GL_TEXTURE_2D);
						GL11.glPopMatrix();

						// Add a cube on the right of human
						GL11.glPushMatrix();
						TexCube Cube3 = new TexCube();
						GL11.glTranslatef(-2f,0f,0f );
						//GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);
						GL11.glScalef(0.5f, 0.5f, 0.5f);
						Color.white.bind();
						texture_rubic.bind();
						GL11.glEnable(GL11.GL_TEXTURE_2D);
						GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
						Cube3.DrawTexCube(texture_rubic);
						GL11.glDisable(GL11.GL_TEXTURE_2D);
						GL11.glPopMatrix();*/

						// left ear : a pink cylinder
						GL11.glColor3f(pink[0], pink[1], pink[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.0f,1.5f);
							GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
							cylinder.DrawCylinder(0.20f,0.9f,32);

						} GL11.glPopMatrix();

						// right ear : a pink cylinder
						GL11.glColor3f(pink[0], pink[1], pink[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.0f,1.5f);
							GL11.glRotatef(-90.0f,0.0f,1.0f,0.0f);
							cylinder.DrawCylinder(0.20f,0.9f,32);

						} GL11.glPopMatrix();

						// nose : a pink sphere
						GL11.glColor3f(pink[0], pink[1], pink[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.8f,1.5f);
							sphere.DrawSphere(0.2f, 32, 32);

						} GL11.glPopMatrix();

						// left eye base : a white sphere
						GL11.glColor3f(white[0], white[1], white[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.4f,0.6f,1.8f);
							sphere.DrawSphere(0.3f, 32, 32);

							// left eye sphere : a black sphere
							GL11.glColor3f(black[0], black[1], black[2]);
							GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
							GL11.glPushMatrix(); {
								GL11.glTranslatef(0.0f,0.2f,0.0f);
								sphere.DrawSphere(0.2f, 32, 32);
							} GL11.glPopMatrix();
						} GL11.glPopMatrix();

						// right eye base : a white sphere
						GL11.glColor3f(white[0], white[1], white[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(-0.4f,0.6f,1.8f);
							sphere.DrawSphere(0.3f, 32, 32);

							// right eye sphere : a black sphere
							GL11.glColor3f(black[0], black[1], black[2]);
							GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
							GL11.glPushMatrix(); {
								GL11.glTranslatef(0.0f,0.2f,0.0f);
								sphere.DrawSphere(0.2f, 32, 32);
							} GL11.glPopMatrix();
						} GL11.glPopMatrix();

						// left moustache(designed as mouth initially, but seems much more similar to moustache)
						// : a black cylinder
						GL11.glColor3f(black[0], black[1], black[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.8f,1.25f);
							GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
							cylinder.DrawCylinder(0.2f,0.25f,32);

						} GL11.glPopMatrix();

						// right moustache : a black cylinder
						GL11.glColor3f(black[0], black[1], black[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.8f,1.25f);
							GL11.glRotatef(-90.0f,0.0f,1.0f,0.0f);
							cylinder.DrawCylinder(0.2f,0.25f,32);

						} GL11.glPopMatrix();

					} GL11.glPopMatrix();


					// left shoulder: a white sphere
					GL11.glColor3f(white[0],white[1], white[2]);
					GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
					GL11.glPushMatrix(); {
						GL11.glTranslatef(0.75f,0.0f,1.5f);
						sphere.DrawSphere(0.3f, 32, 32);
		                    

						// left big arm: a white cylinder
						GL11.glColor3f(white[0], white[1], white[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.0f,0.0f);
							// Along the x-axis, inverting the directions
							GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);
							if (ballCount >= 11 && loopTime > 3) {
								GL11.glRotatef(90f, 1.0f, 0, 0);
							}
		                    // The range that left arm rotates is 90 degrees.
							GL11.glRotatef(LimbRotation,1.0f,0.0f,0.0f);
							//   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);   <-- alternative ways
							cylinder.DrawCylinder(0.25f,1f,32);


							// left elbow: a pink sphere
							GL11.glColor3f(pink[0], pink[1], pink[2]);
							GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
							GL11.glPushMatrix(); {
								GL11.glTranslatef(0.0f,0.0f,1f);
								sphere.DrawSphere(0.25f, 32, 32);
		                            
								//left fore-arm: a pink cylinder
								GL11.glColor3f(pink[0], pink[1], pink[2]);
								GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
								GL11.glPushMatrix(); {
									GL11.glTranslatef(0.0f,0.0f,0.0f);
									// To make elbow displays naturally, spinning 90 degrees along the x-axis
									if (ballCount >= 11 && loopTime > 3) {
										GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
									} else {
										GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									}
									//   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.DrawCylinder(0.2f,1.25f,32);

									// left hand: a pink sphere
									GL11.glColor3f(pink[0], pink[1], pink[2]);
									GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
									GL11.glPushMatrix(); {
										GL11.glTranslatef(0.0f,0.0f,1.25f);
										sphere.DrawSphere(0.25f, 32, 32);

									} GL11.glPopMatrix();
								} GL11.glPopMatrix();
							} GL11.glPopMatrix();
						} GL11.glPopMatrix ();
					} GL11.glPopMatrix ();
					// to chest

					// right shoulder: a white sphere
					GL11.glColor3f(white[0],white[1], white[2]);
					GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
					GL11.glPushMatrix(); {
						GL11.glTranslatef(-0.75f,0.0f,1.5f);
						sphere.DrawSphere(0.3f, 32, 32);


						// right big arm: a white cylinder
						GL11.glColor3f(white[0], white[1], white[2]);
						GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.0f,0.0f);
							// Along the x-axis, inverting the directions
							GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);

							// The range that right arm rotates is 90 degrees.
							// It should be inverse to the left big arm to display common person movement.
							GL11.glRotatef(-LimbRotation,1.0f,0.0f,0.0f);
							//   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.DrawCylinder(0.25f,1f,32);


							// right elbow: a pink sphere
							GL11.glColor3f(pink[0], pink[1], pink[2]);
							GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(pink));
							GL11.glPushMatrix(); {
								GL11.glTranslatef(0.0f,0.0f,1f);
								sphere.DrawSphere(0.25f, 32, 32);

								// right fore-arm: a pink cylinder
								GL11.glPushMatrix(); {
									GL11.glTranslatef(0.0f,0.0f,0.0f);
									// To make elbow displays naturally, spinning 90 degrees along the x-axis
									GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
									//   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
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

				// left hip: a black sphere
				GL11.glColor3f(black[0], black[1], black[2]);
				GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
				GL11.glPushMatrix(); {
					GL11.glTranslatef(0.4f,9.8f,0.0f);
					sphere.DrawSphere(0.3f, 32, 32);


					// left high leg: a black cylinder
					GL11.glPushMatrix(); {
						GL11.glTranslatef(0.0f,0.0f,0.0f);
						GL11.glRotatef(90f,1.0f,0.0f,0.0f);
						//GL11.glRotatef(thighRotation,1.0f,0.0f,0.0f);
						// The thighRotation is applied to present different rotating range compared with small legs.
						GL11.glRotatef((-thighRotation/2)+90,1.0f,0.0f,0.0f);
						//   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.DrawCylinder(0.25f,1.0f,32);


						// left knee: a black sphere
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.0f,1.0f);
							GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
							sphere.DrawSphere(0.3f, 32, 32);

							//left low leg: a black cylinder
							GL11.glPushMatrix(); {
								GL11.glTranslatef(0.0f,0.0f,0.0f);
								GL11.glRotatef(-90f,1.0f,0.0f,0.0f);
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

								// left ankle: a white sphere
								GL11.glColor3f(white[0], white[1], white[2]);
								GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
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

				// right hip: a black sphere
				GL11.glColor3f(black[0], black[1], black[2]);
				GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
				GL11.glPushMatrix(); {
					GL11.glTranslatef(-0.4f,9.8f,0.0f);
					sphere.DrawSphere(0.3f, 32, 32);

					// right high leg: a black cylinder
					GL11.glPushMatrix(); {
						GL11.glTranslatef(0.0f,0.0f,0.0f);
						GL11.glRotatef(90f,1.0f,0.0f,0.0f);
						// The thighRotation is applied to present different rotating range compared with small legs.
						GL11.glRotatef(-(-thighRotation/2)+90,1.0f,0.0f,0.0f);
						//   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.DrawCylinder(0.25f,1.0f,32);


						// right knee: a black sphere
						GL11.glPushMatrix(); {
							GL11.glTranslatef(0.0f,0.0f,1.0f);
							GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
							sphere.DrawSphere(0.3f, 32, 32);

							// right low leg: a black cylinder
							GL11.glPushMatrix(); {
								GL11.glTranslatef(0.0f,0.0f,0.0f);
								GL11.glRotatef(-90f,1.0f,0.0f,0.0f);
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

								// right ankle: a white sphere
								GL11.glColor3f(white[0], white[1], white[2]);
								GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
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
	 