import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddStudent extends JDialog{
    private JButton btSubmit;
    private JTextField tfName;
    private JTextField tfAge;
    private JTextField tfAddress;
    private JTextField tfEmail;
    private JTextField tfRollNo;
    private JTextField tfFathersName;
    private JTextField tfDOB;
    private JTextField tfPhone;
    private JButton btCancel;
    private JPanel addStudentPanel;
    private JComboBox cbBranch;
    private JPasswordField tfPassword;
    private JComboBox tfAcadimicYear;
    private JComboBox tfSemester;


    public AddStudent(JFrame parent){
        super(parent);
        setTitle("Add Student");
        setContentPane(addStudentPanel);
        setMinimumSize(new Dimension(1000,550));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();

                AddStudent myAdd = new AddStudent(null);
                Student student = myAdd.student;
                if(student != null){
                    JOptionPane.showMessageDialog(AddStudent.this,
                            "successful Added Student:"+student.name,
                            "Submit",JOptionPane.INFORMATION_MESSAGE);
//                    System.out.println("successful Added Student: "+student.name);
                }else {
                    JOptionPane.showMessageDialog(AddStudent.this,
                            "Added student form is closed.",
                            "Close",JOptionPane.CLOSED_OPTION);
                    new AddStudent(null).setVisible(false);
//                    System.out.println("Added student form is closed.");
                }
            }
        });
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AddStudent.this,
                        "Do you want to close this page?",
                        "Close",JOptionPane.CLOSED_OPTION);
                dispose();
//                new AddStudent(null).setVisible(false);
            }
        });

        setVisible(true);

    }


    private void addStudent() {
        String name = tfName.getText();
        String fatherName = tfFathersName.getText();
        String age = tfAge.getText() ;
        String dob = tfDOB.getText();
        String address = tfAddress.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(tfPassword.getPassword());
        String branch = (String) cbBranch.getSelectedItem();
        String rollNo = tfRollNo.getText();
        String academicYear = (String) tfAcadimicYear.getSelectedItem();
        String semester = (String) tfSemester.getSelectedItem();

        if (name.isEmpty() || fatherName.isEmpty() || age.isEmpty() || dob.isEmpty() || address.isEmpty() ||
        phone.isEmpty()  || email.isEmpty() || password.isEmpty() || branch.isEmpty() || rollNo.isEmpty()
                || academicYear.isEmpty()|| semester.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        student = addStudentToDatabase(name,fatherName,age,dob,address,phone,email,password,branch,rollNo,academicYear,semester);
        if (student != null)
            dispose();
        else{
            JOptionPane.showMessageDialog(this, "Failed to submit new student","Try Again",JOptionPane.ERROR_MESSAGE);
        }
    }

    public Student student;
    private Student addStudentToDatabase(String name, String fatherName, String age, String dob, String address, String phone, String email,String password,String branch, String rollNo,String academicYear, String semester) {
        Student student = null ;
        final String DB_URL = "jdbc:mysql://localhost/univercity";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

           Statement stmt = conn.createStatement();
            String sql ;
            sql = "INSERT INTO students(name,fatherName,age,dob,address,phone,email,branch,rollNo,password, acadimicYear,semester)"+
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,fatherName);
            preparedStatement.setString(3,age);
            preparedStatement.setString(4,dob);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,phone);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,branch);
            preparedStatement.setString(9,rollNo);
            preparedStatement.setString(10,password);
            preparedStatement.setString(11,academicYear);
            preparedStatement.setString(12,semester);


            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0 ){
                student = new Student();
                student.name=name;
                student.fatherName=fatherName;
                student.age=age;
                student.dob=dob;
                student.address=address;
                student.phone=phone;
                student.email=email;
                student.branch=branch;
                student.rollNo=rollNo;
                student.password=password;
                student.acadimicYear=academicYear;
                student.semester=semester;

            }

                stmt.close();
                conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }
//    public static void main(String[] args) {
//        AddStudent myAdd = new AddStudent(null);
//        Student student = myAdd.student;
//        if(student != null){
//            JOptionPane.showMessageDialog(AddStudent.this,
//                    "Email or Password Invalid teacher",
//                    "Try again",JOptionPane.ERROR_MESSAGE);
//            System.out.println("successful Added Student: "+student.name);
//        }else {
//            System.out.println("Added student form is closed.");
//        }
//    }

}
