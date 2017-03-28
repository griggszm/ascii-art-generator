/**
 * SE1021 - 032
 * Winter 2015-2016
 * ASCIIArtOutput
 * Name: Zachary Griggs (griggszm)
 * Created: 1/30/2016
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Reads a binary image file and displays it as text
 */
public class ASCIIArtOutput extends JFrame {

    private JTextArea output = new JTextArea();
    private BufferedImage img;

    /**
     * Required constructor. Takes a file, generates ASCII, and displays
     *
     * @param file          Binary image file to read
     * @throws IOException  If file cannot be read
     */
    public ASCIIArtOutput(File file) throws IOException {
        this.setTitle("Ascii Art Generator");
        this.setSize(800, 900);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(output);
        this.setResizable(false);
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 3));
        this.read(file);
    }

    /**
     * Reads file and generates ASCII from it
     *
     * @param file          Binary image file to read
     * @throws IOException  If file cannot be read
     */
    private void read(File file) throws IOException {
        img = ImageIO.read(file);
        int colCount = img.getHeight();
        int rowCount = img.getWidth();
        int index = 1;
        String[] s = new String[(colCount)+1];
        for(int i = 0; i<colCount; i++) {
            s[i] = "";
        }

        for(int col = 0; col<colCount; col=col+index) {
            for(int row = 0; row<rowCount; row=row+index) {
                s[col]=s[col]+colorToLetter(new Color(img.getRGB(row,col)));
            }
        }

        for(String s2 : s) {
            output.setText(output.getText() + s2  + "\n");
        }
    }

    /**
     * Converts the specified color to a letter representation
     *
     * @param color
     * @return
     */
    private String colorToLetter(Color color) {
        double brightness = Math.pow((Math.pow(color.getRed(),2) + Math.pow(color.getGreen(),2)
                + Math.pow(color.getBlue(),2)),0.33);
        if(brightness>50) {
            return(" ");
        } else if(brightness>40) {
            return(".");
        } else if(brightness>30) {
            return ("+");
        } else if(brightness>20) {
            return ("?");
        } else if(brightness>10) {
            return ("#");
        } else  {
            return ("W");
        }
    }
}
