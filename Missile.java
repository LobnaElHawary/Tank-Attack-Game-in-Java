
public class Missile extends Sprite 
{
    
    private int direction;

    public Missile(int x, int y, int direction) 
    {
        super(x, y);
        this.direction = direction;
        initMissile();
    }
    
    private void initMissile() 
    {
        loadImage("src/images/smallMissile.png");
        getImageDimensions();
    }

    public void move() {

        //directly above
        if (direction == 0) {
        	y-= 10;
        }

        //then starts moving clockwise throughout the rest
        else if (direction == 1) {
        	x += 10;
        	y -= 10;
        }

        else if (direction == 2) {
        	x += 10;
        }

        else if (direction == 3) {
        	x += 10;
        	y += 10;
        }

        else if (direction == 4) {
        	y += 10;
        }

        else if(direction == 5) {
        	x -= 10;
        	y += 10;
        }

        else if (direction == 6) {
        	x -= 10;
        }
        
        //direction is 7
        else {
            x -= 10;
            y -= 10;
        }
        
        if (x > 780 || y > 780 || y < 0 || x < 0)
        	visible = false;
    }
    
}