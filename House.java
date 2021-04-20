
import java.util.ArrayList;
import java.util.List;

public class House extends Sprite {

    private int health;

    public List<Missile> missiles;

    public House(int x, int y) {
        super(x, y);
        health = 3;
        initHouse();
    }

    private void initHouse() {
        missiles = new ArrayList<>();
        loadImage("src/images/smallHouse.png");
        getImageDimensions();
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

    }

}