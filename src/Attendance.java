import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Attendance extends JDialog{
    private JPanel attendancePanel;
    private JComboBox cbFirstLecture;
    private JComboBox cbSecondLecture;
    private JButton btnSubmit;
    private JButton btnCancel;
    private JComboBox cbRollNo;

    public Attendance(JFrame parent){
        super(parent);
        setTitle("Attendance");
        setContentPane(attendancePanel);
        setMinimumSize(new Dimension(500,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";

        cbRollNo.addItem(" ");
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            ResultSet resultSet=conn.prepareStatement("select * from students").executeQuery();
            while (resultSet.next()){

                cbRollNo.addItem(resultSet.getString("rollNo"));
            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendance();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    public String nam,fnam;
    private void attendance() {

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            ResultSet rs = conn.prepareStatement("select * from students where rollNo = '"+cbRollNo.getSelectedItem()+"'").executeQuery();
            while(rs.next()){

                nam = rs.getString("name");
                fnam = rs.getString("fatherName");
            }
        }catch(Exception e){}

        String rollNo =(String) cbRollNo.getSelectedItem();
        String first =(String) cbFirstLecture.getSelectedItem();
        String second =(String) cbSecondLecture.getSelectedItem();


        if(rollNo.isEmpty()||first.isEmpty()||second.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
        }

         student=addAttendanceToDatabase(rollNo,first,second);

    }

    Student student;
    private Student addAttendanceToDatabase(String rollNo, String first, String second) {
        student=null;

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql ;
            sql = "INSERT INTO attendance_student(rollNo,date,first,second)"+
                    "VALUES (?,?,?,?)";


            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,rollNo);
            preparedStatement.setDate(2,new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setString(3,first);
            preparedStatement.setString(4,second);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this,"Successfully:  " + nam,
                    "Attendance Form",JOptionPane.ERROR_MESSAGE);


            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return student;

    }

    public static void main(String[] args){
        Attendance attendance=new Attendance(null);
    }


}
