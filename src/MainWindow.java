
import java.io.IOException;
import java.nio.FloatBuffer;

import GraphicsObjects.Camera;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private  boolean MouseOnepressed = true;
	private boolean  dragMode = false;
	// At the start, little guy moves in a proper way.
	private boolean BadAnimation = true;
	private boolean Earth = false;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	long  myDelta =0 ; //to use for animation
	float Alpha =0 ; //to use for animation
	long StartTime; // beginAnimiation 

	Arcball MyArcball= new Arcball();
	
	boolean DRAWGRID = true;
	boolean waitForKeyrelease= true;
	/** Mouse movement */
	int LastMouseX = -1 ;
	int LastMouseY= -1;
	
	float pullX = 0.0f; // arc ball  X cord.
	float pullY = 0.0f; // arc ball  Y cord.

	 
	int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project 
	
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

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process

	public static double ballCount = 0;
	public int loopTime = 0;

	// 3 different camera statuses
	private int cameraStatus = 0;
	// cameraCount helps move the camera
	private float parallelCameraCount = 0;
	private float verticalCameraCount = 0;

	// humanWSAD helps the movement of interacting human
	private float humanW = 0;
	private float humanS = 0;
	private float humanA = 0;
	private float humanD = 0;
	// humanRotation helps adjust the orientation of interacting human
	private float humanRotation = 0;
	// to check whether user clicks mouse left to pick
	private boolean humanPickAction = false;
	// store the last moving direction
	private int lastMoveDirection = 0;

	// arrays for storing x, z coordinates to calculate distance
	private float[] humanArray = {0, 0};
	private float[] ballArray = {0, 0};
	// collected ball initial positions
	private float ballDefaultX = 600;
	private float ballDefaultZ = -1200;
	// check whether the ball is being collected
	private boolean ballCollecting = false;
	// check whether the ball is just released by human
	private boolean ballJustCollect = false;

	public void start() {
		
		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		// rotation += 0.01f * delta;
		  
		  
		int MouseX= Mouse.getX();
		int MouseY= Mouse.getY();
		int WheelPostion = Mouse.getDWheel();
	  
		  
		boolean  MouseButonPressed= Mouse.isButtonDown(0);
		  
		 
		  
		if(MouseButonPressed && !MouseOnepressed )
		{
			MouseOnepressed =true;
			//  System.out.println("Mouse drag mode");
			MyArcball.startBall( MouseX, MouseY, 1200, 800);
			dragMode=true;
				
				
		}else if( !MouseButonPressed)
		{
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed =false;
			dragMode=false;
		}
		  
		if(dragMode)
		{
			MyArcball.updateBall( MouseX  , MouseY  , 1200, 800);
			//float xMove = (LastMouseX - MouseX) * 0.05f;
			//float yMove = (LastMouseY - MouseY) * 0.05f;
			//camera.touth(-xMove, yMove);
		}
		  
		if(WheelPostion>0)
		{
			OrthoNumber += 10;
			 
		}
		  
		if(WheelPostion<0)
		{
			OrthoNumber -= 10;
			if( OrthoNumber<610)
			{
				OrthoNumber=610;
			}
			  
			//  System.out.println("Orth nubmer = " +  OrthoNumber);
			  
		}
		  
	  	/** rest key is R*/
	  	if (Keyboard.isKeyDown(Keyboard.KEY_R))
		  	MyArcball.reset();

		// use C to switch between three cameras
		if  (Keyboard.isKeyDown(Keyboard.KEY_C)) {
			if (cameraStatus <= 1) {
				cameraStatus++;
			} else {
				cameraStatus = 0;
			}
		}

		// use Z to move the camera to left
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			if (cameraStatus == 1) {
				if (parallelCameraCount <= 2150) {
					parallelCameraCount += 50;
				}
			}
			if (cameraStatus == 2) {
				if (verticalCameraCount >= -1800) {
					verticalCameraCount -= 50;
				}
			}
		}

		// use X to move the camera to right
		if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
			//x += 0.35f * delta;
			if (cameraStatus == 1) {
				if (parallelCameraCount >= -2150) {
					parallelCameraCount -= 50;
				}
			}
			if (cameraStatus == 2) {
				if (verticalCameraCount <= 1800) {
					verticalCameraCount += 50;
				}
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			humanW += 50;
			humanRotation = 0;
			lastMoveDirection = 1;
			//y += 0.35f * delta;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			humanW -= 50;
			humanRotation = 180;
			lastMoveDirection = 3;
			//y -= 0.35f * delta;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			humanA += 50;
			humanRotation = 90;
			lastMoveDirection = 2;
			//y += 0.35f * delta;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			humanD += 50;
			humanRotation = -90;
			lastMoveDirection = 4;
			//y -= 0.35f * delta;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)
				|| Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			BadAnimation = false;
		} else {
			BadAnimation = true;
		}

		// Click left button of mouse, try to pick up ball on ground
		if (Mouse.isButtonDown(0)) {
			if (!ballCollecting) {
				humanPickAction = true;
				//System.out.println("human "+ humanArray[0] + " " + humanArray[1] );
				//System.out.println("ball "+ ballArray[0] + " " + ballArray[1] );
				double distance = 0.0;
				if (lastMoveDirection == 1) {
					distance = Math.pow(humanArray[0] - ballArray[0], 2) +
							Math.pow(humanArray[1] - 125 - ballArray[1], 2);
				} else if (lastMoveDirection == 3) {
					distance = Math.pow(humanArray[0] - ballArray[0], 2) +
							Math.pow(humanArray[1] + 125 - ballArray[1], 2);
				} else if (lastMoveDirection == 2) {
					distance = Math.pow(humanArray[0] - 125 - ballArray[0], 2) +
							Math.pow(humanArray[1] - ballArray[1], 2);
				} else if (lastMoveDirection == 4) {
					distance = Math.pow(humanArray[0] + 125 - ballArray[0], 2) +
							Math.pow(humanArray[1] - ballArray[1], 2);
				}
				if (Math.sqrt(distance) <= 100) {
					ballCollecting = true;
				}
			}
		} else {
			humanPickAction = false;
		}

		if (Mouse.isButtonDown(1)) {
			if (ballCollecting) {
				ballCollecting = false;
				ballJustCollect = true;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotation += 0.35f * delta;
		/*if (Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			Earth=!Earth;
		} */
		
		if(waitForKeyrelease) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_G))
			{
				DRAWGRID = !DRAWGRID;
				Keyboard.next();
				if(Keyboard.isKeyDown(Keyboard.KEY_G))
				{
					waitForKeyrelease=true;
				}else
				{
					waitForKeyrelease=false;
				}
			}
		}
		 
		/* to check if key is released */
		if(!Keyboard.isKeyDown(Keyboard.KEY_G))
		{
			waitForKeyrelease=true;
		}else
		{
			waitForKeyrelease=false;
		}

		 
			

		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter
		
		LastMouseX= MouseX;
		LastMouseY= MouseY ;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		changeOrth();


		// 12.13 22:38
		//camera.LookAt(-100f, 300f, 300f, -2000f, 2000f, -2000f);
		//GLU.gluPerspective(800f, 1, 0.1f, 10000);
		//camera.LookAt(-20f, 60f, 60f, -2000f, 2000f, -2000f);

		MyArcball.startBall(0, 0, 1200,800);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(10000f).put(1000).put(100).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(500).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(-10000f).put(10000f).put(10000f).put(10000).flip();

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
		//GL11.glEnable(GL11.GL_LIGHT0); // switch light #0 on // I've setup specific materials so in real light it will look abit strange
		// increase light power
		//GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, Utils.ConvertForGL(grey));


		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
											// on
	 	GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
	 	GL11.glEnable(GL11.GL_COLOR_MATERIAL);

		GL11.glEnable(GL11.GL_BLEND);
       	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load in texture
	}

	 

	public void changeOrth() {

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(1200 -  OrthoNumber , OrthoNumber, (800 - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 	
		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);

		//	if(MouseOnepressed)
		//	{

		MyArcball.getMatrix(CurrentMatrix);
		//	}

		GL11.glLoadMatrix(CurrentMatrix);
		 	
	}

	/*
	 * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load 
	 * 
	 */
	public void renderGL() {
		changeOrth();
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glColor3f(0.5f, 0.5f, 1.0f); 
		 
		myDelta =   getTime() - StartTime;
		float delta =((float) myDelta)/10000;
		   
		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		float posn_y  = (float) Math.sin(theta);

		// open camera
		this.cameraOn();
		this.cameraChange();

		// Here I create a new class to draw backgrounds, saving space in main window.
		Base base = new Base();
		base.drawGrid(DRAWGRID, court_grass, net, court_hard, court_clay, texture_wood, texture_ground, sky_left, sky_right);
		
		// Draw a referee
		GL11.glPushMatrix();
		Referee referee = new Referee();
		GL11.glTranslatef(600, 200,1000 );
		GL11.glScalef(90f, 90f,  90f); {

		if(!BadAnimation) {
			// insert your animation code to correct the postion for the human rotating
			// Here I add some proportions to control the moving range radius of the little guy.
			// The GL11.glTranslatef() controls the moving route
			// The GL11.glRotatef() controls the human orientation
//			GL11.glTranslatef(posn_x*12.0f, 0.0f, posn_y*12.0f);
//			GL11.glRotatef(-thetaDeg, 0.0f, 1.0f, 0.0f);
		} else {
			//bad animation  version
			//GL11.glTranslatef(posn_x*3.0f, 0.0f, posn_y*3.0f);
		}

		// Here I draw the referee (man sit in the chair)
		referee.DrawReferee(delta,!BadAnimation, ballCount, loopTime);}
		GL11.glPopMatrix();

		// Draw the interacting human
		GL11.glPushMatrix();
		Human human = new Human();
		humanArray[0] = 600 - humanA + humanD;
		humanArray[1] = -1000 - humanW + humanS;
		GL11.glTranslatef(600 - humanA + humanD, -280, -1000 - humanW + humanS);
		GL11.glRotatef(humanRotation, 0.0f, 1.0f, 0.0f);
		GL11.glScalef(90f, 90f,  90f); {
		human.DrawHuman(delta, !BadAnimation, humanW, humanS, humanA, humanD, humanPickAction);}
		GL11.glPopMatrix();

		// dynamic shadow of the human
		GL11.glPushMatrix();
		HumanShadow humanShadow = new HumanShadow();
		GL11.glTranslatef(600 - humanA + humanD, -280, -1000 - humanW + humanS);
		if (lastMoveDirection == 1 || lastMoveDirection == 0) {
			GL11.glRotatef(45f, 0, 1, 0);
			GL11.glTranslatef(0, 700, -750);
		} else if (lastMoveDirection == 3) {
			GL11.glRotatef(-135f, 0, 1, 0);
			GL11.glTranslatef(0, 700, 750);
		} else if (lastMoveDirection == 2) {
			GL11.glRotatef(-45f, 0, 1, 0);
			GL11.glTranslatef(-750, 700, 0);
		} else if (lastMoveDirection == 4) {
			GL11.glRotatef(135f, 0, 1, 0);
			GL11.glTranslatef(750, 700, 0);
		}
		GL11.glRotatef(humanRotation, 0.0f, 1.0f, 0.0f);
		GL11.glScalef(90f, 90f,  90f); {
			humanShadow.DrawHumanShadow(delta, !BadAnimation, humanW, humanS, humanA, humanD, humanPickAction);}
		GL11.glPopMatrix();

		// Draw an independent ball (ready for picking up)
		GL11.glPushMatrix();
		TexSphere texSphere = new TexSphere();
		// When ball is not being collected
		if (!ballCollecting) { // the ball just stay static
			if (!ballJustCollect) {
				ballArray[0] = ballDefaultX;
				ballArray[1] = ballDefaultZ;
				GL11.glTranslatef(ballDefaultX, 450, ballDefaultZ);
			} else { // The ball is just released by the human
				ballArray[0] = 600 - humanA + humanD;
				ballArray[1] = -1000 - humanW + humanS + 200;
				ballDefaultX = ballArray[0];
				ballDefaultZ = ballArray[1];
				GL11.glTranslatef(ballArray[0], 450, ballArray[1]);
				ballJustCollect = false;
			}
		} else {
			GL11.glTranslatef(600 - humanA + humanD, 1000, -1000 - humanW + humanS);
		}
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		Color.white.bind();
		tennis.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		texSphere.DrawTexSphere(60f, 32, 32, tennis);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			// draw a blue athlete
			AthleteOne athlete1 = new AthleteOne();
			GL11.glTranslatef(-600, -320, 0);
			GL11.glScalef(100f, 100f, 100f);
			athlete1.DrawAthleteOne(delta, ballCount, !BadAnimation, texture_racket);
			GL11.glScalef(0.01f, 0.01f, 0.01f);

			// draw a red athlete
			AthleteCube athlete2 = new AthleteCube();
			GL11.glTranslatef(2400, 0, 0);
			GL11.glRotatef(180f, 0, 1, 0);
			GL11.glScalef(100f, 100f, 100f);
			athlete2.DrawAthleteCube(delta, ballCount, loopTime, !BadAnimation, texture_rubic);
			GL11.glScalef(0.01f, 0.01f, 0.01f);
		} GL11.glPopMatrix();

		//shadow of blue guy
		GL11.glPushMatrix();
		TexCubeSimple Cube = new TexCubeSimple();
		GL11.glTranslatef(-500,410,120 );
		if (ballCount >= 3.7 && ballCount < 6.2) {
			GL11.glRotatef(AthleteOne.chestRotation*1.25f, 0, 1, 0);
		}
		if (ballCount >= 6.2 && ballCount < 8.7) {
			GL11.glRotatef(AthleteOne.chestRotation*0.8f, 0, 1, 0);
		}
		GL11.glScalef(100f, 10f, 250f);
		Color.white.bind();
		shadow.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		Cube.DrawTexCubeSimple(shadow);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();

		//shadow of red guy
		GL11.glPushMatrix();
		GL11.glTranslatef(1900,410,120);
		if (ballCount >= 9.5 && loopTime <= 3) {
			GL11.glRotatef(AthleteCube.chestRotation2*1.25f, 0, 1, 0);
		}
		if (ballCount <= 2.5) {
			//if (ballCount >= 9.5 || (ballCount >= 0 && ballCount < 2.5)) {
			GL11.glRotatef(AthleteCube.chestRotation3*0.8f, 0, 1, 0);
		}
		GL11.glScalef(100f, 10f, 250f);
		Color.white.bind();
		shadow.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		Cube.DrawTexCubeSimple(shadow);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();

		//shadow of net
		GL11.glPushMatrix();
		GL11.glTranslatef(665,400,75 );
		GL11.glScalef(70f, 12f, 900f);
		Color.white.bind();
		shadow.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		Cube.DrawTexCubeSimple(shadow);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();

		// Draw a tennis ball
		drawTennisBall();

		// Draw the camera parallel to audience
		drawCamera();
		
		/*
		 * This code puts the earth code in which is larger than the human so it appears to change the scene 
		 */
		if(Earth)
		{
			//Globe in the centre of the scene 
			GL11.glPushMatrix();
			 TexSphere MyGlobe = new TexSphere();
			//TexCube MyGlobe = new TexCube();
			GL11.glTranslatef(500, 500,500 ); 
			GL11.glScalef(140f, 140f,  140f);
			 
			GL11.glTexParameteri(
					GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
					GL11.GL_CLAMP);
		  
			Color.white.bind();
				texture.bind();
			    GL11.glEnable(GL11.GL_TEXTURE_2D);    
			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);   
	  
		 	MyGlobe.DrawTexSphere(8f, 100, 100, texture); 
			//	MyGlobe.DrawTexCube();
			GL11.glPopMatrix();
		}
		
	}
		  
	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}
	 
	// Declare textures
	Texture texture, texture_rubic, texture_earth, texture_ground, shadow
			, court_hard, court_grass, court_clay, net, tennis, texture_2021, texture_racket
			, texture_wood, texture_polish, sky_left, sky_right;
	 
	/*
	 * Any additional textures for your assignment should be written in here. 
	 * Make a new texture variable for each one so they can be loaded in at the beginning 
	 */
	public void init() throws IOException {
	         
		// Initialize above textures, including moon, rubic, earth, jupiter, ground, plant and soccer.
	  texture_rubic = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/rubic.png"));
	  texture_earth = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
	  texture_ground = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/ground.jpg"));

	  // Tennis textures
	  court_hard = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/court_hard.png"));
	  court_grass = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/court_grass.png"));
	  court_clay = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/court_clay.png"));
	  net = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/net.png"));
	  tennis = TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("res/tennis.jpg"));
	  texture_racket = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/racket.png"));

	  texture_2021 = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/2021.png"));
	  shadow = TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("res/shadow.jpg"));
	  texture_wood = TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("res/wood.jpg"));
	  texture_polish = TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("res/polish.jpg"));

	  sky_left = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/sky_left.png"));
	  sky_right = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/sky_right.png"));

	  System.out.println("Texture loaded okay ");
	}

	// camera init setting
	public void cameraOn() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45f, 1.5f, 2.8f, 20000);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GLU.gluLookAt(-3000, 4000, -3500,
				      1000, 800, -500,
				      0, 1, 0);
	}

	// 3 different camera positions
	public void cameraChange() {
		if (cameraStatus == 0) {
			GL11.glLoadIdentity();
			GLU.gluLookAt(-3000, 4000, -3500,
					      1000, 800, -500,
					      0, 1, 0);
		}
		if (cameraStatus == 1) {
			GL11.glLoadIdentity();
			GLU.gluLookAt(3500f,3000f,0f + parallelCameraCount,
					1000, 800, 0,
					0, 1, 0);
		}
		if (cameraStatus == 2) {
			GL11.glLoadIdentity();
			GLU.gluLookAt(1500f + verticalCameraCount,3000f,2500f,
						  1000, 800, 0,
						  0, 1, 0);
		}
	}

	public void drawTennisBall() {
		// In different areas, tennis ball moves in different directions and speeds.
		// I apply the theory of parabola to make the ball moves more naturally.
		// After 5 rounds, the ball would be kicked into the sky and the match ends.
		// By the way, I add the tennis shadow.
		if (ballCount < 4.2) {
			GL11.glPushMatrix();
			TexSphere texSphere = new TexSphere();
			GL11.glTranslatef((float) (1625f - ballCount*360) , (float) (800f - Math.pow(ballCount, 2)*22.4 ), (float) (390f - ballCount * 130));
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			tennis.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(20f, 32, 32, tennis);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glTranslatef(0, 400f-(float) (800f - Math.pow(ballCount, 2)*22.4 ) ,0);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			shadow.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(25f, 32, 32, shadow);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glPopMatrix();
		} else if (ballCount < 6.2){
			GL11.glPushMatrix();
			TexSphere texSphere = new TexSphere();
			//GL11.glTranslatef((float) (1625f - Math.sqrt(ballCount) * 1.5f), (float) (800f - ballCount * 1.5f), 390f - ballCount * 2);
			GL11.glTranslatef((float) (1625f - 4.1*360 - (ballCount-4.1)*280) , (float) (800f - Math.pow(4.1, 2)*22.4 + (ballCount-4.1)*175 ), (float) (390f - ballCount * 130));
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			tennis.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(20f, 32, 32, tennis);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glTranslatef(0, 400 - (float) (800f - Math.pow(4.1, 2)*22.4 + (ballCount-4.1)*175 ),0);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			shadow.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(25f, 32, 32, shadow);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glPopMatrix();
		} else if (ballCount < 10.2 && loopTime <= 3) {
			GL11.glPushMatrix();
			TexSphere texSphere = new TexSphere();
			//GL11.glTranslatef((float) (1625f - Math.sqrt(ballCount) * 1.5f), (float) (800f - ballCount * 1.5f), 390f - ballCount * 2);
			GL11.glTranslatef((float) (1625f - 4.1*360 - 2*280 + (ballCount-6.1)*370) , (float) (800f - Math.pow(4.1, 2)*22.4 + 2*175 - Math.pow(ballCount-6.1, 2)*21), (float) (390f - 6.1*130 + (ballCount-6.1) * 130));
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			tennis.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(20f, 32, 32, tennis);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glTranslatef(0 , 400 - (float) (800f - Math.pow(4.1, 2)*22.4 + 2*175 - Math.pow(ballCount-6.1, 2)*21), 0);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			shadow.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(20f, 32, 32, shadow);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glPopMatrix();
		} else if (ballCount < 12 && loopTime <= 3) {
			GL11.glPushMatrix();
			TexSphere texSphere = new TexSphere();
			//GL11.glTranslatef((float) (1625f - Math.sqrt(ballCount) * 1.5f), (float) (800f - ballCount * 1.5f), 390f - ballCount * 2);
			GL11.glTranslatef((float) (1625f-4.1*360-2*280+4*370+(ballCount-10.1)*280) , (float) (800f-Math.pow(4.1,2)*22.4+2*175-Math.pow(4,2)*21+(ballCount-10.2)*200), (float) (390f-6.1*130+(ballCount-6.1)*130));
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			tennis.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(20f, 32, 32, tennis);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glTranslatef(0 , 400 - (float) (800f-Math.pow(4.1,2)*22.4+2*175-Math.pow(4,2)*21+(ballCount-10.2)*200), 0);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
			Color.white.bind();
			shadow.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texSphere.DrawTexSphere(20f, 32, 32, shadow);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glPopMatrix();
		} else if (loopTime > 3) {
			if (ballCount >= 6.3) {
				GL11.glPushMatrix();
				TexSphere texSphere = new TexSphere();
				//GL11.glTranslatef((float) (1625f - Math.sqrt(ballCount) * 1.5f), (float) (800f - ballCount * 1.5f), 390f - ballCount * 2);
				GL11.glTranslatef((float) (1625f - 4.1*360 - 2*280 + (ballCount-6.1)*370) , (float) (800f - Math.pow(4.1, 2)*22.4 + 2*175 + (ballCount-6.1)*200), (float) (390f-6.1*130));
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
				Color.white.bind();
				tennis.bind();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				texSphere.DrawTexSphere(20f, 32, 32, tennis);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glPopMatrix();
			}
		} else {
			// common battle (first 6 rounds)
			ballCount = 0;
			loopTime++;
		}
		ballCount += 0.1;

		// restart the match
		if (ballCount > 16) {
			loopTime = 0;
			ballCount = 0;
		}
		//System.out.println("loop" + loopTime);
		//System.out.println("ballcount" + ballCount);
	}

	public void drawCamera() {
		Cube cube = new Cube();
		Sphere sphere = new Sphere();

		// camera body: a black cube
		GL11.glColor3f(black[0], black[1], black[2]);
		GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
		// parallel black camera
		GL11.glPushMatrix(); {
			GL11.glTranslatef(3500f,1900f,0f + parallelCameraCount);
			GL11.glRotatef(20f, 0, 0, 1);
			GL11.glScalef(100f, 100f, 100f);
			cube.DrawCube();

			GL11.glColor3f(white[0], white[1], white[2]);
			GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
			GL11.glPushMatrix(); {
				GL11.glScalef(0.01f, 0.01f, 0.01f);
				GL11.glTranslatef(-100f,0f,0f);
				TexSphere texSphere = new TexSphere();
				Color.white.bind();
				texture_polish.bind();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
				texSphere.DrawTexSphere(60f, 32, 32, texture_polish);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			} GL11.glPopMatrix();
		} GL11.glPopMatrix();

		// vertical white camera
		GL11.glPushMatrix(); {
			GL11.glTranslatef(1500f + verticalCameraCount,1900f,2500f);
			GL11.glRotatef(-20f, 1, 0, 0);
			GL11.glScalef(100f, 100f, 100f);
			cube.DrawCube();

			GL11.glColor3f(white[0], white[1], white[2]);
			GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
			GL11.glPushMatrix(); {
				GL11.glScalef(0.01f, 0.01f, 0.01f);
				GL11.glTranslatef(0f,0f,-100f);
				TexSphere texSphere = new TexSphere();
				Color.white.bind();
				texture_polish.bind();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
				texSphere.DrawTexSphere(60f, 32, 32, texture_polish);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			} GL11.glPopMatrix();
		} GL11.glPopMatrix();
	}
}
