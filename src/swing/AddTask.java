package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTask extends JFrame implements ActionListener {

    private JPanel panel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel addTaskTitle;
    private JLabel taskLabel;
    private JTextField taskNameField;
    private JLabel taskDateLabel;
    private JTextField taskDateField;
    private JButton addTaskButton;

    public AddTask() {
        setSize(400, 200);
        setTitle("Add Task");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());

        addTaskTitle = new JLabel("Add Task");
        addTaskTitle.setFont(addTaskTitle.getFont().deriveFont(Font.BOLD, 15f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,10,5,10);
        panel.add(addTaskTitle, gbc);

        taskLabel = new JLabel("Task:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,10,5,5);
        panel.add(taskLabel,gbc);

        taskNameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,10);
        panel.add(taskNameField,gbc);

        taskDateLabel = new JLabel("Preferred Completion Date:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5,10,5,5);
        panel.add(taskDateLabel,gbc);

        taskDateField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,10);
        panel.add(taskDateField,gbc);

        addTaskButton = new JButton("Add");
        addTaskButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5,5,5,5);
        panel.add(addTaskButton,gbc);

        add(panel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String task =  taskNameField.getText();
        String date = taskDateField.getText();

        logic.ButtonHandler.addTaskButton(task, date);

        setVisible(false);
    }
}
