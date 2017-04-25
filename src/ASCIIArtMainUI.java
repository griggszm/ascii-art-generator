import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * SE2811 - 1
 * Winter 2016-2017
 * ASCIIArtOutput
 * Name: Zachary Griggs (griggszm)
 * Created: 3/27/2017
 */

/**
 * Class for a UI which asks the user for file options
 */
public final class ASCIIArtMainUI {

    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 300;

    private JFrame ui;
    private JTextField fileInput;
    private JTextField fileOuptut;
    private JTextField reductionScale;
    private JButton browseInputButton;
    private JButton browseOutputButton;
    private JButton generateButton;
    private JButton previewButton;

    private ASCIIArtController controller;

    /**
     * Required constructor. Creates and shows UI.
     *
     * @param controller    Controller for application
     */
    public ASCIIArtMainUI(ASCIIArtController controller) {
        this.controller = controller;
        setWindowConstants();
        createControls();
        addControls();
        addEventHandlers();
        ui.setVisible(true);
    }

    /**
     * Sets up needed window properties
     */
    private void setWindowConstants() {
        ui = new JFrame();
        ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ui.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        ui.setTitle("ASCII Art Generator");
        ui.setLayout(new FlowLayout());
    }

    /**
     * Instantiates controls
     */
    private void createControls() {
        fileInput = new JTextField(20);
        fileOuptut = new JTextField(20);
        reductionScale = new JTextField(5);
        reductionScale.setText("1");
        browseInputButton = new JButton("Browse");
        browseOutputButton = new JButton("Browse");
        generateButton = new JButton("Generate and Save");
        previewButton = new JButton("Preview");
    }

    /**
     * Adds the created controls to the UI
     */
    private void addControls() {
        ui.add(new JLabel("Input: "));
        ui.add(fileInput);
        ui.add(browseInputButton);
        ui.add(new JLabel("Output: "));
        ui.add(fileOuptut);
        ui.add(browseOutputButton);
        ui.add(new JLabel("Scale down by: "));
        ui.add(reductionScale);
        ui.add(generateButton);
        ui.add(previewButton);
    }

    /**
     * Creates event handlers using lambda expressions
     * and adds to controls.
     */
    private void addEventHandlers() {
        browseInputButton.addActionListener(e -> fileInput.setText(controller.browse()));
        browseOutputButton.addActionListener(e -> fileOuptut.setText(controller.browse()));
        generateButton.addActionListener(e -> controller.generate(fileInput.getText(), fileOuptut.getText()));
        previewButton.addActionListener(e -> controller.showOuptut(fileInput.getText()));
    }

    /**
     * Gets the frame encapsulated by this UI
     *
     * @return  JFrame being showed to user
     */
    public JFrame getContentPane() {
        return ui;
    }

    /**
     * Displays an error message to the user
     *
     * @param message   Message to display
     */
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(ui, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int getReductionScale() {
        return Integer.parseInt(reductionScale.getText());
    }
}
