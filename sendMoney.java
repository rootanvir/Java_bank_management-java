import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class sendMoney
{
	public JFrame f2;
	private JLabel la1,la2,la3;
	public JTextField fe_acc_send,fe_amount_send;
	public JPasswordField pass_send;
	public JButton submit_send;
	private char[] psd;
	private String balan;
	private String accSend,amountSend,password,catchUser,catchPass,catchBalance;
	private int ibalan,iamount;
	private int i2balan,i2amount;
	sendMoney(String acc,String psd,String bal)
	{
		catchBalance=bal;
		catchUser=acc;
		catchPass=psd;
		send_init();
	}
	private void send_init()
	{
		f2=new JFrame();
		f2.setTitle("Send Money");
		
		f2.setDefaultCloseOperation(f2.DISPOSE_ON_CLOSE);
		f2.setResizable(false);

	
		

		la1=new JLabel("Acccount Number ");
		la2=new JLabel("Enter amount");
		la3=new JLabel("Enter Password ");
		la1.setBounds(50,50,150,30);
		la2.setBounds(50,100,150,30);
		la3.setBounds(50,150,150,30);
		
		fe_acc_send=new JTextField();
		fe_acc_send.setBounds(200,50,150,30);
		fe_amount_send=new JTextField();
		fe_amount_send.setBounds(200,100,150,30);
		pass_send=new JPasswordField();
		pass_send.setBounds(200,150,150,30);
		fe_amount_send.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
			 }
		});
		submit_send=new JButton("Submit");
		submit_send.setBounds(100,250,200,40);
		submit_send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ee)
			{
				accSend=fe_acc_send.getText();
				amountSend=fe_amount_send.getText();
				psd=pass_send.getPassword();
				password=String.valueOf(psd);

				
				System.out.println(accSend);
				System.out.println(amountSend);
				System.out.println(password);
				if(!accSend.equals("")&&!amountSend.equals("")&&!password.equals(""))
				{
					File file=new File("balance\\"+accSend+".txt");
					if(file.exists())
					{
						if(password.equals(catchPass))
						{
						if(ibalan>=iamount)
						{
						try 
						{
							
							Scanner read=new Scanner(file);
							balan=read.nextLine();
							read.close();
							ibalan=Integer.valueOf(balan);
							iamount=Integer.valueOf(amountSend);
							
							ibalan=ibalan+iamount;
							FileWriter wrt=new FileWriter(file);
							wrt.write(String.valueOf(ibalan));
							wrt.close();
							try 
							{
								File file2=new File("history\\"+catchUser+".txt");
								FileWriter wrt2=new FileWriter(file2,true);
								wrt2.write("SendMoney\t\t"+amountSend+"\t\t"+accSend+"\n");
								wrt2.close();
							} 
							catch (Exception e) 
							{

							}
							JOptionPane.showMessageDialog(f2,"Money Successfully sent to  "+accSend);
							f2.dispose();
						

						} 
						catch (Exception e) {

						}
						}
						else
						{
							JOptionPane.showMessageDialog(f2,"Not enough balance");
						}
							}
							else
							{
								JOptionPane.showMessageDialog(f2,"Wrong Password");
							}

					}
					else
					{
						JOptionPane.showMessageDialog(f2,"Account doesn't exist");
					}
					File file2=new File("balance\\"+catchUser+".txt");
					i2balan=Integer.valueOf(catchBalance);
					i2amount=Integer.valueOf(amountSend);
					
					if(i2balan>=i2amount)
					{
					try 
					{
						
						i2balan=i2balan-i2amount;
						FileWriter wrt2=new FileWriter(file2);
						wrt2.write(String.valueOf(i2balan));
						wrt2.close();

					} catch (Exception e) {
						JOptionPane.showMessageDialog(f2, "Fill the information");


					}
					}
					else
					{
						JOptionPane.showMessageDialog(f2, "Not enough Balance");

					}
				}
				else
				{
					JOptionPane.showMessageDialog(f2, "Fill the information");
				}




			}
		});
		f2.add(fe_acc_send);
		f2.add(fe_amount_send);
		f2.add(pass_send);
		f2.add(la1);
		f2.add(la2);
		f2.add(la3);
		f2.add(submit_send);
		
		f2.setSize(500,500);
		f2.setLocationRelativeTo(null);
		f2.setLayout(null);
		f2.setVisible(true);
		
		
		
	}

	// public static void main(String[]args)
	// {
	// 	new sendMoney();
	// }
	
}