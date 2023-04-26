import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Fee extends JDialog{
    private JPanel feePanel;
    //private JTextField tfRollNo;
    private JTextField tfName;
    private JTextField tfFatherName;
   // private JTextField tfAcademicYear;
    private JTextField tfTotalPayable;
    private JButton btnPay;
    private JButton btnBack;
    private JComboBox cbAcademicYear;
    private JComboBox cbRollNo;


    public Fee(JFrame parent){
        super(parent);
        setTitle("Fee Form");
        setContentPane(feePanel);
        setMinimumSize(new Dimension(700,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


//        Choice c1,c2;

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";

        cbRollNo.addItem(" ");
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            ResultSet resultSet=conn.prepareStatement("select * from students").executeQuery();
            while (resultSet.next()){
//                c1=new Choice();
//                c1.add(resultSet.getString("rollNo"));

                cbRollNo.addItem(resultSet.getString("rollNo"));
            }

//            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
//            ResultSet rs = conn.prepareStatement("select * from students where rollNo = '"+cbRollNo.getSelectedItem()+"'").executeQuery();
//            while(rs.next()) {
//                tfName.setText(rs.getString("name"));
//                tfFatherName.setText(rs.getString("fatherName"));
//            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feePaid();
            }
        });

        setVisible(true);
    }


    private void feePaid() {

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            ResultSet rs = conn.prepareStatement("select * from students where rollNo = '"+cbRollNo.getSelectedItem()+"'").executeQuery();
            while(rs.next()){
                tfName.setText(rs.getString("name"));
                tfFatherName.setText(rs.getString("fatherName"));
            }
        }catch(Exception e){}

        String rollNo =(String) cbRollNo.getSelectedItem();
        String name =tfName.getText();
        String fatherName =tfFatherName.getText();
        String academicYear =(String) cbAcademicYear.getSelectedItem();
        String feePayable =tfTotalPayable.getText();

        if(rollNo.isEmpty()||name.isEmpty()||fatherName.isEmpty()||academicYear.isEmpty()||feePayable.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
        }

       student = addFeeToDatabase(rollNo,name,fatherName,academicYear,feePayable);

    }

    Student student;
    private Student addFeeToDatabase(String rollNo, String name, String fatherName, String academicYear, String feePayable) {
        student=null;

        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql ;
            sql = "INSERT INTO fee(rollNo,name,fatherName,academicYear,feePaid)"+
                    "VALUES (?,?,?,?,?)";


            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,rollNo);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,fatherName);
            preparedStatement.setString(4,academicYear);
            preparedStatement.setString(5,feePayable);


            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0 ){
                student = new Student();
                student.name=name;
            }

            JOptionPane.showMessageDialog(this,"Successfully"+student.name,
                    "Fee Paid",JOptionPane.ERROR_MESSAGE);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return student;
    }

    public static void main(String[] args){
        Fee fee=new Fee(null);
    }

}
