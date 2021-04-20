import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class HouseFire extends TimerTask 
{
	private House currHouse;
	private Tank currTank;

	private int fireAcc;
	
    private int tempMissileX;
	private int tempMissileY;
	
	private int height;
	private int width;
	private int y;
	private int x;

	private int calcY;
	private int calcX;
	
	private int direction;
	public List<Missile> missiles;
	
	Random randDirc = new Random();

	public HouseFire(House H, int acc, Tank T, int currHouseHeight, int currHouseWidth) 
	{
		currHouse = H;
		currTank = T;
		
		fireAcc = acc;

		height = currHouseHeight;
		width = currHouseWidth;

		y = currHouse.y;
		x = currHouse.x;

		missiles = new ArrayList<>();
	}

    public List<Missile> getMissiles() {
        return missiles;
    }

	public void accurateShots() {

		int compare = 50;

		//worst accuracy
		if (fireAcc == 0)
			direction = randDirc.nextInt(7);

		else if	(fireAcc == 1) {
			calcX = currTank.x - x;
			calcY = currTank.y - y;

			//above it
			if ((calcX <= compare && calcX >= -compare) && calcY < -compare)
				direction = 0 + (-1 + randDirc.nextInt(1));

			//above to the right, Top-Right
			else if ((calcX >= compare) && calcY < -compare)
				direction = 1 + (-1 + randDirc.nextInt(1));
				
			//To the right
			else if ((calcX >= compare) && (calcY >= -compare && calcY < compare))
				direction = 2 + (-1 + randDirc.nextInt(1));

			//Below to the right, Bottom-Right
			else if ((calcX >= compare) && (calcY > compare))
				direction = 3 + (-1 + randDirc.nextInt(1));

			//below it 
			else if ((calcX <= compare && calcX >= -compare) && (calcY > compare))
				direction = 4 + (-1 + randDirc.nextInt(1));

				
			//Below to the left, Bottom-Left
			else if ((calcX <= -compare) && (calcY > compare))
				direction = 5 + (-1 + randDirc.nextInt(1));

			//To the left
			else if ((calcX <= -compare)	&& (calcY >= -compare && calcY < compare))
				direction = 6 + (-1 + randDirc.nextInt(1));

			
			//Above to the left, Top-Left
			else if ((calcX <= -compare) && (calcY < -compare))
				direction = 7 + (-1 + randDirc.nextInt(1));	
		}
		
		else if	(fireAcc == 2) {
			calcX = currTank.x - x;
			calcY = currTank.y - y;

			//above it
			if ((calcX <= 50 && calcX >= -50) && calcY < -50)
				direction = 0;

			//above to the right, Top-Right
			else if ((calcX >= compare) && calcY < -compare)
				direction = 1;
				
			//To the right
			else if ((calcX >= compare) && (calcY >= -compare && calcY < compare))
				direction = 2;

			//Below to the right, Bottom-Right
			else if ((calcX >= compare) && (calcY > compare))
				direction = 3;

			//below it 
			else if ((calcX <= compare && calcX >= -compare) && (calcY > compare))
				direction = 4;

				
			//Below to the left, Bottom-Left
			else if ((calcX <= -compare) && (calcY > compare))
				direction = 5;

			//To the left
			else if ((calcX <= -compare)	&& (calcY >= -compare && calcY < compare))
				direction = 6;

			
			//Above to the left, Top-Left
			else if ((calcX <= -compare) && (calcY < -compare))
				direction = 7;	
		}
	}

	@Override
	public void run() {
		
		accurateShots();

		tempMissileX = x + (width / 4);
		tempMissileY = y + (height / 4);

        missiles.add(new Missile(tempMissileX, tempMissileY, direction));
	}

}
