package GraphicsObjects;



public class Vector4f {

	public float x=0;
	public float y=0;
	public float z=0;
	public float a=0;
	
	public Vector4f() 
	{  
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	 
	public Vector4f(float x, float y, float z,float a) 
	{ 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	 //implement Vector plus a Vector 
	public Vector4f PlusVector(Vector4f Additonal) {
		return new Vector4f(this.x+Additonal.x, this.y+Additonal.y, this.z+Additonal.z,this.a+Additonal.a);
	} 
	
	 //implement Vector minus a Vector 
	public Vector4f MinusVector(Vector4f Minus) {
		// In x direction, remove the "Minus" vector from "this" vector to get a new vector coordinate.
		float newX = this.x - Minus.x;
		// In y direction, remove the "Minus" vector from "this" vector to get a new vector coordinate.
		float newY = this.y - Minus.y;
		// In z direction, remove the "Minus" vector from "this" vector to get a new vector coordinate.
		float newZ = this.z - Minus.z;
		// In a direction, remove the "Minus" vector from "this" vector to get a new vector coordinate.
		float newA = this.a - Minus.a;
		// Utilize four new coordinates to compose a new vector and return it.
		return new Vector4f(newX, newY, newZ, newA);
	}
	
	//implement Vector plus a Point 
	public Point4f PlusPoint(Point4f Additonal) {
		// In x direction, add the vector Additonal to the point to get a new point coordinate.
		float newX = this.x + Additonal.x;
		// In y direction, add the vector Additonal to the point to get a new point coordinate.
		float newY = this.y + Additonal.y;
		// In z direction, add the vector Additonal to the point to get a new point coordinate.
		float newZ = this.z + Additonal.z;
		// In a direction, add the vector Additonal to the point to get a new point coordinate.
		float newA = this.a + Additonal.a;
		// Utilize four new coordinates to compose a new point and return it.
		return new Point4f(newX, newY, newZ, newA);
	} 
	//Do not implement Vector minus a Point as it is undefined 
	
	//Implement a Vector * Scalar 
	public Vector4f byScalar(float scale ) {
		// In x direction, multiply the vector for scale times to get a new coordinate.
		float newX = scale * this.x;
		// In y direction, multiply the vector for scale times to get a new coordinate.
		float newY = scale * this.y;
		// In z direction, multiply the vector for scale times to get a new coordinate.
		float newZ = scale * this.z;
		// In a direction, multiply the vector for scale times to get a new coordinate.
		float newA = scale * this.a;
		// Utilize four new coordinates to compose a new vector and return it.
		return new Vector4f(newX, newY, newZ, newA);
	}
	
	//implement returning the negative of a  Vector  
	public Vector4f  NegateVector() {
		// In x direction, utilize the inverse vector of the original vector.
		float newX = -this.x;
		// In y direction, utilize the inverse vector of the original vector.
		float newY = -this.y;
		// In z direction, utilize the inverse vector of the original vector.
		float newZ = -this.z;
		// In a direction, utilize the inverse vector of the original vector.
		float newA = -this.a;
		// Utilize four new coordinates to compose a new vector and return it.
		return new Vector4f(newX, newY, newZ, newA);
	}
	
	//implement getting the length of a Vector  
	public float length() {
		// according to the formula: length = (x^2 + y^2 + z^2 + a^2)^0.5, we calculate the length an return it.
	    return (float) Math.sqrt(x*x + y*y + z*z+ a*a);
	}
	
	//Just to avoid confusion here is getting the Normal  of a Vector  
	public Vector4f Normal() {
		// To attain the normal vector, we have length = (x^2 + y^2 + z^2 + a^2)^0.5
		// which equals length^2 = x^2 + y^2 + z^2 + a^2
		// which equals 1 = (x/length)^2 + (y/length)^2 + (z/length)^2 + (a/length)^2
		// Thus, we use original coordinate divided by length to get new coordinate of the new vector.
		float LengthOfTheVector=  this.length();
		return this.byScalar(1.0f/ LengthOfTheVector); 
	} 
	
	//implement getting the dot product of Vector.Vector  

	public float dot(Vector4f v)
	{
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}
	
	// Implemented this for you to avoid confusion 
	// as we will not normally  be using 4 float vector  
	public Vector4f cross(Vector4f v)  
	{ 
    float u0 = (this.y*v.z - z*v.y);
    float u1 = (z*v.x - x*v.z);
    float u2 = (x*v.y - y*v.x);
    float u3 = 0; //ignoring this for now  
    return new Vector4f(u0,u1,u2,u3);
	}
 
}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */