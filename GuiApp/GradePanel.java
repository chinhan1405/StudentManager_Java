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

public class GradePanel extends JPanel {
    private StudentManagerInterface smi;
    private String[] columnNames;
    private String[] ColumnNamesAverage;

    private JPanel toolPanel;
    private JPanel dataPanel;
    private JLabel studentIdLabel;
    private JLabel courseIdLabel;
    private JLabel pointLabel;
    private JLabel coefficientLabel;
    private JTextField studentIdTextField;
    private JTextField courseIdTextField;
    private JTextField pointTextField;
    private JTextField coefficientTextField;
    private JButton addGradeButton;
    private JButton deleteGradeButton;
    private JButton getAllGradesButton;
    private JButton getGradesOfStudentButton;
    private JButton getStudentAverageButton;
    private JTable dataTable;
    private JScrollPane scrollpane;

    public GradePanel(StudentManagerInterface smi) {
        this.smi = smi;

        //Load tools
        setLayout(new GridBagLayout());
        this.toolPanel = new JPanel(new GridBagLayout());
        this.toolPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.studentIdLabel = new DefaultLabel("Student ID:");
        this.courseIdLabel = new DefaultLabel("Course ID:");
        this.pointLabel = new DefaultLabel("Point:");
        this.coefficientLabel = new DefaultLabel("Coef:");
        this.studentIdTextField = new DefaultTextField("", 20);
        this.courseIdTextField = new DefaultTextField("", 20);
        this.pointTextField = new DefaultTextField("", 20);
        this.coefficientTextField = new DefaultTextField("", 20);
        this.addGradeButton = new DefaultButton("ADD");
        this.deleteGradeButton = new DefaultButton("DELETE");
        this.getAllGradesButton = new DefaultButton("SHOW ALL");
        this.getGradesOfStudentButton = new DefaultButton("STUDENT GRADES");
        this.getStudentAverageButton = new DefaultButton("STUDENT AVERAGE");
        this.toolPanel.add(studentIdLabel, GridBag.getGridBagConstraintsLeft(0, 0));
        this.toolPanel.add(studentIdTextField, GridBag.getGridBagConstraintsLeft(1, 0));
        this.toolPanel.add(courseIdLabel, GridBag.getGridBagConstraintsLeft(0, 1));
        this.toolPanel.add(courseIdTextField, GridBag.getGridBagConstraintsLeft(1, 1));
        this.toolPanel.add(pointLabel, GridBag.getGridBagConstraintsLeft(0, 2));
        this.toolPanel.add(pointTextField, GridBag.getGridBagConstraintsLeft(1, 2));
        this.toolPanel.add(coefficientLabel, GridBag.getGridBagConstraintsLeft(0, 3));
        this.toolPanel.add(coefficientTextField, GridBag.getGridBagConstraintsLeft(1, 3));
        this.toolPanel.add(addGradeButton, GridBag.getGridBagConstraintsFill(0, 4));
        this.toolPanel.add(deleteGradeButton, GridBag.getGridBagConstraintsFill(0, 5));
        this.toolPanel.add(getAllGradesButton, GridBag.getGridBagConstraintsFill(0, 6));
        this.toolPanel.add(getGradesOfStudentButton, GridBag.getGridBagConstraintsFill(0, 7));
        this.toolPanel.add(getStudentAverageButton, GridBag.getGridBagConstraintsFill(0, 8));

        //load data
        this.dataPanel = new JPanel();
        this.columnNames = new String[]{ "Student ID", "Course ID", "point", "coef" };
        this.ColumnNamesAverage = new String[]{ "Student ID", "Course ID", "Course Name", "Average" };
        this.dataTable = new DefaultTable(this.smi.getAllGrades(), columnNames);
        this.scrollpane = new JScrollPane(dataTable);
        this.dataPanel.add(scrollpane);
        add(toolPanel, GridBag.getGridBagConstraintsTopLeft(0, 0));
        add(dataPanel, GridBag.getGridBagConstraintsFill(1, 0));

        //load event listener
        addGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float point = Float.parseFloat(pointTextField.getText());
                    float coefficient = Float.parseFloat(coefficientTextField.getText());
                    if (smi.addGradeForStudent(studentIdTextField.getText(), courseIdTextField.getText(), point, coefficient)) {
                        System.out.println("Added a grade.");
                    }
                    updateAllGrades();
                }
                catch (NumberFormatException ne) {
                    pointTextField.setText("Must be a float");
                    coefficientTextField.setText("Must be a float");
                }
            }
        });
        deleteGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(pointTextField.getText());
                    if (smi.deleteGradeForStudent(studentIdTextField.getText(), courseIdTextField.getText(), index)) {
                        System.out.println("Deleted a grade.");
                    }
                    updateAllGrades();
                }
                catch (NumberFormatException ne) {
                    pointTextField.setText("Must be an int");
                }
            }
        });
        getAllGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAllGrades();
            }
        });
        getGradesOfStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateGradesOfStudent(studentIdTextField.getText());
                }
                catch (Exception ex) {
                    studentIdTextField.setText("Student ID Error");
                }
            }
        });
        getStudentAverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateStudentAverage(studentIdTextField.getText());
                }
                catch (Exception ex) {
                    studentIdTextField.setText("Student ID Error");
                }
            }
        });
    }

    private void updateTableContent(Object[][] data) {
        TableModel newModel = new DefaultTableModel(data, columnNames);
        this.dataTable.setModel(newModel);
    }

    private void updateAllGrades() {
        updateTableContent(smi.getAllGrades());
    }

    private void updateGradesOfStudent(String studentId) {
        updateTableContent(smi.getStudentAllGrades(studentId));
    }

    private void updateStudentAverage(String studentId) {
        TableModel newModel = new DefaultTableModel(smi.getStudentAverage(studentId), ColumnNamesAverage);
        this.dataTable.setModel(newModel);
    }
}
