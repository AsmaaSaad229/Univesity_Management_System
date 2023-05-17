import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JDialog {
    private JPanel LoginPanel;
    private JTextField tfEmail;
    private JButton btnOk;
    private JButton btnCancel;
    private JPasswordField pfPassword;
    private JRadioButton adminRadioButton;
    private JRadioButton studentRadioButton;
    private JRadioButton teacherRadioButton;

    public Login (JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(500,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ButtonGroup group = new ButtonGroup();
        group.add(adminRadioButton);
        group.add(studentRadioButton);
        group.add(teacherRadioButton);

        btnOk.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
//               if(adminRadioButton.isSelected()==false&&studentRadioButton.isSelected()==false&&teacherRadioButton.isSelected()==false)
//                    JOptionPane.showMessageDialog(Login.this,
//                            "Who are you? ",
//                            "Try again",JOptionPane.ERROR_MESSAGE);
//                selection = (String) cbWho.getSelectedItem();
//                if(selection.isEmpty()){
//                    JOptionPane.showMessageDialog(Login.this,
//                            "Who are you? ",
//                            "Try again",JOptionPane.ERROR_MESSAGE);
//                }


                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

//                student = getAuthenticatedUser(email,password);
//                if(student!= null)
//                    dispose();
//                else
//                    JOptionPane.showMessageDialog(Login.this,
//                            "Email or Password Invalid student",
//                            "Try again",JOptionPane.ERROR_MESSAGE);

                teacher = getAuthenticatedUser(email,password);
                if(teacher!= null)
                    dispose();
                else
                    JOptionPane.showMessageDialog(Login.this,
                            "Email or Password Invalid teacher",
                            "Try again",JOptionPane.ERROR_MESSAGE);
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
    String selection;
//        &&studentRadioButton.isSelected()==false&&teacherRadioButton.isSelected()==false)

    public String actionPerformed (){
        if(adminRadioButton.isSelected()==true)
            selection="Admin";
//        if(ee.getSource() == adminRadioButton)
        else if(studentRadioButton.isSelected()==true) {
            selection="Student";
        }
//         else if (ee.getSource() ==studentRadioButton)
        else if(teacherRadioButton.isSelected()==true)
            selection="Teacher";
        else
            JOptionPane.showMessageDialog(Login.this,
                    "Select .Who are you",
                    "Try again",JOptionPane.ERROR_MESSAGE);
        return selection;
   }


//    public Student student;
    public Teacher teacher;

    private Teacher getAuthenticatedUser(String email, String password){
        Teacher teacher=null;
        selection=actionPerformed();
        final String DB_URL = "jdbc:mysql://localhost/university";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();

            if(selection == "Admin"){

                String sql ;
                sql = "SELECT * FROM `admin` WHERE email=? AND password=?";

                PreparedStatement preparedStatement=conn.prepareStatement(sql);

                preparedStatement.setString(1,email);
                preparedStatement.setString(2,password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet .next() ){
                    new AdminMain(null).setVisible(true);
                    new Login(null).dispose();
                }
            } else if (selection=="Student") {
                String sql2 ;
                sql2 = "SELECT * FROM `student` WHERE email=? AND password=?";

                PreparedStatement preparedStatement2=conn.prepareStatement(sql2);

                preparedStatement2.setString(1,email);
                preparedStatement2.setString(2,password);

                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if(resultSet2 .next() ){
                    String options[]={"Result", "Fee", "Cancel"};
                    int res = JOptionPane.showOptionDialog(Login.this,
                            "Choose an option", "OptionDialog",JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE,null, options, options[0]);
                    if (res==0){
                        new Login(null).dispose();
                        new StudentResult(null).setVisible(true);
                    }
                    if (res==1){
                        new Login(null).dispose();
                        new  Fee(null).setVisible(true);
                    }
                    if(res == 2){
                        new Login(null).setVisible(true);
                    }


////                    setVisible(false);
//                    new StudentResult(null).setVisible(true);
//                    new Login(null).dispose();

//                    teacher = new Student();
//                    teacher.email=resultSet2.getString("email");
//                    teacher.name=resultSet2.getString("name");
//                    ((Student) teacher).acadimicYear=resultSet2.getString("acadimicYear");

                }
            } else if (selection=="Teacher") {
                String sql3 ;
                sql3 = "SELECT * FROM `teachers` WHERE email=? AND password=?";

                PreparedStatement preparedStatement3=conn.prepareStatement(sql3);

                preparedStatement3.setString(1,email);
                preparedStatement3.setString(2,password);

                ResultSet resultSet3 = preparedStatement3.executeQuery();
                if(resultSet3.next() ){

                    String options[]={"Add Marks", "Add Attendance", "Cancel"};
                    int res = JOptionPane.showOptionDialog(Login.this,
                            "Choose an option", "OptionDialog",JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE,null, options, options[0]);
                    if (res==0){
                        new Login(null).dispose();
                        EnterMarks entermark = new EnterMarks(null);
                        entermark.setVisible(true);
                    }
                    if (res==1){
                        new Login(null).dispose();
                        Attendance AT = new  Attendance(null);
                        AT.setVisible(true);
                    }
                    if(res == 2){
                        new Login(null).setVisible(true);
                    }
//                    setVisible(false);
//                    new EnterMarks(null).setVisible(true);
                    teacher= new Teacher();
                    teacher.email=resultSet3.getString("email");
                    teacher.name=resultSet3.getString("name");
                    teacher.branch=resultSet3.getString("department");

                }

            }

            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }


    public static void main(String[] args){
        Login login=new Login(null);
//        if(student!=null) {
//            System.out.println("Successful Authentication of: " + student.name +
//                    "\nEmail: " + student.email +
//                    "\nPhone: " + student.acadimicYear +
//                    "\nAddress: " + student.branch);
////        } else if (teacher!=null) {
////            System.out.println("Successful Authentication of: "+teacher.name+
////                      "\nEmail: "+ teacher.email+
////                      "\nPhone: "+teacher.branch);
//        }else
//            System.out.println("Authentication Canceled");

        Teacher teacher = login.teacher;
//        Student student = (Student) login.teacher;

        if (teacher!=null) {
            System.out.println("Successful Authentication of: "+teacher.name+
                      "\nEmail: "+ teacher.email+
//                    "\nPhone: " + student.acadimicYear +
                    "\nPhone: "+teacher.branch);
        }else
            System.out.println("Authentication Canceled");

    }

//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }

}
