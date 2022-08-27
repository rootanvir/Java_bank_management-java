import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;

public class recharge
{
	public JFrame f5;
	private JLabel la1,la2,la3;
	public JTextField fe_acc_recharge,fe_amount_recharge;
	public JPasswordField pass_recharge;
	public JButton submit_recharge;
	public JRadioButton gram,bang,robi,airtel;
	public ButtonGroup grp_sim;
	public String simSel;
	public String catchAcc,catchPass,catchBalance;
	public char[] pss;
	recharge(String acc,String pass,String bal)
	{
		catchAcc=acc;
		catchPass=pass;
		catchBalance=bal;
		recharge_init();
	}
	private void recharge_init()
	{
		f5=new JFrame();
		f5.setTitle("Recharge");
		f5.setDefaultCloseOperation(f5.DISPOSE_ON_CLOSE);
		f5.setResizable(false);
				
		la1=new JLabel("Phone Number ");
		la2=new JLabel("Enter amount");
		la3=new JLabel("Enter Password ");
		la1.setBounds(50,200,150,30);
		la2.setBounds(50,250,150,30);
		la3.setBounds(50,300,150,30);
		
		fe_acc_recharge=new JTextField();
		fe_acc_recharge.setBounds(200,200,150,30);
		fe_amount_recharge=new JTextField();
		fe_amount_recharge.setBounds(200,250,150,30);
		pass_recharge=new JPasswordField();
		pass_recharge.setBounds(200,300,150,30);

		fe_amount_recharge.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
			 }
		});
		
		submit_recharge=new JButton("Submit");
		
		grp_sim=new ButtonGroup();
		submit_recharge.setBounds(100,350,200,40);
		gram=new JRadioButton("Grameenphone");
		bang=new JRadioButton("Banglalink");
		robi=new JRadioButton("Robi");
		airtel=new JRadioButton("Airtel");
		gram.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				simSel="grameenphone";
			}
		});
		bang.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				simSel="BanglaLink";
			}
		});
		robi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				simSel="Robi";
			}
		});
		airtel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				simSel="Airtel";
			}
		});


		submit_recharge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ee)
			{
				String number=fe_acc_recharge.getText();
				String amount=fe_amount_recharge.getText();
				pss=pass_recharge.getPassword();
				String password=String.valueOf(pss);
				int ibalan=Integer.valueOf(catchBalance);
				int iamount=Integer.valueOf(amount);

				File file=new File("balance\\"+catchAcc+".txt");
				if(file.exists())
				{
					if(!number.equals("")&&!amount.equals("")&&!password.equals("")&&!simSel.equals(null))
					{
						if(password.equals(catchPass))
						{
							if(ibalan>=iamount)
							{

							try
							{
								FileWriter wrt=new FileWriter(file);
								ibalan=ibalan-iamount;
								wrt.write(String.valueOf(ibalan));
								wrt.close();
								try 
								{
									File file2=new File("history\\"+catchAcc+".txt");
									FileWriter wrt2=new FileWriter(file2,true);
									wrt2.write("Recharge\t\t"+amount+"\t\t"+number+"\n");
									wrt2.close();
								} 
								catch (Exception e) 
								{
		
								}
								JOptionPane.showMessageDialog(f5,"Successfully recharge a "+simSel+" number "+number);
								f5.dispose();
							}
							catch (Exception e) {
								JOptionPane.showMessageDialog(f5,"Fill the information first");

							}
							}
							else
							{
								JOptionPane.showMessageDialog(f5,"Not enough balance");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(f5,"Wrong password");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f5,"Fill the information first");

					}

				}
				else
				{
					JOptionPane.showMessageDialog(f5,"Fill the information first");
				}
				
			}
		});






		
		gram.setBounds(50,100,150,30);
		bang.setBounds(200,100,150,30);
		robi.setBounds(50,150,150,30);
		airtel.setBounds(200,150,150,30);
		
		
		grp_sim.add(gram);
		grp_sim.add(bang);
		grp_sim.add(robi);
		grp_sim.add(airtel);
		
		f5.add(fe_acc_recharge);
		f5.add(fe_amount_recharge);
		f5.add(pass_recharge);
		f5.add(la1);
		f5.add(la2);
		f5.add(la3);
		f5.add(submit_recharge);
		f5.add(gram);
		f5.add(bang);
		f5.add(robi);
		f5.add(airtel);
		
		f5.setSize(500,500);
		f5.setLayout(null);
		f5.setVisible(true);
		f5.setLocationRelativeTo(null);
		
		
		
	}

	// public static void main(String[]args)
	// {
	// 	new recharge();
	// }
	
}