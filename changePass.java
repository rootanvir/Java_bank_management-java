import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

public class changePass 
{
    public JFrame f10;
    private JLabel l_old,l_new,l_confirm;
    private JPasswordField fe_oldPass,fe_newPass,fe_conPass;
    private JButton submit_changePass;
    private String catchAcc,catchPass;
    private String phone,name,dob,present,permanent,nid,blood,gender;

    changePass(String acc,String pass)
    {
        catchAcc=acc;
        catchPass=pass;
        initPass();
    }
    public void initPass()
    {
        f10=new JFrame("Change Password");
        f10.setDefaultCloseOperation(f10.DISPOSE_ON_CLOSE);
        f10.setResizable(false);
        l_old=new JLabel("Enter Old password ");
        l_new=new JLabel("Enter new password ");
        l_confirm=new JLabel("Enter new Confirm password ");

        fe_oldPass=new JPasswordField();
        fe_newPass=new JPasswordField();
        fe_conPass=new JPasswordField();

        l_old.setBounds(50,50,200,30);
		l_new.setBounds(50,100,200,30);
		l_confirm.setBounds(50,150,200,30);

        fe_oldPass.setBounds(250,50,200,30);
        fe_newPass.setBounds(250,100,200,30);
        fe_conPass.setBounds(250,150,200,30);

        submit_changePass=new JButton("Change Password");
        submit_changePass.setBounds(150,250,150,40);

        submit_changePass.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                


                char[] oldpss,newpss,conpss;
                oldpss=fe_oldPass.getPassword();
                newpss=fe_newPass.getPassword();
                conpss=fe_conPass.getPassword();
                String oldPass,newPass,conPass;
                oldPass=String.valueOf(oldpss);
                newPass=String.valueOf(newpss);
                conPass=String.valueOf(conpss);
                try {
                    File file=new File("userinfo\\"+catchAcc+".txt");
                    Scanner reader=new Scanner(file);
                    phone=reader.nextLine();
                    name=reader.nextLine();
                    name=reader.nextLine();
                    dob=reader.nextLine();
                    present=reader.nextLine();
                    permanent=reader.nextLine();
                    nid=reader.nextLine();
                    blood=reader.nextLine();
                    gender=reader.nextLine();
                    
                } catch (Exception ee) 
                {
                    
                }
                if(!oldPass.equals("")&&!newPass.equals("")&&!conPass.equals(""))
                {
                    if(catchPass.equals(oldPass))
                    {
                        if(newPass.equals(conPass))
                        {
                            if(!newPass.equals(oldPass))
                            {
                            try {
                                File file2=new File("userinfo\\"+catchAcc+".txt");
                                FileWriter wrt2=new FileWriter(file2);
                                wrt2.write(phone+"\n");
                                wrt2.write(newPass+"\n");
                                wrt2.write(name+"\n");
                                wrt2.write(dob+"\n");
                                wrt2.write(present+"\n");
                                wrt2.write(permanent+"\n");
                                wrt2.write(nid+"\n");
                                wrt2.write(blood+"\n");
                                wrt2.write(gender+"\n");
                                wrt2.close();
                                JOptionPane.showMessageDialog(f10,"Password changed successfully");
                                f10.dispose();

                                
                                
                            } catch (Exception ei) {
                                
                            }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(f10,"Old Password and new Passsword are same");
                            }

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(f10,"new password and confirm password are not matching");
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(f10,"Old password is not matching");

                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(f10,"Field are empty");
                }
            }
        });
        



        f10.add(l_old);
        f10.add(l_new);
        f10.add(l_confirm);
        f10.add(fe_oldPass);
        f10.add(fe_newPass);
        f10.add(fe_conPass);
        f10.add(submit_changePass);

        f10.setSize(500,500);
        f10.setLocationRelativeTo(null);
		f10.setLayout(null);
		f10.setVisible(true);
		

    }
  

}
