
public class Mine extends Sprite 
{
	  public Mine(int x, int y) 
	  {
	        super(x, y);
	        placeObject();
	  }
	  
	  public void placeObject()
	  {
	    loadImage("src/images/Mine.png");
	    getImageDimensions();
	  }

}
