package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskOptions extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel panel;
    private GridBagConstraints gbc;

    private JButton backButton;
    private JButton addTaskButton;
    private JLabel addTaskLabel;
    private JButton completeTaskButton;
    private JLabel completeTaskLabel;
    private JButton deleteTaskButton;
    private JLabel deleteTaskLabel;
    private JLabel filler;

    public TaskOptions(CardLayout cardLayout, JPanel panel) {
        this.cardLayout = cardLayout;
        this.panel = panel;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        backButton = new JButton("◀");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        backButton.addActionListener(this);
        add(backButton, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        addTaskButton = new JButton("Add Task");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        addTaskButton.addActionListener(this);
        add(addTaskButton, gbc);

        addTaskLabel = new  JLabel("Adding a task allows you to create a task description and set a completion date.");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(addTaskLabel, gbc);

        completeTaskButton = new JButton("Complete Task");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        completeTaskButton.addActionListener(this);
        add(completeTaskButton, gbc);

        completeTaskLabel = new JLabel("Completing a task removes it from the stack and adds to your completion counter!");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(completeTaskLabel, gbc);

        deleteTaskButton = new JButton("Delete Task");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 10, 0);
        deleteTaskButton.addActionListener(this);
        add(deleteTaskButton, gbc);

        deleteTaskLabel = new  JLabel("Deleting a task removes it from the stack.");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(deleteTaskLabel, gbc);

        filler = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weighty = 1.0;
        add(filler, gbc);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(panel, "TaskList Panel");
        }

        if (e.getSource() == addTaskButton){
            AddTask addTask = new AddTask();
            addTask.setVisible(true);
        }

        if (e.getSource() == deleteTaskButton){
            DeleteTask deleteTask = new DeleteTask();
            deleteTask.setVisible(true);
        }

        if (e.getSource() == completeTaskButton){
            CompleteTask completeTask = new CompleteTask();
            completeTask.setVisible(true);
        }


    }
}
