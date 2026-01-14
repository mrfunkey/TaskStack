package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskList extends JPanel implements ActionListener {

    private GridBagConstraints gbc;

    private JLabel userTasksLabel;
    private JButton addTaskButton;
    private JButton removeTaskButton;

    private JTable userTasks;
    private JScrollPane userTasksScroll;
    private String[] headers;
    private static DefaultTableModel model;
    DefaultTableCellRenderer centerRenderer;

    public TaskList(){
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();


        userTasksLabel = new JLabel("Welcome to TaskStack! Your stacked tasks are below.");
        userTasksLabel.setFont(userTasksLabel.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0,10,0);
        add(userTasksLabel, gbc);

        addTaskButton = new JButton("Add Task");
        gbc.gridx = 0;
        gbc.gridy = 1;
        addTaskButton.addActionListener(this);
        add(addTaskButton, gbc);

        headers = new String[]{"Tasks", "Completion Date"};
        model =  new DefaultTableModel(headers, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        userTasks = new JTable(model);
        userTasks.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;

        userTasksScroll = new JScrollPane(userTasks);
        add(userTasksScroll, gbc);

    }

    public static void addTask(String task, String date){
        Object[] newRowData =  {task, date};
        model.addRow(newRowData);
    }

    public static void modelRowReset(){
        model.setRowCount(0);
    }

    public static void modelRowUpdate(String task, String date){
        Object[] newRowData =  {task, date};
        model.addRow(newRowData);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTaskButton){
            AddTask addTask = new AddTask();
            addTask.setVisible(true);
        }
    }
}
