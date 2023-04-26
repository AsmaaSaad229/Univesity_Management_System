import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Results extends JDialog {
    private JTextField tfDocRollNum;
    private JTextField tfStudentRollNum;
    private JTextField tfResult;
    private JButton cancelButton;
    private JPanel JSubResult;
    private JButton btSubmit;

    public Results(JFrame parent){
        super(parent);
        setTitle("Add Result");
        setContentPane(JSubResult);
        setMinimumSize(new Dimension(600,550));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String docRollNum = tfDocRollNum.getText();
                String studentRollNum = tfStudentRollNum.getText();
                String result = tfResult.getText();

                submitResult(docRollNum,studentRollNum,result);
            }
        });
        setVisible(true);
    }

    private void submitResult(String docRollNum, String studentRollNum, String result) {
        Teacher teacher;
        Student student;
        String s=null ,s2 = null, s3 =null;
        String m1=null,m2=null,m3=null,m4=null,m5=null,m6=null;

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql,sql2,sql3 ;
            sql = "SELECT * FROM `teachers` WHERE rollNo=?";
            sql2 = "SELECT * FROM `students` WHERE rollNo=?";


            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,docRollNum);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet .next() ){
                teacher = new Teacher();
                s =  teacher.subject=resultSet.getString("subject");
//                System.out.println("s = "+s);
            }

            PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
            preparedStatement2.setString(1,studentRollNum);

            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if(resultSet2 .next() ){
                student = new Student();
                s2 =  student.rollNo=resultSet2.getString("rollNo");
//                System.out.println("s2 = "+s2);

            }

            sql3 = "INSERT INTO subjects(rollNo,subject_1,subject_2,subject_3,subject_4,subject_5,subject_6)"+"VALUES(?,?,?,?,?,?,?)\"";


            PreparedStatement preparedStatement3=conn.prepareStatement(sql3);
            preparedStatement3.setString(1,"");
            preparedStatement3.setString(2,result);
            preparedStatement3.setString(3,"");
            preparedStatement3.setString(4,"");
            preparedStatement3.setString(5,"");
            preparedStatement3.setString(6,"");
            preparedStatement3.setString(7,"");

//            ResultSet resultSet3 = preparedStatement3.executeQuery();
//            if(resultSet3 .next() ){
//                teacher = new Teacher();
//                s3 =  teacher.subject=resultSet.getString(s);
//                System.out.println("s3 = "+ s3);
//            }

            int addRows = preparedStatement3.executeUpdate();
            if(addRows >0 ){
//                teacher = new Teacher();
//                String string = new String();
                JOptionPane.showMessageDialog(null,"Marks inserted successfully");
            }

            stmt.close();
            conn.close();

//            return teacher;

        } catch (Exception e) {
            e.printStackTrace();
        }

        }

    public static void main(String[] args) {
        Results  myAdd = new Results (null);
//        Student student = myAdd.student;
//        if(student != null){
//            System.out.println("successful Added Student: "+student.name);
//        }else {
//            System.out.println("Added student form is closed.");
//        }
    }
}
