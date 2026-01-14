package swing;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Mainframe(){
        this.setSize(600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new TaskList(), "TaskList Panel");

        add(cardPanel);

    }


}
