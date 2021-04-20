
import javax.swing.JFrame;

public class TankAttack extends JFrame {

    private static final long serialVersionUID = 1L;
    static SliderWindow slide;

    public TankAttack() {
        UI();
    }

    private void UI() {

        add(new Board(slide.mineValue, slide.aggressionValue, slide.accuracyValue));

        setResizable(false);
        pack();

        setTitle("Assignment 3 - Tank Attack");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        slide = new SliderWindow();
    }
}