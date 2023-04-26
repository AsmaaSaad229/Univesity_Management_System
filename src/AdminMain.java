import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMain extends JDialog {
    private JPanel adPanel;
    private JButton btAddStudent;
    private JButton btAddTeacher;

    public AdminMain (JFrame parent){
        super(parent);
        setTitle("Add");
        setContentPane(adPanel);
        setMinimumSize(new Dimension(560,380));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddStudent(null).setVisible(true);
            }
        });
        btAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddTeacher(null).setVisible(true);
            }
        });
        setVisible(true);
    }
    }
