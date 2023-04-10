package GuiApp;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import StudentManager.StudentManagerInterface;

public class MainFrame extends JFrame {
    private StudentManagerInterface smi;
    private CardLayout cardLayout;
    private JMenuBar menuBar;
    private JButton studentMenu;
    private JButton lecturerMenu;
    private JButton courseMenu;
    private JButton enrollMenu;
    private JButton gradeMenu;
    private JPanel mainPanel;
    private StudentPanel studentPanel;
    private LecturerPanel lecturerPanel;
    private CoursePanel coursePanel;
    private EnrolledCoursePanel enrolledCoursePanel;
    private GradePanel gradePanel;

    public MainFrame(StudentManagerInterface smi) {
        this.smi = smi;
        setSize(1200, 700);
        setTitle("Student Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        menuBar = new JMenuBar();
        studentMenu = new JButton("Student");
        lecturerMenu = new JButton("Lecturer");
        courseMenu = new JButton("Course");
        enrollMenu = new JButton("Enroll");
        gradeMenu = new JButton("Grade");
        menuBar.add(studentMenu);
        menuBar.add(lecturerMenu);
        menuBar.add(courseMenu);
        menuBar.add(enrollMenu);
        menuBar.add(gradeMenu);
        setJMenuBar(this.menuBar);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        add(mainPanel);
        studentPanel = new StudentPanel(this.smi);
        lecturerPanel = new LecturerPanel(this.smi);
        coursePanel = new CoursePanel(this.smi);
        enrolledCoursePanel = new EnrolledCoursePanel(this.smi);
        gradePanel = new GradePanel(this.smi);
        mainPanel.add(studentPanel, "student");
        mainPanel.add(lecturerPanel, "lecturer");
        mainPanel.add(coursePanel, "course");
        mainPanel.add(enrolledCoursePanel, "enroll");
        mainPanel.add(gradePanel, "grade");
        cardLayout.show(mainPanel, "student");
        pack();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (smi.storeData()) {
                    System.out.println("Successfully save the data.");
                }
                else {
                    System.out.println("Failed to save the data.");
                }
                System.out.println("Closing the application...");
                dispose();
            }
        });

        studentMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "student");
                pack();
            }
        });
        lecturerMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "lecturer");
                pack();
            }
        });
        courseMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "course");
                pack();
            }
        });
        enrollMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "enroll");
                pack();
            }
        });
        gradeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "grade");
                pack();
            }
        });
    }

    public static void main(String[] args) {
        StudentManagerInterface smi = new StudentManagerInterface();
        System.out.println("Loading data ...");
        if (smi.loadData()) {
            System.out.println("Successfully load the data.");
        }
        else {
            System.out.println("Faild to load the data.");
        }
        MainFrame frame = new MainFrame(smi);
        frame.setVisible(true);
    }
}
