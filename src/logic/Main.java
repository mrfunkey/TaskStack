package logic;

import swing.Mainframe;

public class Main {
    public static void main(String[] args) {
        Database.connectPortal();

        Mainframe frame = new Mainframe();
        frame.setVisible(true);

        Database.loadTasks();
    }

}