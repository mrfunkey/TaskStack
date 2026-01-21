package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteTask extends JFrame implements ActionListener {

    private JPanel panel;
    private GridBagConstraints gbc;

    private JLabel selectTaskLabel;
    private JComboBox taskComboBox;
    private JButton deleteButton;

    public DeleteTask() {
        setSize(400, 200);
        setTitle("Delete Task");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        selectTaskLabel = new JLabel("Select Task to Delete.");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 2, 5);
        panel.add(selectTaskLabel, gbc);

        taskComboBox = logic.Database.createTaskCB();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(2, 5, 10, 5);
        panel.add(taskComboBox, gbc);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 10, 5);
        panel.add(deleteButton, gbc);


        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedTask = (String) taskComboBox.getSelectedItem();
        String deletionType = "d";
        logic.Database.deleteTask(selectedTask, deletionType);
        logic.Database.loadTasks();
        setVisible(false);

    }
}
