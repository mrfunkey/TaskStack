package swing;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Mainframe(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("TaskStack");

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new TaskList(cardLayout, cardPanel), "TaskList Panel");
        cardPanel.add(new TaskOptions(cardLayout, cardPanel), "TaskOptions Panel");

        add(cardPanel);

    }
}
