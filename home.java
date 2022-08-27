import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class home 
{
	private JFrame f_home;
	public JButton btn_refresh,btn_home,btn_addMoney,btn_sendMoney,btn_payment,btn_recharge,btn_withdraw,btn_history,btn_logout,btn_changePass;
	public String userFromlogin,psd;
	public JLabel balance,l_name,l_phone,l_blood,l_gender;
	public JLabel sw_name,sw_phone,sw_blood,sw_gender;
	public JTextField sw_balance;
	private String value_balance,name,phone,blood,gender;
	
	home(String user)
	{
		userFromlogin=user;
		init();
	}
	public void init()
	{

			f_home=new JFrame("Home");
			f_home.setLayout(null);
			f_home.setDefaultCloseOperation(f_home.DO_NOTHING_ON_CLOSE);
			f_home.setResizable(false);



			btn_home=new JButton("HOME");
			btn_addMoney=new JButton("ADD MONEY");
			btn_sendMoney=new JButton("SEND MONEY");
			btn_payment=new JButton("PAYMENT");
			btn_recharge=new JButton("RECHARGE ");
			btn_withdraw=new JButton("WITHDRAW");
			btn_history=new JButton("HISTORY");
			btn_refresh=new JButton("Refresh Balance");
			btn_changePass=new JButton("Change password");


			btn_logout=new JButton("Logout");
			btn_logout.setBounds(550,0,100,35);
			btn_logout.setBackground(new  Color(224, 65, 47));
			btn_logout.setFocusPainted(false);
			btn_logout.setForeground(Color.white);
			btn_logout.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent ee)
				{
					int x=JOptionPane.showConfirmDialog(f_home, "Want to logout?", "Confirm logout",JOptionPane.YES_NO_OPTION);
					if(x==0)
					{
						f_home.dispose();
						new login();
					}
					
				}
			});	


			balance=new JLabel("Balance");
			l_name=new JLabel("Name");
			l_phone=new JLabel("Phone");
			l_blood=new JLabel("Blood");
			l_gender=new JLabel("Gender");
			try {
				File file=new File("balance\\"+userFromlogin+".txt");
				if(!file.exists())
				{
				FileWriter wrt=new FileWriter(file);
				wrt.write("00");
				wrt.close();
				}

			} catch (Exception e) {
				
			}

			try {
				File file=new File("userinfo\\"+userFromlogin+".txt");
				Scanner read=new Scanner(file);
				
				phone=read.nextLine();
				psd=read.nextLine();
				name=read.nextLine();
				blood=read.nextLine();
				blood=read.nextLine();
				blood=read.nextLine();
				blood=read.nextLine();
				blood=read.nextLine();
				gender=read.nextLine();
				read.close();

				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				File file2=new File("balance\\"+phone+".txt");
				Scanner rd2=new Scanner(file2);
				value_balance=rd2.nextLine();
				rd2.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			btn_refresh.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ew)
				{
					try {
						File file2=new File("balance\\"+phone+".txt");
						Scanner rd2=new Scanner(file2);
						value_balance=rd2.nextLine();
						rd2.close();
						sw_balance.setText(value_balance);
						
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			});
			try {
				File filew=new File("history\\"+phone+".txt");
				if(!filew.exists())
				{
					FileWriter wrt=new FileWriter(filew);
					wrt.close();
				}
				
			} catch (Exception e) {

			}

			



			sw_balance=new JTextField(value_balance);
			sw_name=new JLabel(name);
			sw_phone=new JLabel(phone);
			sw_blood=new JLabel(blood);
			sw_gender=new JLabel(gender);
			sw_balance.setEditable(false);
			

			balance.setBounds(200,100,100,30);
			l_name.setBounds(200,150,100,30);
			l_phone.setBounds(200,200,100,30);
			l_blood.setBounds(200,250,100,30);
			l_gender.setBounds(200,300,100,30);
			
			
			btn_refresh.setBounds(500,100,150,30);
			sw_balance.setBounds(300,100,200,30);
			sw_name.setBounds(300,150,200,30);
			sw_phone.setBounds(300,200,200,30);
			sw_blood.setBounds(300,250,200,30);
			sw_gender.setBounds(300,300,200,30);
			

			btn_home.setBounds(0,50,150,50);
			btn_addMoney.setBounds(0,100,150,50);
			btn_sendMoney.setBounds(0,150,150,50);
			btn_payment.setBounds(0,200,150,50);
			btn_recharge.setBounds(0,250,150,50);
			btn_withdraw.setBounds(0,300,150,50);
			btn_history.setBounds(0,350,150,50);
			btn_changePass.setBounds(0,400,150,50);
			
			btn_home.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

				}
			});
			btn_addMoney.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new addMoney(psd,userFromlogin);
					
				}
			});
			btn_sendMoney.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new sendMoney(userFromlogin,psd,value_balance);
				}
			});
			btn_recharge.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new recharge(userFromlogin,psd,value_balance);
				}
			});
			btn_payment.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new payment(userFromlogin,value_balance,psd);
				}
			});
			btn_history.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new history(userFromlogin);
				}
			});
			btn_withdraw.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new withdraw(userFromlogin,psd,value_balance);
				}
			});
			btn_changePass.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new changePass(userFromlogin,psd);
				}
			});

			
			f_home.add(btn_home);
			f_home.add(btn_addMoney);
			f_home.add(btn_sendMoney);
			f_home.add(btn_recharge);
			f_home.add(btn_withdraw);
			f_home.add(btn_history);
			f_home.add(btn_payment);
			f_home.add(balance);
			f_home.add(l_name);
			f_home.add(l_phone);
			f_home.add(l_blood);
			f_home.add(l_gender);
			f_home.add(sw_balance);
			f_home.add(sw_name);
			f_home.add(sw_phone);
			f_home.add(sw_blood);
			f_home.add(sw_gender);
			f_home.add(btn_refresh);
			f_home.add(btn_logout);
			f_home.add(btn_changePass);

			f_home.setSize(700,500);
			f_home.setLayout(null);
			f_home.setLocationRelativeTo(null);
			f_home.setVisible(true);
		
	}

	

}
