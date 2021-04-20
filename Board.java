import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private static final long serialVersionUID = -624570671055660805L;
    private Timer timer;
    private HouseFire FIRE;

    private Tank tanky;
    private House housey;
    private Mountain mountain1;
    private Mountain mountain2;
    private List<Mine> mines;
    private boolean ingame;

    private final int houseXAxis = 300;
    private final int houseYAxis = 300;

    private final int mountain1XAxis = 60;
    private final int mountain1YAxis = 100;
    private final int mountain2XAxis = 550;
    private final int mountain2YAxis = 650;

    private final int B_WIDTH = 800; // width of board
    private final int B_HEIGHT = 800; // height of board
    private final int DELAY = 15;

    private int mineAmt = 3;
    private int HouseFireDelay = 3000;
    private int accuracy = 0;

    private static int score = 0;

    private static String str; // a string that holds result of game
    String fileName = "highestScores.txt";

    public Board(int mine, int delay, int acc) {
        mineAmt = mine;
        HouseFireDelay = delay;
        accuracy = acc;
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.GREEN);
        ingame = true;
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        housey = new House(houseXAxis, houseYAxis);
        initMinesTankMtn();

        timer = new Timer(DELAY, this);
        timer.start();
        FIRE = new HouseFire(housey, accuracy, tanky, houseYAxis, houseXAxis);
        new java.util.Timer().schedule(FIRE, 0, HouseFireDelay);
    }

    public void initMinesTankMtn() {
        Random rand = new Random();
        mines = new ArrayList<>();
        List<Integer>PlacedX_Axis = new ArrayList<>();
        List<Integer>PlacedY_Axis = new ArrayList<>();
        boolean Accept;

        int X_Axis;
        int Y_Axis;
        
        //putting the 2 mountains
        mountain1 = new Mountain(mountain1XAxis, mountain1YAxis);
        mountain2 = new Mountain(mountain2XAxis, mountain2YAxis);

        //putting all the mines
        for (int i = 0; i < mineAmt; i++) {
            do {
                Accept = true;
                int mineXSize = 0;
                int mineYSize = 0;

                if (mines.size() == 0) {
                    Mine temp = new Mine(0, 0);
                    mineXSize = temp.width;
                    mineYSize = temp.height;
                }

                else {
                    mineXSize = mines.get(0).width;
                    mineYSize = mines.get(0).height;
                }

                //Leaving enough pixels free from right and bottom sides
                X_Axis = rand.nextInt(800 - mineXSize);     //x-axis
                Y_Axis = rand.nextInt(800 - mineYSize);     //y-axis

                //house collision
                if ((X_Axis <= (houseXAxis + housey.width)) && (X_Axis >= (houseXAxis))
                && (Y_Axis <= (houseYAxis + housey.height)) && (Y_Axis >= (houseYAxis)))
                    Accept = false;
                
                //mountain collision
                if (((X_Axis <= (mountain1XAxis + mountain1.width)) && (X_Axis >= (mountain1XAxis))
                && (Y_Axis <= (mountain1YAxis + mountain1.height)) && (Y_Axis >= (mountain1YAxis))) 
                ||
                ((X_Axis <= (mountain2XAxis + mountain2.width)) && (X_Axis >= (mountain2XAxis))
                && (Y_Axis <= (mountain2YAxis + mountain2.height)) && (Y_Axis >= (mountain2YAxis))))
                    Accept = false;

                for (int j = 0; j < PlacedX_Axis.size(); j++)
                if ((X_Axis <= (PlacedX_Axis.get(j) + mines.get(0).width)) && (X_Axis >= (PlacedX_Axis.get(j)))
                && (Y_Axis <= (PlacedY_Axis.get(j) + mines.get(0).height)) && (Y_Axis >= (PlacedY_Axis.get(j))))
                    Accept = false;
            } while(!Accept);

            PlacedX_Axis.add(X_Axis);
            PlacedY_Axis.add(Y_Axis);
            mines.add(new Mine(X_Axis, Y_Axis));
        }

        
        do {
            Accept = true;
            int mineXSize = 0;
            int mineYSize = 0;

            if (mines.size() == 0) {
                Mine temp = new Mine(0, 0);
                mineXSize = temp.width;
                mineYSize = temp.height;
            }

            else {
                mineXSize = mines.get(0).width;
                mineYSize = mines.get(0).height;
            }

            //Leaving enough pixels free from right and bottom sides
            X_Axis = rand.nextInt(800 - mineXSize);     //x-axis
            Y_Axis = rand.nextInt(800 - mineYSize);     //y-axis

            //house collision
            if ((X_Axis <= (houseXAxis + housey.width)) && (X_Axis >= (houseXAxis))
            && (Y_Axis <= (houseYAxis + housey.height)) && (Y_Axis >= (houseYAxis)))
                Accept = false;
            
            //mountain collision
            if (((X_Axis <= (mountain1XAxis + mountain1.width)) && (X_Axis >= (mountain1XAxis))
            && (Y_Axis <= (mountain1YAxis + mountain1.height)) && (Y_Axis >= (mountain1YAxis))) 
            ||
            ((X_Axis <= (mountain2XAxis + mountain2.width)) && (X_Axis >= (mountain2XAxis))
            && (Y_Axis <= (mountain2YAxis + mountain2.height)) && (Y_Axis >= (mountain2YAxis))))
                Accept = false;

            for (int j = 0; j < PlacedX_Axis.size(); j++)
            if ((X_Axis <= (PlacedX_Axis.get(j) + mines.get(0).width)) && (X_Axis >= (PlacedX_Axis.get(j)))
            && (Y_Axis <= (PlacedY_Axis.get(j) + mines.get(0).height)) && (Y_Axis >= (PlacedY_Axis.get(j))))
                Accept = false;
        } while(!Accept);

        PlacedX_Axis.add(X_Axis);
        PlacedY_Axis.add(Y_Axis);
        tanky = new Tank(X_Axis, Y_Axis);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame)
            drawObjects(g);

        else
            drawGameOver(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (tanky.isVisible())
            g.drawImage(tanky.getImage(), tanky.getX(), tanky.getY(), this);

        if (housey.isVisible())
            g.drawImage(housey.getImage(), housey.getX(), housey.getY(), this);
                    
        if (mountain1.isVisible())
            g.drawImage(mountain1.getImage(), mountain1.getX(), mountain1.getY(), this);
                
        if (mountain2.isVisible())
            g.drawImage(mountain2.getImage(), mountain2.getX(), mountain2.getY(), this);

        List<Missile> ms = tanky.getMissiles();
        List<Missile> houseMs = FIRE.getMissiles();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
            }
        }
        
        for (Missile missile : houseMs) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
            }
        }


        for (Mine mine : mines) {
            if (mine.isVisible()) {
                g.drawImage(mine.getImage(), mine.getX(), mine.getY(), this);
            }
        }

    }

    private void drawGameOver(Graphics g) {

        String msg;
        FontMetrics fm;

        //tank won
        if (housey.getHP() == 0) {
            msg = "YOU WON";
            Font small = new Font("Times New Roman", Font.BOLD, 34);
            fm = getFontMetrics(small);

            g.setColor(Color.black);
            g.setFont(small);
        }
        
        else {
            msg = "GAME OVER";
            Font small = new Font("Times New Roman", Font.BOLD, 34);
            fm = getFontMetrics(small);

            g.setColor(Color.RED);
            g.setFont(small);
        }

        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, (B_HEIGHT / 2) - 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        checkCollisions();
        
        updateMissiles();

        if (tanky.missiles.size() == 0)
            updateTank();

        //toMove = true;

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
            appendStrToFile(fileName, str); 
        }
    }
    
    //checks if tank is still on the board and if it is, it can move
    private void updateTank() {

        if (tanky.isVisible()) {
            tanky.move();
        }
    }

    private void updateMissiles() {

        List<Missile> ms = tanky.getMissiles();
        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible())
                m.move();
            else {
                ms.remove(i);
            }
        }
            
        List<Missile> houseMs = FIRE.getMissiles();
        for (int j = 0; j < houseMs.size(); j++) {

            Missile m2 = houseMs.get(j);

            if (m2.isVisible())
                m2.move();
            else {
                houseMs.remove(j);
            }
        }
    }


    public void checkCollisions() {

        Rectangle r3 = tanky.getBounds();
        Rectangle r6 = housey.getBounds();

        Rectangle r4 = mountain1.getBounds();
        Rectangle r5 = mountain2.getBounds();
        if (r3.intersects(r4) || r3.intersects(r5)) { 
            //handled in Tank.java now
        }

        for (Mine mine : mines) {
            
            Rectangle r2 = mine.getBounds();

            if (r3.intersects(r2)) {
                tanky.setVisible(false);
                mine.setVisible(false);
                score -= 3;
                ingame = false;
                str = "Lost by the mines.\n";
            }
        }

        List<Missile> ms = tanky.getMissiles();
        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Mine mine : mines) {

                Rectangle r2 = mine.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                }
            }
            
            //hits mountain = disappears
            if (r1.intersects(r4) || r1.intersects(r5))
                m.setVisible(false);
            
            //hits the house, it disappears and house takes 1 dmg
            else if (r1.intersects(r6)) {
                m.setVisible(false);
                housey.setHP(housey.getHP() - 1);
                score += 1;
                
                if (housey.getHP() == 0)
                {
                    housey.setVisible(false);
                    ingame = false;
                    str = "Won.\n";
                }
            }
        }
        
        List<Missile> houseMs = FIRE.getMissiles();
        for (Missile m2 : houseMs) {
            Rectangle r1 = m2.getBounds();
            
            //hits mountain = disappears
            if (r1.intersects(r4) || r1.intersects(r5))
                m2.setVisible(false);

            //hits the Tank, it disappears and tank takes 1 dmg
            else if (r1.intersects(r3)) {
                m2.setVisible(false);
                tanky.setHP(tanky.getHP() - 1);
                score -= 1;

                if (tanky.getHP() == 0)
                {
                    tanky.setVisible(false);
                    ingame = false;
                    str = "Lost by the house.\n";
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            tanky.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            tanky.keyPressed(e);
        }
    }
    public static int CountLines(String fileName) {
    	int count = 0;
    	try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
				while(line != null){
					count++;
					line = br.readLine();				
				}
			br.close();
        }
        catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error" + e.getMessage());
		}
		return count;
    }
    public static void appendStrToFile(String fileName, String str) 
	{ 
		try { 
		
			BufferedWriter out = new BufferedWriter( 
			new FileWriter(fileName, true)); 
			out.write("Game Number: " + CountLines(fileName)+ "... The score was: " + score + "\t Status of the game: ");
			out.write(str); 
			out.close(); 
		} 
		catch (IOException e) { 
			System.out.println("Exception occoured" + e); 
		} 
	}
}