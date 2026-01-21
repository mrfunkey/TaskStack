package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompleteTask extends JFrame implements ActionListener {

    JPanel panel;
    GridBagConstraints gbc;
    JLabel selectTaskLabel;
    JComboBox taskComboBox;
    JButton completeButton;
    JLabel taskCompleteLabel;

    public CompleteTask(){
        setSize(400, 200);
        setTitle("Complete Task");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        selectTaskLabel = new JLabel("Select Task to Complete.");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 2, 5);
        panel.add(selectTaskLabel, gbc);

        taskComboBox = logic.Database.createTaskCB();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(2, 5, 10, 5);
        panel.add(taskComboBox, gbc);

        completeButton = new JButton("Complete");
        completeButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 10, 5);
        panel.add(completeButton, gbc);

        add(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedTask = (String) taskComboBox.getSelectedItem();
        String deletionType = "c";
        logic.Database.deleteTask(selectedTask, deletionType);
        logic.Database.loadTasks();
        setVisible(false);
    }
}
