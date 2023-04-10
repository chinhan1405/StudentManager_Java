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

public class EnrolledCoursePanel extends JPanel {
    private StudentManagerInterface smi;
    private String[] columnNames;

    private JPanel toolPanel;
    private JPanel dataPanel;
    private JLabel studentLabel;
    private JLabel courseLabel;
    private JTextField studentIdTextField;
    private JTextField courseIdTextField;
    private JButton enrollButton;
    private JButton removeButton;
    private JButton findAllButton;
    private JButton findByStudentButton;
    private JButton findByCourseButton;
    private JTable dataTable;
    private JScrollPane scrollpane;

    public EnrolledCoursePanel(StudentManagerInterface smi) {
        this.smi = smi;

        //Load tools
        setLayout(new GridBagLayout());
        this.toolPanel = new JPanel(new GridBagLayout());
        this.toolPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.studentLabel = new DefaultLabel("Student ID:");
        this.courseLabel = new DefaultLabel("Course ID:");
        this.studentIdTextField = new DefaultTextField("", 20);
        this.courseIdTextField = new DefaultTextField("", 20);
        this.enrollButton = new DefaultButton("ENROLL");
        this.removeButton = new DefaultButton("REMOVE");
        this.findAllButton = new DefaultButton("SHOW ALL");
        this.findByStudentButton = new DefaultButton("FIND BY STUDENT");
        this.findByCourseButton = new DefaultButton("FIND BY COURSE");
        this.toolPanel.add(studentLabel, GridBag.getGridBagConstraintsLeft(0, 0));
        this.toolPanel.add(studentIdTextField, GridBag.getGridBagConstraintsLeft(1, 0));
        this.toolPanel.add(courseLabel, GridBag.getGridBagConstraintsLeft(0, 1));
        this.toolPanel.add(courseIdTextField, GridBag.getGridBagConstraintsLeft(1, 1));
        this.toolPanel.add(enrollButton, GridBag.getGridBagConstraintsFill(0, 2));
        this.toolPanel.add(removeButton, GridBag.getGridBagConstraintsFill(0, 3));
        this.toolPanel.add(findAllButton, GridBag.getGridBagConstraintsFill(0, 4));
        this.toolPanel.add(findByStudentButton, GridBag.getGridBagConstraintsFill(0, 5));
        this.toolPanel.add(findByCourseButton, GridBag.getGridBagConstraintsFill(0, 6));

        //load data
        this.dataPanel = new JPanel();
        this.columnNames = new String[]{ "Student ID", "Course ID", "Finished" };
        this.dataTable = new DefaultTable(this.smi.getAllEnrolledCourses(), columnNames);
        this.scrollpane = new JScrollPane(dataTable);
        this.dataPanel.add(scrollpane);
        add(toolPanel, GridBag.getGridBagConstraintsTopLeft(0, 0));
        add(dataPanel, GridBag.getGridBagConstraintsFill(1, 0));

        //load event listener
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (smi.enrollNewCourseForStudent(studentIdTextField.getText(), courseIdTextField.getText())) {
                        System.out.println("Enrolled a student.");
                    }
                    updateAllEnrolledCourses();
                }
                catch (NumberFormatException ne) {
                    studentIdTextField.setText("Error somewhere");
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (smi.removeStudentfromCourse(studentIdTextField.getText(), courseIdTextField.getText())) {
                        System.out.println("Removed a student from a course.");
                    }
                    updateAllEnrolledCourses();
                }
                catch (NumberFormatException ne) {
                    studentIdTextField.setText("Error somewhere");
                }
            }
        });
        findAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAllEnrolledCourses();
            }
        });
        findByStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateEnrolledCoursesByStudent(studentIdTextField.getText());
                }
                catch (NumberFormatException ne) {
                    studentIdTextField.setText("Error somewhere");
                }
            }
        });
        findByCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateEnrolledCoursesByCourse(courseIdTextField.getText());
                }
                catch (NumberFormatException ne) {
                    studentIdTextField.setText("Error somewhere");
                }
            }
        });
    }

    private void updateTableContent(Object[][] data) {
        TableModel newModel = new DefaultTableModel(data, columnNames);
        this.dataTable.setModel(newModel);
    }

    private void updateAllEnrolledCourses() {
        updateTableContent(smi.getAllEnrolledCourses());
    }

    private void updateEnrolledCoursesByStudent(String studentId) {
        updateTableContent(smi.findEnrolledCoursesByStudent(studentId));
    }

    private void updateEnrolledCoursesByCourse(String courseId) {
        updateTableContent(smi.findEnrolledCoursesByCourse(courseId));
    }
}
