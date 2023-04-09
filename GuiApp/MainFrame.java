package GuiApp;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel mainPanel;
    private StudentPanel studentPanel;
    private LecturerPanel lecturerPanel;
    private CoursePanel coursePanel;

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
        menuBar.add(studentMenu);
        menuBar.add(lecturerMenu);
        menuBar.add(courseMenu);
        setJMenuBar(this.menuBar);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        add(mainPanel);
        studentPanel = new StudentPanel(this.smi);
        lecturerPanel = new LecturerPanel(this.smi);
        coursePanel = new CoursePanel();
        mainPanel.add(studentPanel, "student");
        mainPanel.add(lecturerPanel, "lecturer");
        mainPanel.add(coursePanel, "course");
        cardLayout.show(mainPanel, "student");
        pack();

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
    }

    public static void main(String[] args) {
        StudentManagerInterface smi = new StudentManagerInterface();
        smi.loadData();
        MainFrame frame = new MainFrame(smi);
        frame.setVisible(true);
    }
}
