package GuiApp;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import StudentManager.StudentManagerInterface;

public class LecturerPanel extends JPanel {
    private StudentManagerInterface smi;
    private String[] columnNames;

    private JPanel toolPanel;
    private JPanel dataPanel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel yearOfBirthLabel;
    private JLabel emailLabel;
    private JLabel studentLabel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField yearOfBirthTextField;
    private JTextField emailTextField;
    private JTextField studentIdTextField;
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton findAllButton;
    private JButton findByNameButton;
    private JButton findByYearOfBirthButton;
    private JButton findByEmailButton;
    private JButton findByStudentButton;
    private JTable dataTable;
    private JScrollPane scrollpane;

    public LecturerPanel(StudentManagerInterface smi) {
        this.smi = smi;

        //Load tools
        setLayout(new GridBagLayout());
        this.toolPanel = new JPanel(new GridBagLayout());
        this.toolPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.idLabel = new DefaultLabel("ID:");
        this.nameLabel = new DefaultLabel("Name:");
        this.yearOfBirthLabel = new DefaultLabel("Year of Birth:");
        this.emailLabel = new DefaultLabel("Email:");
        this.studentLabel = new DefaultLabel("Student ID:");
        this.idTextField = new DefaultTextField("", 20);
        this.nameTextField = new DefaultTextField("", 20);
        this.yearOfBirthTextField = new DefaultTextField("", 20);
        this.emailTextField = new DefaultTextField("", 20);
        this.studentIdTextField = new DefaultTextField("", 20);
        this.addButton = new DefaultButton("ADD");
        this.removeButton = new DefaultButton("REMOVE");
        this.updateButton = new DefaultButton("UPDATE");
        this.findAllButton = new DefaultButton("SHOW ALL");
        this.findByNameButton = new DefaultButton("FIND BY NAME");
        this.findByYearOfBirthButton = new DefaultButton("FIND BY YEAR");
        this.findByEmailButton = new DefaultButton("FIND BY EMAIL");
        this.findByStudentButton = new DefaultButton("FIND BY STUDENT");
        this.toolPanel.add(idLabel, GridBag.getGridBagConstraintsLeft(0, 0));
        this.toolPanel.add(idTextField, GridBag.getGridBagConstraintsLeft(1, 0));
        this.toolPanel.add(nameLabel, GridBag.getGridBagConstraintsLeft(0, 1));
        this.toolPanel.add(nameTextField, GridBag.getGridBagConstraintsLeft(1, 1));
        this.toolPanel.add(yearOfBirthLabel, GridBag.getGridBagConstraintsLeft(0, 2));
        this.toolPanel.add(yearOfBirthTextField, GridBag.getGridBagConstraintsLeft(1, 2));
        this.toolPanel.add(emailLabel, GridBag.getGridBagConstraintsLeft(0, 3));
        this.toolPanel.add(emailTextField, GridBag.getGridBagConstraintsLeft(1, 3));
        this.toolPanel.add(addButton, GridBag.getGridBagConstraintsFill(0, 4));
        this.toolPanel.add(removeButton, GridBag.getGridBagConstraintsFill(0, 5));
        this.toolPanel.add(updateButton, GridBag.getGridBagConstraintsFill(0, 6));
        this.toolPanel.add(findAllButton, GridBag.getGridBagConstraintsFill(0, 7));
        this.toolPanel.add(findByNameButton, GridBag.getGridBagConstraintsFill(0, 8));
        this.toolPanel.add(findByYearOfBirthButton, GridBag.getGridBagConstraintsFill(0, 9));
        this.toolPanel.add(findByEmailButton, GridBag.getGridBagConstraintsFill(0, 10));
        this.toolPanel.add(studentLabel, GridBag.getGridBagConstraintsLeft(0, 11));
        this.toolPanel.add(studentIdTextField, GridBag.getGridBagConstraintsFill(1, 11));
        this.toolPanel.add(findByStudentButton, GridBag.getGridBagConstraintsFill(0, 12));
        
        //load data
        this.dataPanel = new JPanel();
        this.columnNames = new String[]{ "ID", "Name", "YearOfBirth", "Email" };
        this.dataTable = new DefaultTable(this.smi.getAllLecturers(), columnNames);
        this.scrollpane = new JScrollPane(dataTable);
        this.dataPanel.add(scrollpane);
        add(toolPanel, GridBag.getGridBagConstraintsTopLeft(0, 0));
        add(dataPanel, GridBag.getGridBagConstraintsFill(1, 0));

        //load event listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int yearOfBirth = Integer.parseInt(yearOfBirthTextField.getText());
                    if (smi.addLecturer(idTextField.getText(), nameTextField.getText(), yearOfBirth, emailTextField.getText())) {
                        System.out.println("Added a lecturer.");
                    }
                    updateAllLecturers();
                }
                catch (NumberFormatException ne) {
                    yearOfBirthTextField.setText("Must be an integer");
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (smi.removeLecturer(idTextField.getText())) {
                        System.out.println("Removed a lecturer.");
                    }
                    updateAllLecturers();
                }
                catch (Exception ex) {
                    nameTextField.setText("Check the ID again");
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int yearOfBirth = 0;
                    if (!yearOfBirthTextField.getText().equals("")) {
                        yearOfBirth = Integer.parseInt(yearOfBirthTextField.getText());
                    }
                    if (smi.updateLecturer(idTextField.getText(), nameTextField.getText(), yearOfBirth, emailTextField.getText())) {
                        System.out.println("Updated a lecturer.");
                    }
                    updateAllLecturers();
                }
                catch (NumberFormatException ne) {
                    yearOfBirthTextField.setText("Must be an integer");
                }
            }
        });
        findAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAllLecturers();
            }
        });
        findByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateLecturersByName(nameTextField.getText());
                }
                catch (Exception ex) {
                    nameTextField.setText("Name Error");
                }
            }
        });
        findByYearOfBirthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int yearOfBirth = Integer.parseInt(yearOfBirthTextField.getText());
                    updateLecturersByYearOfBirth(yearOfBirth);
                }
                catch (NumberFormatException ne) {
                    yearOfBirthTextField.setText("Must be an integer");
                }
            }
        });
        findByEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateLecturersByEmail(emailTextField.getText());
                }
                catch (Exception ex) {
                    emailTextField.setText("Email Error");
                }
            }
        });
        findByStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateLecturersByLecturer(studentIdTextField.getText());
                }
                catch (Exception ex) {
                    studentIdTextField.setText("Lecturer ID Error");
                }
            }
        });
    }

    private void updateTableContent(Object[][] data) {
        TableModel newModel = new DefaultTableModel(data, columnNames);
        this.dataTable.setModel(newModel);
    }

    private void updateAllLecturers() {
        updateTableContent(smi.getAllLecturers());
    }

    private void updateLecturersByName(String name) {
        updateTableContent(smi.findLecturerByName(name));
    }

    private void updateLecturersByYearOfBirth(int yearOfBirth) {
        updateTableContent(smi.findLecturerByYearOfBirth(yearOfBirth));
    }

    private void updateLecturersByEmail(String email) {
        updateTableContent(smi.findLecturerByEmail(email));
    }

    private void updateLecturersByLecturer(String lecturerId) {
        updateTableContent(smi.findLecturerByStudent(lecturerId));
    }
}
