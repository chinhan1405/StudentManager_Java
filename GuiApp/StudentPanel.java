package GuiApp;

import java.awt.GridBagConstraints;
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

public class StudentPanel extends JPanel {
    private StudentManagerInterface smi;
    private String[] columnNames;

    private JPanel toolPanel;
    private JPanel dataPanel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel yearOfBirthLabel;
    private JLabel emailLabel;
    private JLabel lecturerLabel;
    private JLabel courseLabel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField yearOfBirthTextField;
    private JTextField emailTextField;
    private JTextField lecturerIdTextField;
    private JTextField courseIdTextField;
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton findAllButton;
    private JButton findByNameButton;
    private JButton findByYearOfBirthButton;
    private JButton findByEmailButton;
    private JButton findByLecturerButton;
    private JButton findByCourseButton;
    private JTable dataTable;
    private JScrollPane scrollpane;


    public StudentPanel(StudentManagerInterface smi) {
        this.smi = smi;

        //Load tools
        setLayout(new GridBagLayout());
        this.toolPanel = new JPanel(new GridBagLayout());
        this.toolPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.idLabel = new DefaultLabel("ID:");
        this.nameLabel = new DefaultLabel("Name:");
        this.yearOfBirthLabel = new DefaultLabel("Year of Birth:");
        this.emailLabel = new DefaultLabel("Email:");
        this.lecturerLabel = new DefaultLabel("Lecturer ID:");
        this.courseLabel = new DefaultLabel("Course ID:");
        this.idTextField = new DefaultTextField("", 20);
        this.nameTextField = new DefaultTextField("", 20);
        this.yearOfBirthTextField = new DefaultTextField("", 20);
        this.emailTextField = new DefaultTextField("", 20);
        this.lecturerIdTextField = new DefaultTextField("", 20);
        this.courseIdTextField = new DefaultTextField("", 20);
        this.addButton = new DefaultButton("ADD");
        this.removeButton = new DefaultButton("REMOVE");
        this.updateButton = new DefaultButton("UPDATE");
        this.findAllButton = new DefaultButton("SHOW ALL");
        this.findByNameButton = new DefaultButton("FIND BY NAME");
        this.findByYearOfBirthButton = new DefaultButton("FIND BY YEAR");
        this.findByEmailButton = new DefaultButton("FIND BY EMAIL");
        this.findByLecturerButton = new DefaultButton("FIND BY LECTURER");
        this.findByCourseButton = new DefaultButton("FIND BY COURSE");
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
        this.toolPanel.add(lecturerLabel, GridBag.getGridBagConstraintsLeft(0, 11));
        this.toolPanel.add(lecturerIdTextField, GridBag.getGridBagConstraintsLeft(1, 11));
        this.toolPanel.add(findByLecturerButton, GridBag.getGridBagConstraintsFill(0, 12));
        this.toolPanel.add(courseLabel, GridBag.getGridBagConstraintsLeft(0, 13));
        this.toolPanel.add(courseIdTextField, GridBag.getGridBagConstraintsLeft(1, 13));
        this.toolPanel.add(findByCourseButton, GridBag.getGridBagConstraintsFill(0, 14));
        
        //load data
        this.dataPanel = new JPanel();
        this.columnNames = new String[]{ "ID", "Name", "YearOfBirth", "Email" };
        this.dataTable = new DefaultTable(this.smi.getAllStudents(), columnNames);
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
                    if (smi.addStudent(idTextField.getText(), nameTextField.getText(), yearOfBirth, emailTextField.getText())) {
                        System.out.println("Added a student.");
                    }
                    updateAllStudents();
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
                    if (smi.removeStudent(idTextField.getText())) {
                        System.out.println("Removed a student.");
                    }
                    updateAllStudents();
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
                    if (smi.updateStudent(idTextField.getText(), nameTextField.getText(), yearOfBirth, emailTextField.getText())) {
                        System.out.println("Updated a student.");
                    }
                    updateAllStudents();
                }
                catch (NumberFormatException ne) {
                    yearOfBirthTextField.setText("Must be an integer");
                }
            }
        });
        findAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAllStudents();
            }
        });
        findByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateStudentsByName(nameTextField.getText());
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
                    updateStudentsByYearOfBirth(yearOfBirth);
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
                    updateStudentsByEmail(emailTextField.getText());
                }
                catch (Exception ex) {
                    emailTextField.setText("Email Error");
                }
            }
        });
        findByLecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateStudentsByLecturer(lecturerIdTextField.getText());
                }
                catch (Exception ex) {
                    lecturerIdTextField.setText("Lecturer ID Error");
                }
            }
        });
        findByCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateStudentsByCourse(courseIdTextField.getText());
                }
                catch (Exception ex) {
                    courseIdTextField.setText("Course ID Error");
                }
            }
        });
    }

    private void updateTableContent(Object[][] data) {
        TableModel newModel = new DefaultTableModel(data, columnNames);
        this.dataTable.setModel(newModel);
    }

    private void updateAllStudents() {
        updateTableContent(smi.getAllStudents());
    }

    private void updateStudentsByName(String name) {
        updateTableContent(smi.findStudentByName(name));
    }

    private void updateStudentsByYearOfBirth(int yearOfBirth) {
        updateTableContent(smi.findStudentByYearOfBirth(yearOfBirth));
    }

    private void updateStudentsByEmail(String email) {
        updateTableContent(smi.findStudentByEmail(email));
    }

    private void updateStudentsByLecturer(String lecturerId) {
        updateTableContent(smi.findStudentByLecturer(lecturerId));
    }
    
    private void updateStudentsByCourse(String courseId) {
        updateTableContent(smi.findStudentByCourse(courseId));
    }
}

class GridBag {
    public static GridBagConstraints getGridBagConstraintsCenter(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.anchor = GridBagConstraints.CENTER;
        return c;
    }

    public static GridBagConstraints getGridBagConstraintsLeft(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    public static GridBagConstraints getGridBagConstraintsRight(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.anchor = GridBagConstraints.EAST;
        return c;
    }

    public static GridBagConstraints getGridBagConstraintsTopLeft(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.anchor = GridBagConstraints.NORTHWEST;
        return c;
    }
    
    public static GridBagConstraints getGridBagConstraintsFill(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.gridx = gridx;
        c.gridy = gridy;
        return c;
    }
}
