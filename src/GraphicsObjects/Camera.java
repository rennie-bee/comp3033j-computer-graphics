package GraphicsObjects;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Camera {
	public Vector4f position;
	public Vector4f dir;
	
	float yAngle;
	float pitchAngle;
	float speed;
	
	public Camera()
	{
		position = new Vector4f(0.0f, 0.0f, 0.0f, 0.0f);
		dir = new Vector4f(0.0f, 0.0f, -1.0f, 0.0f);
		yAngle = 0.0f;
		pitchAngle = 0.0f;
		speed = 10.0f;
	}
	
	public void update(float delay)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			moveLeft(delay);
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			moveRight(delay);
		}
	
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			forward(delay);
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			backward(delay);
		}
	
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			turnLeft();
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			turnRight();
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += speed * delay;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= speed * delay;
		}
	}
	
	public void touth(float x, float y)
	{
		pitchAngle += y;
		yAngle += x;
		
		computeDir();
	}
	
	public void setView()
	{
		GL11.glRotatef(pitchAngle, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(yAngle, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(-position.x, -position.y, -position.z);
	}
	
	public void LookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ)
	{
		position = new Vector4f(eyeX, eyeY, eyeZ, 0.0f);
		dir = new Vector4f(centerX, centerY, centerZ, 0.0f);
		//dir = dir.MinusVector(position);
		dir = position.MinusVector(dir);
		dir = dir.Normal();
		
		pitchAngle = (float)Math.toDegrees(Math.asin(dir.y));
		yAngle = (float)Math.toDegrees(Math.atan2(dir.z,  dir.x)) - 90.0f;
	}
	
	public void forward(float delay)
	{
		float n = speed * delay;
		Vector4f v = dir.byScalar(n);
		position = position.PlusVector(v);
	}
	
	public void backward(float delay)
	{
		float n = speed * delay;
		Vector4f v = dir.byScalar(n);
		position = position.MinusVector(v);
	}
	
	public void moveLeft(float delay)
	{
		float n = speed * delay;
		Vector4f v = dir.byScalar(n);
		position = position.PlusVector(new Vector4f(v.z, 0.0f, -v.x, 0.0f));
	}
	
	public void moveRight(float delay)
	{
		float n = speed * delay;
		Vector4f v = dir.byScalar(n);
		position = position.PlusVector(new Vector4f(-v.z, 0.0f, v.x, 0.0f));
	}
	
	public void turnLeft()
	{
		yAngle -= 1.0f;
		computeDir();
	}
	
	public void turnRight()
	{
		yAngle += 1.0f;
		computeDir();
	}
	
	public void lookUp()
	{
		pitchAngle += 1.0f;
		computeDir();
	}
	
	public void lookDown()
	{
		pitchAngle -= 1.0f;
		computeDir();
	}

	void computeDir()
	{
		double ax = Math.toRadians(pitchAngle);
		double ay = Math.toRadians(yAngle);
		
		double sinX = Math.sin(ax);
		double sinY = Math.sin(ay);
		double cosX = Math.cos(ax);
		double cosY = Math.cos(ay);
		
		dir.x = (float)(speed * cosX * sinY);
		dir.y = (float)(speed * -sinX);
		dir.z = (float)(speed * cosX * -cosY);
		dir = dir.Normal();
	}
}
