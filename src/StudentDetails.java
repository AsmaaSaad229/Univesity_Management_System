import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class StudentDetails extends JDialog{

    private JPanel SDPanel;
    private JTable StudentTable;
    private JTextField tfDelete;
    private JButton btnDelete;
    private JButton addStudentButton;
    private JButton updateStudentButton;


    public StudentDetails(JFrame parent){
        super(parent);
        setTitle("Student Details");
        setContentPane(SDPanel);
        setMinimumSize(new Dimension(700,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableLoad();


        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });


        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddStudent(null).setVisible(true);
            }
        });

        setVisible(true);
    }

    private void deleteStudent() {

        String delRollNo = tfDelete.getText();

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";



        try{

            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement;

            preparedStatement=conn.prepareStatement("DELETE FROM students WHERE rollNo=?");
            preparedStatement.setString(1,delRollNo);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null,"Student deleted successfully");

            tableLoad();

            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }


    }


    void tableLoad(){

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";



        try{

            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement;

            preparedStatement=conn.prepareStatement("select * from students");
            ResultSet resultSet = preparedStatement.executeQuery();
            StudentTable.setModel(DbUtils.resultSetToTableModel(resultSet));

            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        StudentDetails studentDetails=new StudentDetails(null);
    }

}
