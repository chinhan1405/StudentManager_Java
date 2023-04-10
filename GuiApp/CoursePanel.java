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

public class CoursePanel extends JPanel {
    private StudentManagerInterface smi;
    private String[] columnNames;

    private JPanel toolPanel;
    private JPanel dataPanel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel lecturerLabel;
    private JLabel studentLabel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField lecturerIdTextField;
    private JTextField studentIdTextField;
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton findAllButton;
    private JButton findByNameButton;
    private JButton findByLecturerButton;
    private JButton setLecturerButton;
    private JButton findByStudentButton;
    private JButton finishCourseButton;
    private JTable dataTable;
    private JScrollPane scrollpane;

    public CoursePanel(StudentManagerInterface smi) {
        this.smi = smi;

        //Load tools
        setLayout(new GridBagLayout());
        this.toolPanel = new JPanel(new GridBagLayout());
        this.toolPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.idLabel = new DefaultLabel("ID:");
        this.nameLabel = new DefaultLabel("Name:");
        this.descriptionLabel = new DefaultLabel("Description:");
        this.lecturerLabel = new DefaultLabel("Lecturer ID:");
        this.studentLabel = new DefaultLabel("Student ID:");
        this.idTextField = new DefaultTextField("", 20);
        this.nameTextField = new DefaultTextField("", 20);
        this.descriptionTextField = new DefaultTextField("", 20);
        this.lecturerIdTextField = new DefaultTextField("", 20);
        this.studentIdTextField = new DefaultTextField("", 20);
        this.addButton = new DefaultButton("ADD");
        this.removeButton = new DefaultButton("REMOVE");
        this.updateButton = new DefaultButton("UPDATE");
        this.findAllButton = new DefaultButton("SHOW ALL");
        this.findByNameButton = new DefaultButton("FIND BY NAME");
        this.findByLecturerButton = new DefaultButton("FIND BY LECTURER");
        this.setLecturerButton = new DefaultButton("SET LECTURER");
        this.findByStudentButton = new DefaultButton("FIND BY STUDENT");
        this.finishCourseButton = new DefaultButton("FINISH COURSE");
        this.toolPanel.add(idLabel, GridBag.getGridBagConstraintsLeft(0, 0));
        this.toolPanel.add(idTextField, GridBag.getGridBagConstraintsLeft(1, 0));
        this.toolPanel.add(nameLabel, GridBag.getGridBagConstraintsLeft(0, 1));
        this.toolPanel.add(nameTextField, GridBag.getGridBagConstraintsLeft(1, 1));
        this.toolPanel.add(descriptionLabel, GridBag.getGridBagConstraintsLeft(0, 2));
        this.toolPanel.add(descriptionTextField, GridBag.getGridBagConstraintsLeft(1, 2));
        this.toolPanel.add(addButton, GridBag.getGridBagConstraintsFill(0, 3));
        this.toolPanel.add(removeButton, GridBag.getGridBagConstraintsFill(0, 4));
        this.toolPanel.add(updateButton, GridBag.getGridBagConstraintsFill(0, 5));
        this.toolPanel.add(findAllButton, GridBag.getGridBagConstraintsFill(0, 6));
        this.toolPanel.add(findByNameButton, GridBag.getGridBagConstraintsFill(0, 7));
        this.toolPanel.add(lecturerLabel, GridBag.getGridBagConstraintsLeft(0, 8));
        this.toolPanel.add(lecturerIdTextField, GridBag.getGridBagConstraintsLeft(1, 8));
        this.toolPanel.add(findByLecturerButton, GridBag.getGridBagConstraintsFill(0, 9));
        this.toolPanel.add(setLecturerButton, GridBag.getGridBagConstraintsFill(0, 10));
        this.toolPanel.add(studentLabel, GridBag.getGridBagConstraintsLeft(0, 11));
        this.toolPanel.add(studentIdTextField, GridBag.getGridBagConstraintsLeft(1, 11));
        this.toolPanel.add(findByStudentButton, GridBag.getGridBagConstraintsFill(0, 12));
        this.toolPanel.add(finishCourseButton, GridBag.getGridBagConstraintsFill(0, 13));

        
        //load data
        this.dataPanel = new JPanel();
        this.columnNames = new String[]{ "ID", "Name", "Description", "Lecturer ID", "Finished" };
        this.dataTable = new DefaultTable(this.smi.getAllCourses(), columnNames);
        this.scrollpane = new JScrollPane(dataTable);
        this.dataPanel.add(scrollpane);
        add(toolPanel, GridBag.getGridBagConstraintsTopLeft(0, 0));
        add(dataPanel, GridBag.getGridBagConstraintsFill(1, 0));

        //load event listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (smi.addCourse(idTextField.getText(), nameTextField.getText(), descriptionTextField.getText())) {
                        System.out.println("Added a course.");
                    }
                    updateAllCourses();
                }
                catch (Exception ex) {
                    nameTextField.setText("Error somewhere");
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (smi.removeCourse(idTextField.getText())) {
                        System.out.println("Removed a course.");
                    }
                    updateAllCourses();
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
                    if (smi.updateCourse(idTextField.getText(), nameTextField.getText(), descriptionTextField.getText())) {
                        System.out.println("Updated a course.");
                    }
                    updateAllCourses();
                }
                catch (Exception ex) {
                    nameTextField.setText("Error somewhere");
                }
            }
        });
        findAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAllCourses();
            }
        });
        findByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateCoursesByName(nameTextField.getText());
                }
                catch (Exception ex) {
                    nameTextField.setText("Name Error");
                }
            }
        });        
        findByLecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateCoursesByLecturer(lecturerIdTextField.getText());
                }
                catch (Exception ex) {
                    lecturerIdTextField.setText("Lecturer ID Error");
                }
            }
        });
        setLecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (smi.setNewLecturerToCourse(lecturerIdTextField.getText(), idTextField.getText())) {
                        System.out.println("Set a lecturer to a course.");
                    }
                    updateAllCourses();
                }
                catch (Exception ex) {
                    lecturerIdTextField.setText("Lecturer ID Error");
                }
            }
        });
        findByStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateCoursesByStudent(studentIdTextField.getText());
                }
                catch (Exception ex) {
                    studentIdTextField.setText("Student ID Error");
                }
            }
        });
        finishCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (smi.finishCourse(idTextField.getText())) {
                        System.out.println("Finished a course.");
                    }
                    updateAllCourses();
                }
                catch (Exception ex) {
                    idTextField.setText("ID Error");
                }
            }
        });
    }

    private void updateTableContent(Object[][] data) {
        TableModel newModel = new DefaultTableModel(data, columnNames);
        this.dataTable.setModel(newModel);
    }

    private void updateAllCourses() {
        updateTableContent(smi.getAllCourses());
    }

    private void updateCoursesByName(String name) {
        updateTableContent(smi.findCourseByName(name));
    }

    private void updateCoursesByLecturer(String lecturerId) {
        updateTableContent(smi.findCourseByLecturer(lecturerId));
    }
    
    private void updateCoursesByStudent(String courseId) {
        updateTableContent(smi.findCourseByStudent(courseId));
    }
}
