import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EnterMarks extends JDialog {
    private JPanel submarkPanel;
    private JTextField tfRollNO;
    private JTextField tfSub1;
    private JTextField tfSub2;
    private JTextField tfSub3;
    private JTextField tfSub4;
    private JTextField tfSub5;
    private JTextField tfSub6;
    private JTextField tfMark1;
    private JTextField tfMark2;
    private JTextField tfMark3;
    private JTextField tfMark4;
    private JTextField tfMark5;
    private JTextField tfMark6;
    private JButton btnSubmit;
    private JButton btBack;


    public EnterMarks(JFrame parent){
        super(parent);
        setTitle("Enter Marks");
        setContentPane(submarkPanel);
        setMinimumSize(new Dimension(500,400));
        setModal(true);
        setLocationRelativeTo(submarkPanel);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterMarks();
            }
        });

//        setVisible(true);

        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                String options[]={"Add Marks", "Add Attendance", "Cancel"};
                int res = JOptionPane.showOptionDialog(null,
                        "Choose an option", "OptionDialog",JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,null, options, options[0]);
                if (res==0){
//                    EnterMarks entermark = new EnterMarks(null);
                    setVisible(true);
                }
                if (res==1){
                    Attendance AT = new  Attendance(null);
                    AT.setVisible(true);
                }
                if(res == 2){
                        dispose();
                    Login login=new Login(null);
                    login.setVisible(true);
                }
            }
        });
    }

//    public EnterMarks(){}


    private void enterMarks() {
        String rollNo = tfRollNO.getText();
        String sub1=tfSub1.getText();
        String sub2=tfSub2.getText();
        String sub3=tfSub3.getText();
        String sub4=tfSub4.getText();
        String sub5=tfSub5.getText();
        String sub6=tfSub6.getText();
        String mark1=tfMark1.getText();
        String mark2=tfMark2.getText();
        String mark3=tfMark3.getText();
        String mark4=tfMark4.getText();
        String mark5=tfMark5.getText();
        String mark6=tfMark6.getText();

        if(sub1.isEmpty()||sub2.isEmpty()||sub3.isEmpty()||sub4.isEmpty()||sub5.isEmpty()||sub6.isEmpty()||
                mark1.isEmpty()||mark2.isEmpty()||mark3.isEmpty()||mark4.isEmpty()||mark5.isEmpty()||mark6.isEmpty()){

            JOptionPane.showMessageDialog(EnterMarks.this,
                    "Please, Enter all fields",
                    "Try again",JOptionPane.ERROR_MESSAGE);
        }

        enterSubAndMarksToDB(rollNo,sub1,sub2,sub3,sub4,sub5,sub6,mark1,mark2,mark3,mark4,mark5,mark6);
    }

    private void enterSubAndMarksToDB(String rollNo, String sub1, String sub2, String sub3, String sub4, String sub5, String sub6,
    String mark1,String mark2,String mark3,String mark4,String mark5,String mark6) {
        final String DB_RUL = "jdbc:mysql://localhost/university";
        final String USERNAME="root";
        final String PASSWORD="";

        try{

            Connection conn= DriverManager.getConnection(DB_RUL,USERNAME,PASSWORD);

            Statement stmt=conn.createStatement();
            String sql1,sql2;
            sql1="INSERT INTO subjects(rollNo,subject_1,subject_2,subject_3,subject_4,subject_5,subject_6)"+"VALUES(?,?,?,?,?,?,?)";
            sql2="INSERT INTO marks(rollNo,mark_1,mark_2,mark_3,mark_4,mark_5,mark_6)"+"VALUES(?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement1=conn.prepareStatement(sql1);
            preparedStatement1.setString(1,rollNo);
            preparedStatement1.setString(2,sub1);
            preparedStatement1.setString(3,sub2);
            preparedStatement1.setString(4,sub3);
            preparedStatement1.setString(5,sub4);
            preparedStatement1.setString(6,sub5);
            preparedStatement1.setString(7,sub6);

            PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
            preparedStatement2.setString(1,rollNo);
            preparedStatement2.setString(2,mark1);
            preparedStatement2.setString(3,mark2);
            preparedStatement2.setString(4,mark3);
            preparedStatement2.setString(5,mark4);
            preparedStatement2.setString(6,mark5);
            preparedStatement2.setString(7,mark6);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();

            JOptionPane.showMessageDialog(null,"Marks inserted successfully");

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


//    public static void main(String []args){
//        EnterMarks enterMarks = new EnterMarks(null);
//    }
}
