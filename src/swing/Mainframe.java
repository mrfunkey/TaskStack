package swing;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ImageIcon icon;
    private Image image;

    public Mainframe(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("TaskStack");
        image = getIcon();
        if (image != null){
            this.setIconImage(image);
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new TaskList(cardLayout, cardPanel), "TaskList Panel");
        cardPanel.add(new TaskOptions(cardLayout, cardPanel), "TaskOptions Panel");

        add(cardPanel);

    }

    public static Image getIcon() {
        URL url = Mainframe.class.getResource("/taskstack_icon.png");
        if (url != null) {
            return new ImageIcon(url).getImage();
        }
        else {
            System.err.println("Couldn't load icon");
        }
        return null;
    }
}
