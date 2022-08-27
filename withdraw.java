import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class withdraw
{
	public JFrame f3;
	private JLabel la1,la2,la3;
	public JTextField fe_amount_withdraw;
	public JPasswordField pass_withdraw;
	public JButton submit_withdraw;
	private String catchPass,catchAcc,catchBalance;
	public char[] pss;
	withdraw(String acc,String pass,String bal)
	{
		catchAcc=acc;
		catchPass=pass;
		catchBalance=bal;
		withdraw_init();
	}
	private void withdraw_init()
	{
		f3=new JFrame();
		f3.setTitle("Money Withdraw");
		f3.setDefaultCloseOperation(f3.DISPOSE_ON_CLOSE);
		f3.setResizable(false);

				

		
		la2=new JLabel("Enter amount");
		la3=new JLabel("Enter Password ");
		
		la2.setBounds(50,100,150,30);
		la3.setBounds(50,150,150,30);
		
		
		fe_amount_withdraw=new JTextField();
		fe_amount_withdraw.setBounds(200,100,150,30);
		pass_withdraw=new JPasswordField();
		pass_withdraw.setBounds(200,150,150,30);
		fe_amount_withdraw.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
			 }
		});
		submit_withdraw=new JButton("Submit");
		submit_withdraw.setBounds(100,250,200,40);
		
		submit_withdraw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ee)
			{
				String amount=fe_amount_withdraw.getText();
				pss=pass_withdraw.getPassword();
				String password=String.valueOf(pss);
				
				File file=new File("balance\\"+catchAcc+".txt");
				if(file.exists())
				{
				if(!amount.equals("")&&!password.equals(""))
				{
					int ibalan=Integer.valueOf(catchBalance);
					int iamount=Integer.valueOf(amount);
					
					if(ibalan>=iamount)
					{
					if(catchPass.equals(password))
					{
						try {
							FileWriter wrt=new FileWriter(file);
							ibalan=ibalan-iamount;
							wrt.write(String.valueOf(ibalan));
							wrt.close();
							if(iamount%500==0)
							{
								if(iamount<=50000)
								{
							try 
							{
								File file2=new File("history\\"+catchAcc+".txt");
								FileWriter wrt2=new FileWriter(file2,true);
								wrt2.append("Withdraw\t\t"+amount+"\t\t"+catchAcc+"\n");
								wrt2.close();
							} 
							catch (Exception e) 
							{

							}
							JOptionPane.showMessageDialog(f3,"Successfully withdraw "+String.valueOf(iamount)+"    From "+catchAcc);
							f3.dispose();
								}
								else
								{
									JOptionPane.showMessageDialog(f3,"WithDraw Limit is 50,000");	
								}
							}
							else
							{
								JOptionPane.showMessageDialog(f3,"Only withdraw multification of 500");
							}

						} catch (Exception e) {
							JOptionPane.showMessageDialog(f3,"Empty field");
						}

					}
					else
					{
						JOptionPane.showMessageDialog(f3,"Wrong Password");
					}
					}
					else
					{
						JOptionPane.showMessageDialog(f3,"Not enough Balance");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(f3,"Fill all information");
				}
			}
			else
			JOptionPane.showMessageDialog(f3,"Fill all information");

			}
		});


		
		f3.add(fe_amount_withdraw);
		f3.add(pass_withdraw);
		f3.add(la2);
		f3.add(la3);
		f3.add(submit_withdraw);
		
		f3.setSize(500,500);
		f3.setLocationRelativeTo(null);
		f3.setLayout(null);
		f3.setVisible(true);
		
		
		
	}

	// public static void main(String[]args)
	// {
	// 	new withdraw();
	// }
	
}