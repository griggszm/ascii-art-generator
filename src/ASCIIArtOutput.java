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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Reads a binary image file and displays it as text
 */
public final class ASCIIArtOutput extends JFrame {

    private JTextArea output = new JTextArea();
    private BufferedImage img;

    /**
     * Required constructor. Takes a file, generates ASCII, and displays
     *
     * @param file           Binary image file to read
     * @param reductionScale Amount to downscale picture by.
     * @throws IOException   If file cannot be read
     */
    public ASCIIArtOutput(File file, int reductionScale) throws IOException {
        this.setTitle("Ascii Art Generator");
        this.setSize(1800, 2000);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.add(output);
        //this.setResizable(false);
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 3));
        this.read(file, reductionScale);
        this.setVisible(true);
    }

    /**
     * Reads file and generates ASCII from it
     *
     * @param file           Binary image file to read
     * @param reductionScale Amount to downscale picture by.
     * @throws IOException   If file cannot be read
     */
    private void read(File file, int reductionScale) throws IOException {
        img = ImageIO.read(file);
        int colCount = img.getHeight();
        int rowCount = img.getWidth();
        String[] s = new String[(colCount)+1];
        for(int i = 0; i<colCount; i++) {
            s[i] = "";
        }

        for(int col = 0; col<colCount; col=col+reductionScale) {
            for(int row = 0; row<rowCount; row=row+reductionScale) {
                s[col]=s[col]+colorToLetter(new Color(img.getRGB(row,col)));
            }
        }

        for(String s2 : s) {
            output.setText(output.getText() + s2  + "\r\n");
        }
    }

    /**
     * Converts the specified color to a letter representation
     *
     * @param color Color to get brightness of and convert to letter
     * @return      Letter representation of color
     */
    private char colorToLetter(Color color) {
        double brightness = Math.pow((Math.pow(color.getRed(),2) + Math.pow(color.getGreen(),2)
                + Math.pow(color.getBlue(),2)),0.33);
        if(brightness>50) {
            return(' ');
        } else if(brightness>40) {
            return('.');
        } else if(brightness>30) {
            return ('+');
        } else if(brightness>20) {
            return ('?');
        } else if(brightness>10) {
            return ('#');
        } else  {
            return ('W');
        }
    }

    /**
     * Saves the output to the target file.
     * For best result, should be viewed in font size
     * 1-3, monospaced, plain
     *
     * @param file  File to save to
     */
    public void save(File file) throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "UTF-8"));
        out.write(output.getText());
        System.out.println(output.getText());
        out.flush();
        out.close();
    }
}
