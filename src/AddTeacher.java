import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTeacher extends JDialog{
    private JPanel AddTeacherPanel;
    private JTextField tfName;
    private JTextField tfAge;
    private JTextField tfAddress;
    private JTextField tfEmailID;
    private JTextField tfRollNo;
    private JTextField tfFatherName;
    private JTextField tfDOB;
    private JTextField tfPhone;
    private JButton btnSubmit;
    private JButton btnCancel;
    private JComboBox cbDepartment;
    private JPasswordField tfPassword;
    private JComboBox cbSubject;
    private JButton btBack;

    public AddTeacher(JFrame parent){
        super(parent);
        setTitle("Add Teacher");
        setContentPane(AddTeacherPanel);
        setMinimumSize(new Dimension(700,550));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeacher();

                AddTeacher myAdd = new AddTeacher(null);
                Teacher teacher = myAdd.teacher;
                if(teacher != null){
                    JOptionPane.showMessageDialog(AddTeacher.this,
                            "successful Added Teacher:"+teacher.name,
                            "Submit",JOptionPane.INFORMATION_MESSAGE);
//                    System.out.println("successful Added Teacher: "+teacher.name);
                }else {
                    JOptionPane.showMessageDialog(AddTeacher.this,
                            "Added Teacher form is closed.",
                            "Close",JOptionPane.CLOSED_OPTION);
                    new AddTeacher(null).setVisible(false);
//                    System.out.println("Added teacher form is closed.");
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AddTeacher.this,
                        "Do you want to close this page?",
                        "Close",JOptionPane.CLOSED_OPTION);
                dispose();
            }
        });

//        setVisible(true);

        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminMain(null).setVisible(true);
            }
        });
    }

    private void addTeacher() {

        String name = tfName.getText();
        String fatherName = tfFatherName.getText();
        String age = tfAge.getText() ;
        String dob = tfDOB.getText();
        String address = tfAddress.getText();
        String phone = tfPhone.getText();
        String email = tfEmailID.getText();
        String department = (String) cbDepartment.getSelectedItem();
        String rollNo = tfRollNo.getText();
        String password = String.valueOf(tfPassword.getPassword());
        String subject = (String) cbSubject.getSelectedItem();

        if (name.isEmpty() || fatherName.isEmpty() || age.isEmpty() || dob.isEmpty() || address.isEmpty() ||subject.isEmpty()||
                phone.isEmpty()  || email.isEmpty() || department.isEmpty() || rollNo.isEmpty() || password.isEmpty() ){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email );
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Email address is invalid.","Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        teacher = addTeacherToDatabase(name,fatherName,age,dob,address,phone,email,department,rollNo,password,subject);
        if (teacher != null)
            dispose();
        else{
            JOptionPane.showMessageDialog(this, "Failed to submit new student","Try Again",JOptionPane.ERROR_MESSAGE);
        }

    }

    Teacher teacher;
    private Teacher addTeacherToDatabase(String name, String fatherName, String age, String dob, String address, String phone, String email, String department, String rollNo,String password,String subject) {
        Teacher teacher;
        teacher = null;

        final String DB_URL = "jdbc:mysql://localhost/university";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql ;
            sql = "INSERT INTO teachers(name,fatherName,age,dob,address,phone,email,department,rollNo,PASSWORD,subject)"+
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,fatherName);
            preparedStatement.setString(3,age);
            preparedStatement.setString(4,dob);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,phone);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,department);
            preparedStatement.setString(9,rollNo);
            preparedStatement.setString(10,password);
            preparedStatement.setString(11,subject);


            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0 ){
                teacher = new Teacher();
                teacher.name=name;
                teacher.fatherName=fatherName;
                teacher.age=age;
                teacher.dob=dob;
                teacher.address=address;
                teacher.phone=phone;
                teacher.email=email;
                teacher.branch=department;
                teacher.rollNo=rollNo;
                teacher.password=password;
                teacher.subject=subject;

            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return teacher;
    }


//    public static void main(String[] args) {
//        AddTeacher myAdd = new AddTeacher(null);
//        Teacher teacher = myAdd.teacher;
//        if(teacher != null){
//            System.out.println("successful Added Teacher: "+teacher.name);
//        }else {
//            System.out.println("Added teacher form is closed.");
//        }
//    }

}
