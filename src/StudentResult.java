import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class StudentResult  extends JDialog {
    private JPanel srPanel;
    private JTable table1;
    private JTextField tfRollNum;
    private JButton button1;
    private JButton btBack;

    public StudentResult (JFrame parent) {
        super(parent);
        setTitle("Student Result");
        setContentPane(srPanel);
        setMinimumSize(new Dimension(500, 300));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createTable();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getResult();
            }
        });
//        setVisible(true);
        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                String options[]={"Result", "Fee", "Cancel"};
                int res = JOptionPane.showOptionDialog(null,
                        "Choose an option", "OptionDialog",JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,null, options, options[0]);
                if (res==0){
                    new StudentResult(null).setVisible(true);
                    new Login(null).dispose();
                }
                if (res==1){
                    new  Fee(null).setVisible(true);
                    new Login(null).dispose();
                }
                if(res == 2){
                    new Login(null).setVisible(true);
                }
            }
        });
    }

//    public StudentResult(){
//        createTable();
//    }
    public JPanel getSrPanel(){
        return srPanel;
    }
    String m1,s1,s2,s3,s4,s5,s6,m2,m3,m4,m5,m6;

    void getResult(){
        String tRollNo = tfRollNum.getText();

        final String DB_URL = "jdbc:mysql://localhost/university";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            ResultSet rs = conn.prepareStatement("select * from marks where rollNo = '"+tRollNo+"'").executeQuery();
            while(rs.next()) {
                m1 = rs.getString("mark_1");
                m2 = rs.getString("mark_2");
                m3 = rs.getString("mark_3");
                m4 = rs.getString("mark_4");
                m5 = rs.getString("mark_5");
                m6 = rs.getString("mark_6");
            }
                Connection conn2 = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                ResultSet rs2 = conn2.prepareStatement("select * from subjects where rollNo = '"+tRollNo+"'").executeQuery();
                while(rs2.next()){
                    s1 = rs2.getString("subject_1");
                    s2 = rs2.getString("subject_2");
                    s3 = rs2.getString("subject_3");
                    s4 = rs2.getString("subject_4");
                    s5 = rs2.getString("subject_5");
                    s6 = rs2.getString("subject_6");
                createTable();
//                tfFatherName.setText(rs.getString("fatherName"));
            }
        }catch(Exception e){}

    }
    Object[][] data;
    private void createTable(){
        Object[][] data ={{"Subject","Result"},{s1,m1},{s2,m2},{s3,m3},{s4,m4},{s5,m5},{s6,m6}};
        table1.setModel(new DefaultTableModel(data,new String[]{"Subject","Result"}));

    }

    public static void main(String[] args){
        StudentResult StudentResult=new StudentResult(null);
    }

}
