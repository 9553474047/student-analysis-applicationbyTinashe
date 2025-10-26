import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StudentDetailsForm extends JFrame implements ActionListener {

    private JTextField nameField, emailField, mobileField;
    private JComboBox<String> branchBox, sectionBox;
    private JButton saveButton, closeButton;

    public StudentDetailsForm() {
        setTitle("Student Details Form");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Student Name:");
        JLabel emailLabel = new JLabel("College Email ID:");
        JLabel mobileLabel = new JLabel("Mobile No:");
        JLabel branchLabel = new JLabel("Branch:");
        JLabel sectionLabel = new JLabel("Section:");

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        mobileField = new JTextField(15);

        String[] branches = {"CSE", "ECE", "EEE", "CIVIL", "MEC", "Others"};
        branchBox = new JComboBox<>(branches);

        String[] sections = {"Section-A", "Section-B", "Section-C", "Section-D", "Section-E"};
        sectionBox = new JComboBox<>(sections);

        saveButton = new JButton("Save");
        closeButton = new JButton("Close");

        gbc.gridx = 0; gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 2;
        add(sectionLabel, gbc);
        gbc.gridx = 3;
        add(sectionBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 2;
        add(mobileLabel, gbc);
        gbc.gridx = 3;
        add(mobileField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(branchLabel, gbc);
        gbc.gridx = 1;
        add(branchBox, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        add(saveButton, gbc);
        gbc.gridx = 2;
        add(closeButton, gbc);

        saveButton.addActionListener(this);
        closeButton.addActionListener(this);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveDetails();
        } else if (e.getSource() == closeButton) {
            dispose();
        }
    }

    private void saveDetails() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String mobile = mobileField.getText().trim();
        String branch = (String) branchBox.getSelectedItem();
        String section = (String) sectionBox.getSelectedItem();

        if (name.isEmpty() || email.isEmpty() || mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Pro.txt"))) {
            writer.write(name);
            writer.newLine();
            writer.write(email);
            writer.newLine();
            writer.write(branch);
            writer.newLine();
            writer.write(section);
            writer.newLine();
            writer.write(mobile);
            writer.newLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Successfully Saved - The Details", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentDetailsForm::new);
    }
}