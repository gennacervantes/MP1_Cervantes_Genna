/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.util.ArrayList;

/**
 *
 * @author giogen
 */
public class App {

    private int errorCounter = 0;

    private LoginGUI gui;
    private DBManager dbManager;

    public App() {
        this.gui = new LoginGUI();
        this.dbManager = new DBManager("user", "password");
    }

    public void run() {
        gui.runGUI();

        gui.getLoginBtn().addActionListener(e -> {

            String username = gui.getUsername();
            String password = gui.getPassword();

            String userRole = dbManager.login(gui.getUsername(), gui.getPassword());

            if (userRole == null && errorCounter < 2) {
                gui.displayErrorMessage("Incorrect Username / Password");
                errorCounter += 1;
                System.out.println(errorCounter);

            } else if (userRole != null) {

                errorCounter = 0;

                ArrayList<User> retrievedUsers = dbManager.printRecords();
                ArrayList<String> records = new ArrayList<>();

                for (User u : retrievedUsers) {
                    records.add(u.getEmail() + " " + u.getPassword() + " " + u.getRole());
                }

                if (userRole.equals("Guest")) {

                    gui.displaySuccessfulGuest(records);

                } else if (userRole.equals("Admin")) {

                    gui.displaySuccessfulAdmin(records);
                }

            } else {
                gui.displayFinalErrorMessage("Limit of attempts reached");

            }
        });

        gui.getAddBtn().addActionListener(e -> {

            int success = dbManager.addRecord(gui.getAddEmail(), gui.getAddPassword(), gui.getAddRole());

            if (success > 0) {
                ArrayList<User> retrievedUsers = dbManager.printRecords();
                ArrayList<String> records = new ArrayList<>();

                for (User u : retrievedUsers) {
                    records.add(u.getEmail() + " " + u.getPassword() + " " + u.getRole());
                }

                gui.refreshRecords(records);
            }
        });

        gui.getEditBtn().addActionListener(e -> {

            int success = dbManager.editRecord(gui.getEditPassword(), gui.getEditRole(), gui.getEditEmail());

            if (success > 0) {
                ArrayList<User> retrievedUsers = dbManager.printRecords();
                ArrayList<String> records = new ArrayList<>();

                for (User u : retrievedUsers) {
                    records.add(u.getEmail() + " " + u.getPassword() + " " + u.getRole());
                }
                gui.refreshRecords(records);
            }

        });

        gui.getDeleteBtn().addActionListener(e -> {

            int success = dbManager.deleteRecord(gui.getDeleteEmail());

            if (success > 0) {
                ArrayList<User> retrievedUsers = dbManager.printRecords();
                ArrayList<String> records = new ArrayList<>();

                for (User u : retrievedUsers) {
                    records.add(u.getEmail() + " " + u.getPassword() + " " + u.getRole());
                }
                gui.refreshRecords(records);
            }
        });

    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
