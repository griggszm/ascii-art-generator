import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * SE2811 - 1
 * Winter 2016-2017
 * ASCIIArtOutput
 * Name: Zachary Griggs (griggszm)
 * Created: 3/27/2017
 */

/**
 * Controller for the application. Contains
 * the main method.
 */
public class ASCIIArtController {

    private ASCIIArtMainUI ui;

    /**
     * Required constructor. Creates new controller and UI.
     */
    public ASCIIArtController() {
        ui = new ASCIIArtMainUI(this);
    }

    /**
     * Asks user to select a file
     *
     * @return  File path if selected; empty string if canceled.
     */
    public String browse() {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        chooser.showDialog(ui.getContentPane(),"Select");
        String selected = "";
        if(chooser.getSelectedFile()!=null ) {
            selected = chooser.getSelectedFile().getAbsolutePath();
        }
        return selected;
    }

    /**
     * Shows output for input file; does not save output
     *
     * @param in    File path to input
     */
    public void showOuptut(String in) {
        try {
            new ASCIIArtOutput(new File(in),ui.getReductionScale());
        } catch (IOException ex) {
            ui.displayErrorMessage("Cannot open file: " + in);
        } catch (NullPointerException ex) {
            ui.displayErrorMessage("Not an image file");
        } catch (NumberFormatException ex) {
            ui.displayErrorMessage("Scale down must be a whole, positive number");
        }
    }

    /**
     * Shows output and saves file.
     *
     * @param in    File path to input
     * @param out   File path to save to
     */
    public void generate(String in, String out) {
        try {
            File inputFile = new File(in);
            ASCIIArtOutput display = new ASCIIArtOutput(inputFile,ui.getReductionScale());
            if(!out.equals("")) {
                File outputFile = new File(out);
                display.save(outputFile);
            }
        } catch (IOException ex) {
            ui.displayErrorMessage("Cannot open file: " + in);
        }  catch (NullPointerException ex) {
            ui.displayErrorMessage("Not an image file");
        } catch (NumberFormatException ex) {
            ui.displayErrorMessage("Scale down must be a whole, positive number");
        }
    }

    /**
     * Main method; creates controller.
     *
     * @param args  Ignored
     */
    public static void main(String[] args) {
        new ASCIIArtController();
    }
}
