import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * SE2811 - 1
 * Winter 2016-2017
 * ASCIIArtOutput
 * Name: Zachary Griggs (griggszm)
 * Created: 3/27/2017
 */
public class ASCIIArtMainUI {

    private JFrame ui;

    public ASCIIArtMainUI() {
        setWindowConstants();
    }

    private void setWindowConstants() {
        ui = new JFrame();
        ui.setVisible(true);
        ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ui.setSize(600, 400);
    }

    private void addControls() {

    }
}
