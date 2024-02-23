/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author giogen
 */
public class LoginGUI implements ActionListener {

    private String[] roles = {"Guest", "Admin"};

    // for styling
    private String hexBG = "#393E41";
    private String hexFG = "#FFF6ED";
    private String hexAdd = "#6A8D73";
    private String hexEdit = "#989FCE";
    private String hexDelete = "#DD2D4A";
    private Color bgColor = Color.decode(hexBG);
    private Color fgColor = Color.decode(hexFG);
    private Color addColor = Color.decode(hexAdd);
    private Color editColor = Color.decode(hexEdit);
    private Color deleteColor = Color.decode(hexDelete);
    
    private Font appFont = new Font("Courier New", Font.BOLD, 30);

    // login
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JPanel usernamePanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JPanel marginPanel = new JPanel();
    private JLabel userLabel = new JLabel("User: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton loginBtn = new JButton("Log In");

    // admin
    JFrame adminFrame = new JFrame();

    // add records
    private JButton addBtn = new JButton("Add");
    private JLabel addEmailLabel = new JLabel("Email:");
    private JLabel addPasswordLabel = new JLabel("Password:");
    private JLabel addRoleLabel = new JLabel("Role:");
    private JTextField addEmailField = new JTextField(30);
    private JTextField addPasswordField = new JTextField(30);
    private JComboBox<String> addRoleField = new JComboBox<>(roles);

    // edit records
    private JButton editBtn = new JButton("Edit");
    private JLabel editEmailLabel = new JLabel("Email: ");
    private JLabel editPasswordLabel = new JLabel("Password: ");
    private JLabel editRoleLabel = new JLabel("Role: ");
    private JComboBox<String> editEmailField;
    private JTextField editPasswordField = new JTextField(30);
    private JComboBox<String> editRoleField = new JComboBox<>(roles);

    // delete records
    private JButton deleteBtn = new JButton("Delete");
    private JLabel deleteEmailLabel = new JLabel("Email: ");
    private JComboBox<String> deleteEmailField;


    public LoginGUI() {

        frame.setTitle("Login Page");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        usernamePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 0));
        passwordPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 0));
        marginPanel.setPreferredSize(new Dimension(1000, 30));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));

        userLabel.setFont(appFont);
        passwordLabel.setFont(appFont);
        userLabel.setPreferredSize(new Dimension(200, 50));
        passwordLabel.setPreferredSize(new Dimension(200, 50));

        usernameField.setPreferredSize(new Dimension(500, 50));
        usernameField.setBackground(fgColor);
        usernameField.setForeground(bgColor);
        passwordField.setPreferredSize(new Dimension(500, 50));
        passwordField.setBackground(fgColor);
        passwordField.setForeground(bgColor);
        usernameField.setFont(appFont);
        passwordField.setFont(appFont);

        loginBtn.setPreferredSize(new Dimension(300, 50));
        loginBtn.setFont(appFont);
        loginBtn.setBackground(bgColor);
        loginBtn.setForeground(fgColor);

        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        panel.add(marginPanel);
        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(loginBtn);

    }

    public void displayErrorMessage(String errorMessage) {

        JLabel label = new JLabel(errorMessage, SwingConstants.CENTER);

        label.setPreferredSize(new Dimension(600, 250));
        label.setFont(appFont);

        JOptionPane.showMessageDialog(frame, label, "Error", JOptionPane.ERROR_MESSAGE);

    }

    public void displayFinalErrorMessage(String errorMessage) {
        JLabel label = new JLabel(errorMessage, SwingConstants.CENTER);

        label.setPreferredSize(new Dimension(600, 250));
        label.setFont(appFont);

        Object[] options = {"Close Application"};
        int option = JOptionPane.showOptionDialog(frame, label, "Final Error", JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        if (option == 0) {
            System.exit(0);
        }
    }

    public void displaySuccessfulGuest(ArrayList<String> records) {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %-20s %-20s\n", "USERNAME", "PASSWORD", "ROLE"));

        for (String record : records) {

            String[] columns = record.split(" ");

            sb.append(String.format("%-20s %-20s %-20s\n", columns[0], columns[1], columns[2]));
        }

        frame.setVisible(false);

        JFrame guestFrame = new JFrame();
        guestFrame.setTitle("Guest");
        guestFrame.setSize(1200, 700);
        guestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        JPanel marginPanel = new JPanel();
        JButton logoutBtn = new JButton("Logout");

        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guestFrame.dispose();
                frame.setVisible(true);
            }
        });

        textArea.setText(sb.toString());
        textArea.setRows(10);
        textArea.setColumns(60);
        textArea.setFont(appFont);
        textArea.setBackground(fgColor);
        textArea.setForeground(bgColor);

        marginPanel.setPreferredSize(new Dimension(1200, 30));

        logoutBtn.setPreferredSize(new Dimension(300, 50));
        logoutBtn.setFont(appFont);
        logoutBtn.setBackground(bgColor);
        logoutBtn.setForeground(fgColor);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
        panel.add(marginPanel);
        panel.add(textArea);
        panel.add(logoutBtn);

        guestFrame.add(panel);

        guestFrame.setVisible(true);

    }

    public void displaySuccessfulAdmin(ArrayList<String> records) {
        
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %-20s %-20s\n", "Username", "Password", "Role"));

        for (String record : records) {

            String[] columns = record.split(" ");

            sb.append(String.format("%-20s %-20s %-20s\n", columns[0], columns[1], columns[2]));
        }

        frame.setVisible(false);

        adminFrame.setSize(1400, 700);
        adminFrame.setTitle("Admin");
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        JPanel marginPanel = new JPanel();
        JButton logoutBtn = new JButton("Logout");
        JButton addRecordBtn = new JButton("Add");
        JButton editRecordBtn = new JButton("Edit");
        JButton deleteRecordBtn = new JButton("Delete");

        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();
                frame.setVisible(true);
            }
        });

        addRecordBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAddRecord();

            }
        });

        ArrayList<String> emails = new ArrayList<>();
        for (String record : records) {
            String[] parts = record.split("\\s+");

            if (parts.length > 0) {
                emails.add(parts[0]);
            }
        }

        editRecordBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayEditRecord(emails);
            }
        });

        deleteRecordBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayDeleteRecord(emails);
            }
        });

        marginPanel.setPreferredSize(new Dimension(1200, 30));

        textArea.setText(sb.toString());
        textArea.setRows(10);
        textArea.setColumns(50);
        textArea.setFont(appFont);
        textArea.setBackground(fgColor);
        textArea.setForeground(bgColor);

        logoutBtn.setPreferredSize(new Dimension(300, 50));
        logoutBtn.setFont(appFont);
        logoutBtn.setBackground(bgColor);
        logoutBtn.setForeground(fgColor);

        addRecordBtn.setPreferredSize(new Dimension(300, 50));
        addRecordBtn.setFont(appFont);
        addRecordBtn.setBackground(addColor);
        addRecordBtn.setForeground(fgColor);

        editRecordBtn.setPreferredSize(new Dimension(300, 50));
        editRecordBtn.setFont(appFont);
        editRecordBtn.setBackground(editColor);
        editRecordBtn.setForeground(fgColor);

        deleteRecordBtn.setPreferredSize(new Dimension(300, 50));
        deleteRecordBtn.setFont(appFont);
        deleteRecordBtn.setBackground(deleteColor);
        deleteRecordBtn.setForeground(fgColor);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30)); 
        panel.add(marginPanel);
        panel.add(textArea);
        panel.add(logoutBtn);
        panel.add(addRecordBtn);
        panel.add(editRecordBtn);
        panel.add(deleteRecordBtn);

        adminFrame.add(panel);

        adminFrame.setVisible(true);

    }

    public void displayDeleteRecord(ArrayList<String> emails) {
        String[] emailList = emails.toArray(new String[emails.size()]);

        deleteEmailField = new JComboBox<>(emailList);

        JDialog dialog = new JDialog(frame, "Delete Record", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setSize(new Dimension(1000, 500));
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        JPanel dialogPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel marginPanel = new JPanel();
        JPanel emailPanel = new JPanel();

        emailPanel.setPreferredSize(new Dimension(1000, 50));
        marginPanel.setSize(new Dimension(1000, 30));

        JButton cancelBtn = new JButton("Cancel");

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        deleteEmailLabel.setFont(appFont);
        deleteEmailLabel.setPreferredSize(new Dimension(200, 50));
        deleteEmailLabel.setForeground(bgColor);

        deleteEmailField.setPreferredSize(new Dimension(500, 50));
        deleteEmailField.setFont(appFont);
        deleteEmailField.setBackground(fgColor);
        deleteEmailField.setForeground(bgColor);

        deleteBtn.setPreferredSize(new Dimension(300, 50));
        deleteBtn.setFont(appFont);
        deleteBtn.setBackground(deleteColor);
        deleteBtn.setForeground(fgColor);
        cancelBtn.setPreferredSize(new Dimension(300, 50));
        cancelBtn.setFont(appFont);
        cancelBtn.setBackground(bgColor);
        cancelBtn.setForeground(fgColor);

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JDialog) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JDialog) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
            }
        });

        emailPanel.add(deleteEmailLabel);
        emailPanel.add(deleteEmailField);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(cancelBtn);

        dialogPanel.add(marginPanel);
        dialogPanel.add(emailPanel);
        dialogPanel.add(buttonPanel);

        dialog.getContentPane().add(dialogPanel);
        dialog.setVisible(true);
    }

    public void displayEditRecord(ArrayList<String> emails) {
        String[] emailList = emails.toArray(new String[emails.size()]);

        editEmailField = new JComboBox<>(emailList);

        JDialog dialog = new JDialog(frame, "Edit Record", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setSize(new Dimension(1000, 500));
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        JPanel dialogPanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel rolePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel marginPanel = new JPanel();
        JPanel paddingPanel = new JPanel();

        JButton cancelBtn = new JButton("Cancel");

        emailPanel.setPreferredSize(new Dimension(1000, 50));
        passwordPanel.setPreferredSize(new Dimension(1000, 50));
        rolePanel.setPreferredSize(new Dimension(1000, 50));
        buttonPanel.setPreferredSize(new Dimension(1000, 50));
        marginPanel.setPreferredSize(new Dimension(1000, 30));
        paddingPanel.setPreferredSize(new Dimension(1000, 20));

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        editEmailLabel.setFont(appFont);
        editEmailLabel.setForeground(bgColor);
        editPasswordLabel.setFont(appFont);
        editPasswordLabel.setForeground(bgColor);
        editRoleLabel.setFont(appFont);
        editRoleLabel.setForeground(bgColor);

        editEmailLabel.setPreferredSize(new Dimension(200, 50));
        editPasswordLabel.setPreferredSize(new Dimension(200, 50));
        editRoleLabel.setPreferredSize(new Dimension(200, 50));

        editEmailField.setPreferredSize(new Dimension(500, 50));
        editEmailField.setBackground(fgColor);
        editEmailField.setForeground(bgColor);
        editPasswordField.setPreferredSize(new Dimension(500, 50));
        editPasswordField.setBackground(fgColor);
        editPasswordField.setForeground(bgColor);
        editRoleField.setPreferredSize(new Dimension(500, 50));
        editRoleField.setBackground(fgColor);
        editRoleField.setForeground(bgColor);
        editEmailField.setFont(appFont);
        editPasswordField.setFont(appFont);
        editRoleField.setFont(appFont);

        editBtn.setPreferredSize(new Dimension(300, 50));
        editBtn.setFont(appFont);
        editBtn.setBackground(editColor);
        editBtn.setForeground(fgColor);
        cancelBtn.setPreferredSize(new Dimension(300, 50));
        cancelBtn.setFont(appFont);
        cancelBtn.setBackground(bgColor);
        cancelBtn.setForeground(fgColor);

        editBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JDialog) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JDialog) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
            }
        });

        emailPanel.add(editEmailLabel);
        emailPanel.add(editEmailField);
        passwordPanel.add(editPasswordLabel);
        passwordPanel.add(editPasswordField);
        rolePanel.add(editRoleLabel);
        rolePanel.add(editRoleField);
        buttonPanel.add(editBtn);
        buttonPanel.add(cancelBtn);

        dialogPanel.add(marginPanel);
        dialogPanel.add(emailPanel);
        dialogPanel.add(paddingPanel);
        dialogPanel.add(passwordPanel);
        dialogPanel.add(paddingPanel);
        dialogPanel.add(rolePanel);
        dialogPanel.add(paddingPanel);
        dialogPanel.add(buttonPanel);

        dialog.getContentPane().add(dialogPanel);
        dialog.setVisible(true);

    }

    public void displayAddRecord() {

        JDialog dialog = new JDialog(frame, "Add Record", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setSize(new Dimension(1000, 500));
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        JPanel dialogPanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel rolePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel marginPanel = new JPanel();
        JPanel paddingPanel = new JPanel();

        JButton cancelBtn = new JButton("Cancel");

        emailPanel.setPreferredSize(new Dimension(1000, 50));
        passwordPanel.setPreferredSize(new Dimension(1000, 50));
        rolePanel.setPreferredSize(new Dimension(1000, 50));
        buttonPanel.setPreferredSize(new Dimension(1000, 50));
        marginPanel.setPreferredSize(new Dimension(1000, 30));
        paddingPanel.setPreferredSize(new Dimension(1000, 20));

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        addEmailLabel.setFont(appFont);
        addEmailLabel.setForeground(bgColor);
        addPasswordLabel.setFont(appFont);
        addPasswordLabel.setForeground(bgColor);
        addRoleLabel.setFont(appFont);
        addRoleLabel.setForeground(bgColor);

        addEmailLabel.setPreferredSize(new Dimension(200, 50));
        addPasswordLabel.setPreferredSize(new Dimension(200, 50));
        addRoleLabel.setPreferredSize(new Dimension(200, 50));

        addEmailField.setPreferredSize(new Dimension(500, 50));
        addEmailField.setBackground(fgColor);
        addEmailField.setForeground(bgColor);
        addPasswordField.setPreferredSize(new Dimension(500, 50));
        addPasswordField.setBackground(fgColor);
        addPasswordField.setForeground(bgColor);
        addRoleField.setPreferredSize(new Dimension(500, 50));
        addRoleField.setBackground(fgColor);
        addRoleField.setForeground(bgColor);

        addEmailField.setFont(appFont);
        addPasswordField.setFont(appFont);
        addRoleField.setFont(appFont);

        addBtn.setPreferredSize(new Dimension(300, 50));
        addBtn.setFont(appFont);
        addBtn.setBackground(addColor);
        addBtn.setForeground(fgColor);

        cancelBtn.setPreferredSize(new Dimension(300, 50));
        cancelBtn.setFont(appFont);
        cancelBtn.setBackground(bgColor);
        cancelBtn.setForeground(fgColor);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JDialog) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JDialog) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
            }
        });

        emailPanel.add(addEmailLabel);
        emailPanel.add(addEmailField);
        passwordPanel.add(addPasswordLabel);
        passwordPanel.add(addPasswordField);
        rolePanel.add(addRoleLabel);
        rolePanel.add(addRoleField);
        buttonPanel.add(addBtn);
        buttonPanel.add(cancelBtn);

        dialogPanel.add(marginPanel);
        dialogPanel.add(emailPanel);
        dialogPanel.add(paddingPanel);
        dialogPanel.add(passwordPanel);
        dialogPanel.add(paddingPanel);
        dialogPanel.add(rolePanel);
        dialogPanel.add(paddingPanel);
        dialogPanel.add(buttonPanel);

        dialog.getContentPane().add(dialogPanel);
        dialog.setVisible(true);

    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public String getAddEmail() {
        return addEmailField.getText();
    }

    public String getAddPassword() {
        return addPasswordField.getText();
    }

    public String getAddRole() {
        return addRoleField.getSelectedItem().toString();
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public String getEditEmail() {
        return editEmailField.getSelectedItem().toString();
    }

    public String getEditPassword() {
        return editPasswordField.getText();
    }

    public String getEditRole() {
        return editRoleField.getSelectedItem().toString();
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public String getDeleteEmail() {
        return deleteEmailField.getSelectedItem().toString();
    }

    public void refreshRecords(ArrayList<String> updatedRecords) {
        adminFrame.dispose();
        adminFrame = new JFrame();
        displaySuccessfulAdmin(updatedRecords);
    }

    public void runGUI() {
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
