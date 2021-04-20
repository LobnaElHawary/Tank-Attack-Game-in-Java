
public class Mountain extends Sprite 
{
	  public Mountain(int x, int y) 
	  {
	        super(x, y);
	        placeObject();
	  }
	  
	  public void placeObject()
	  {
		loadImage("src/images/Mountain.png");
		getImageDimensions();
	  }

}
