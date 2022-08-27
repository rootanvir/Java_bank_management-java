

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import java.lang.*;


public class login
{
	private Font fnt;
	private JLabel luser,lpass,la;
	public JFrame frame_login;
	public JTextField fe_user_login;
	public JPasswordField fe_pass_login;
	public JButton submit_login,btn_clear_login,btn_signup;
	private String loginUser,loginPass;
	private String saveUser,savePass;
	private char[] psd;
	login()
	{
		login_init();
	}
	public void login_init()
	{
		fnt=new Font("Arial",Font.BOLD,30);
		frame_login=new JFrame("Login");
		frame_login.setLocationRelativeTo(null);
		frame_login.setDefaultCloseOperation(frame_login.EXIT_ON_CLOSE);
		frame_login.setResizable(false);

		la=new JLabel("Login");
		luser=new JLabel("Account Number");
		lpass=new JLabel("Passsword");
		la.setBounds(50,50,150,50);
		luser.setBounds(50,150,150,30);
		lpass.setBounds(50,200,150,30);
		fe_user_login=new JTextField();
		fe_pass_login=new JPasswordField();
		la.setFont(fnt);
		
		submit_login=new JButton("Login");
		submit_login.setBounds(250,250,150,40);
		btn_clear_login=new JButton("Clear");
		btn_clear_login.setBounds(50,250,150,40);
		btn_signup=new JButton("Signup");
		btn_signup.setBounds(50,250,150,40);
		
		fe_user_login.setBounds(200,150,200,30);
		fe_pass_login.setBounds(200,200,200,30);

		btn_signup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				new signup();
			}
		});
		
		frame_login.add(la);
		frame_login.add(luser);
		frame_login.add(lpass);
		frame_login.add(fe_user_login);
		frame_login.add(fe_pass_login);
		frame_login.add(submit_login);
		//frame_login.add(btn_clear_login);
		frame_login.add(btn_signup);
		submit_login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eee)
			{
				loginUser=fe_user_login.getText();
				psd=fe_pass_login.getPassword();
				loginPass=String.valueOf(psd);
				File file=new File("userinfo\\"+loginUser+".txt");
				if(!loginUser.equals("")&&!loginPass.equals(""))
				{
					if(file.exists())
					{
						try {
							Scanner reader=new Scanner(file);
							saveUser=reader.nextLine();
							savePass=reader.nextLine();
							if(savePass.equals(loginPass)&&saveUser.equals(loginUser))
							{
								frame_login.dispose();
								new home(loginUser);
							}
							else
							{
								JOptionPane.showMessageDialog(frame_login, "Wrong password", "Message", 0);
							}

							
						} catch (Exception e) 
						{
							JOptionPane.showMessageDialog(frame_login, "Account doesn't exist", "Message", 0);

						}
					}
					else{JOptionPane.showMessageDialog(frame_login, "Account doesn't exist", "Message", 0);}
				}
				else{JOptionPane.showMessageDialog(frame_login, "Please fill the information first", "Message", 2);}


			}
		});
		
		frame_login.setSize(500,500);
		frame_login.setLayout(null);
		frame_login.setVisible(true);
		frame_login.setLocationRelativeTo(null);

	}
	
	
	
	
	public static void main(String[]args)
	{
		new login();
	}
}