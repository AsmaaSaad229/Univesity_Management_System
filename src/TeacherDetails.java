import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TeacherDetails extends JDialog {
    private JPanel TDPanel;
    private JTextField tfDelete;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JTable TeacherTable;

    public TeacherDetails(JFrame parent){
        super(parent);
        setTitle("Teacher Details");
        setContentPane(TDPanel);
        setMinimumSize(new Dimension(700,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableLoad();

        setVisible(true);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTaecher();

            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeacher(null).setVisible(true);

            }
        });

        setVisible(true);

    }

    private void deleteTaecher() {

        String delRollNo = tfDelete.getText();

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";



        try{

            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement;

            preparedStatement=conn.prepareStatement("DELETE FROM teachers WHERE rollNo=?");
            preparedStatement.setString(1,delRollNo);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null,"Teacher deleted successfully");

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

            preparedStatement=conn.prepareStatement("select * from teachers");
            ResultSet resultSet = preparedStatement.executeQuery();
            TeacherTable.setModel(DbUtils.resultSetToTableModel(resultSet));

            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        TeacherDetails teacherDetails=new TeacherDetails(null);
    }




}
