
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Tank extends Sprite {

    private int dy;

    public int direction;
    private int health;

    private int tempMissileX;
    private int tempMissileY;
    public List<Missile> missiles;
    public int missilesDestroyed;

    public Tank(int x, int y) {
        super(x, y);
        health = 3;
        direction = 0;
        missilesDestroyed = 0;
        initTank();
    }

    private void initTank() {
        missiles = new ArrayList<>();
        loadImage("src/images/Tank.png");
        getImageDimensions();
    }

    public void move() {

        int tempX = x;
        int tempY = y;

        //don't move
        if (missilesDestroyed != missiles.size())
            dy = 0;

        y += dy;


        //house
        if (x > 300  && x < (300 + 245)
            && y > 300 && y < (300 + 103)) {
            x = tempX;
            y = tempY;
        }

        //mountain 1
        if (x > 60  && x < (60 + 120)
            && y > 100 && y < (100 + 50)) {
            x = tempX;
            y = tempY;
        }

        //mountain 2
        if (x > 550 && x < (550 + 120)
            && y > 650 && y < (650 + 50)) {
            x = tempX;
            y = tempY;
        }
        
        /* Out of Bounds */ 
        if (x < 1) 
            x = 1;
        
        else if (x > 800 - height)
            x = 800 - height;

        if (y < 1) 
            y = 1;
            
        else if (y > 800 - height)
            y = 800 - height;
        /* Out of Bounds */ 
        
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public int getHP() {
        return health;
    }

    public void setHP(int HP) {
        health = HP;
    }


    public void fire() {

        tempMissileX = x + (width / 4);
        tempMissileY = y + (height / 4);
        
        missiles.add(new Missile(tempMissileX, tempMissileY, direction));
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) 
            fire();
            
        if (key == KeyEvent.VK_LEFT) 
            direction = (direction + 7) % 8;

        if (key == KeyEvent.VK_RIGHT) 
            direction = (direction + 1) % 8;

        if (key == KeyEvent.VK_UP) 
            dy = -1;

        if (key == KeyEvent.VK_DOWN) 
            dy = 1;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            direction = direction % 8;
        }

        if (key == KeyEvent.VK_RIGHT) {
            direction = direction % 8;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}