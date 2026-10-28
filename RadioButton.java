import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RadioButton extends JPanel implements ActionListener {

    private static final String[] PETS = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
    private final JLabel picture = new JLabel("", SwingConstants.CENTER);

    public RadioButton() {
        super(new BorderLayout());

        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        ButtonGroup group = new ButtonGroup();   // only one button can be selected

        for (String pet : PETS) {
            JRadioButton button = new JRadioButton(pet);
            button.setActionCommand(pet);
            button.addActionListener(this);
            group.add(button);
            radioPanel.add(button);
        }

        picture.setPreferredSize(new Dimension(200, 200));

        add(radioPanel, BorderLayout.LINE_START);
        add(picture, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pet = e.getActionCommand();
        String[] extensions = {".gif", ".png", ".jpg"};
        boolean found = false;

        // Look for images/Pet.gif, images/Pet.png or images/Pet.jpg
        for (String ext : extensions) {
            File file = new File("images/" + pet + ext);
            if (file.exists()) {
                picture.setIcon(new ImageIcon(file.getPath()));
                picture.setText("");
                found = true;
                break;
            }
        }

        // Fallback: show the pet name and print where we looked
        if (!found) {
            picture.setIcon(null);
            picture.setText(pet);
            System.out.println("Image not found. Looking in: "
                    + new File("images").getAbsolutePath());
        }

        // Message box showing the selection
        JOptionPane.showMessageDialog(this, "You selected: " + pet,
                "Your Selection", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("RadioButtonDemo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new RadioButton());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}