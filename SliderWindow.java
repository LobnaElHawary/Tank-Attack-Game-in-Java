import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class SliderWindow
{
	public boolean BoardRun;
	public int mineValue = 0;
	public int aggressionValue = 0;
	public int accuracyValue = 0;

   SliderWindow() 
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
            	SliderTest frame = new SliderTest();
     
                JButton b1 = new JButton();
                frame.setLayout(new BorderLayout());
                frame.setSize(900,200); 
                b1.setVisible(true);
                b1.setText("Set");
                frame.setLayout(new FlowLayout());
                frame.add(b1);
                b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
						if (mineValue == 0)
							mineValue = 3;
						else if (mineValue  == 1)
							mineValue = 5;
						else 
							mineValue = 10;

						if (aggressionValue == 0)
							aggressionValue = 3000;
						else if (aggressionValue == 1)
							aggressionValue = 1500;
						else
							aggressionValue = 500;
							
						frame.dispose();
						
							EventQueue.invokeLater(() -> {
								TankAttack ex = new TankAttack();
								ex.setVisible(true);
						});
                    }
                });
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
               
            }
         });
   }


class SliderTest extends JFrame
{
	private static final long serialVersionUID = -1020563893718121461L;

	public SliderTest()
   {
      setTitle("Input Parameters");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      sliderPanel = new JPanel();
      sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
         

      //ADD AGGRESSIVENESS SLIDER
      JSlider intensitySlider = new JSlider(0, 2, 0);
      intensitySlider.setPaintTicks(true);
      intensitySlider.setSnapToTicks(true);
      intensitySlider.setPaintLabels(true);
      intensitySlider.setMinorTickSpacing(1);
      intensitySlider.setMinorTickSpacing(1);
      addSlider(intensitySlider, "Aggressiveness");
      
      Dictionary<Integer, Component> intensity = new Hashtable<Integer, Component>();
      intensity.put(0, new JLabel("Low"));
      intensity.put(1, new JLabel("Medium"));
      intensity.put(2, new JLabel("High"));
      
      intensitySlider.setLabelTable(intensity);
      
      //intensityListener
      intensitySlider.addChangeListener((ChangeEvent event) -> {
         	aggressionValue = intensitySlider.getValue();
      });
      
      //---------ADD ACCURACY SLIDER-----------
      JSlider accuracySlider = new JSlider(0, 2, 0);
      accuracySlider.setPaintTicks(true);
      accuracySlider.setSnapToTicks(true);
      accuracySlider.setPaintLabels(true);
      accuracySlider.setMinorTickSpacing(1);
      addSlider(accuracySlider, "Accuracy");
      
      Dictionary<Integer, Component> accuracy = new Hashtable<Integer, Component>();
      accuracy.put(0, new JLabel("Low"));
      accuracy.put(1, new JLabel("Medium"));
      accuracy.put(2, new JLabel("High"));
      
      accuracySlider.setLabelTable(accuracy);
      
      //accuracyListener
      accuracySlider.addChangeListener((ChangeEvent event) -> {
		accuracyValue = accuracySlider.getValue();
      });
      
      //ADD MINE SLIDER
      JSlider mineSlider = new JSlider(0, 2, 0);
      mineSlider.setPaintLabels(true);
      mineSlider.setPaintTicks(true);
      mineSlider.setSnapToTicks(true);
      mineSlider.setMajorTickSpacing(1);
      mineSlider.setMinorTickSpacing(1);

      Dictionary<Integer, Component> mine = new Hashtable<Integer, Component>();
      mine.put(0, new JLabel("3"));
      mine.put(1, new JLabel("5"));
      mine.put(2, new JLabel("10"));

      mineSlider.setLabelTable(mine);
      addSlider(mineSlider, "Mines");
      
     //mineListener
      mineSlider.addChangeListener((ChangeEvent event) -> {
		mineValue = mineSlider.getValue();
      });
     
         add(sliderPanel, BorderLayout.CENTER);
   
   }
   
   public void addSlider(JSlider s, String description)
   {
//      s.addChangeListener(listener);
      JPanel panel = new JPanel();
      panel.add(s);
      panel.add(new JLabel(description));
      sliderPanel.add(panel);
   }

   public static final int DEFAULT_WIDTH = 500;
   public static final int DEFAULT_HEIGHT = 200;

   private JPanel sliderPanel;
   public int value;
}
}