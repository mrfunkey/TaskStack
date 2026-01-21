package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class TaskList extends JPanel implements ActionListener, ComponentListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;


    private GridBagConstraints gbc;

    private JLabel userTasksLabel;
    private String splashText;

    private int tasksCompleted;
    private JLabel taskCounterLabel;

    private boolean isEmpty;

    private JButton taskOptionsButton;

    private JTable userTasks;
    private JScrollPane userTasksScroll;
    private String[] headers;
    private static DefaultTableModel model;
    DefaultTableCellRenderer centerRenderer;

    public TaskList(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.addComponentListener(this);

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        isEmpty = logic.Database.isDatabaseEmpty();
        if (isEmpty){
            splashText = "You have no stacked tasks.";
        }
        else{
            splashText = "Your stacked tasks are below.";
        }



        userTasksLabel = new JLabel("Welcome to TaskStack! " + splashText);
        userTasksLabel.setFont(userTasksLabel.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0,10,0);
        add(userTasksLabel, gbc);

        tasksCompleted = logic.Database.getTotalCompletedPortal();
        taskCounterLabel = new JLabel("Tasks Completed: " + tasksCompleted);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0,10,0);
        add(taskCounterLabel, gbc);

        taskOptionsButton = new JButton("Task Options");
        taskOptionsButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        add(taskOptionsButton, gbc);

        gbc.weightx = 0.5;
        gbc.gridwidth = 1;

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
        userTasks.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        userTasks.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        userTasks.setRowHeight(30);
        gbc.gridx = 0;
        gbc.gridy = 3;
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

    public void updateCounter() {
        tasksCompleted =  logic.Database.getTotalCompletedPortal();
        taskCounterLabel.setText("Tasks Completed: " + tasksCompleted);
    }

    public void updateSplashText() {
        isEmpty = logic.Database.isDatabaseEmpty();
        if (isEmpty){
            splashText = "You have no stacked tasks.";
        }
        else{
            splashText = "Your stacked tasks are below.";
        }
        userTasksLabel.setText("Welcome to TaskStack! " + splashText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == taskOptionsButton){
            cardLayout.show(cardPanel, "TaskOptions Panel");
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {
        updateCounter();
        updateSplashText();
        logic.Database.loadTasks();
    }

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}
