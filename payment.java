import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class payment
{
	public JFrame f4;
	private JLabel la1,la2,la3;
	public JTextField fe_acc_payment,fe_amount_payment;
	public JPasswordField pass_payment;
	public JButton submit_payment;
	public String catchPass,catchBalance,catchAcc;
	public char[] pss;
	public String password;
	payment(String acc,String bal,String pass)
	{
		catchAcc=acc;
		catchBalance=bal;
		catchPass=pass;
		payment_init();
	}
	private void payment_init()
	{
		f4=new JFrame();
		f4.setTitle("Payment");
		
		f4.setDefaultCloseOperation(f4.DISPOSE_ON_CLOSE);
		f4.setResizable(false);



				
		la1=new JLabel("Payment Number ");
		la2=new JLabel("Enter amount");
		la3=new JLabel("Enter Password ");
		la1.setBounds(50,50,150,30);
		la2.setBounds(50,100,150,30);
		la3.setBounds(50,150,150,30);
		
		fe_acc_payment=new JTextField();
		fe_acc_payment.setBounds(200,50,150,30);
		fe_amount_payment=new JTextField();
		fe_amount_payment.setBounds(200,100,150,30);
		pass_payment=new JPasswordField();
		pass_payment.setBounds(200,150,150,30);
		
		fe_amount_payment.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
			 }
		});

		submit_payment=new JButton("Submit");
		submit_payment.setBounds(100,250,200,40);
		submit_payment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ee)
			{
				String payacc=fe_acc_payment.getText();
				String amount=fe_amount_payment.getText();
				int ibalan=Integer.valueOf(catchBalance);
				int iamount=Integer.valueOf(amount);
				pss=pass_payment.getPassword();
				password=String.valueOf(pss);
				File file=new File("balance\\"+catchAcc+".txt");
				if(file.exists())
				{
				if(!payacc.equals("")&&!amount.equals("")&&!password.equals(""))
				{

					if(password.equals(catchPass))
					{
						if(ibalan>=iamount)
						{
						try {
						FileWriter wrt=new FileWriter(file);
						ibalan=ibalan-iamount;
						wrt.write(String.valueOf(ibalan));
						wrt.close();
						try 
						{
							
							File file3=new File("history\\"+catchAcc+".txt");
							FileWriter wrt3=new FileWriter(file3,true);
							wrt3.write("Payment\t\t"+amount+"\t\t"+payacc+"\n");
							wrt3.close();
						} 
						catch (Exception e) 
						{

						}
						JOptionPane.showMessageDialog(f4, "Payment successfully completed to "+payacc);
						f4.dispose();
						
						} 
						catch (Exception e) 
						{
							JOptionPane.showMessageDialog(f4, "Fill all the information");

						}
						}
						else
						{
							JOptionPane.showMessageDialog(f4,"Not enough balance");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f4,"Wrong Password");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(f4, "Fill all the information");
				}
			
			}
			else
			JOptionPane.showMessageDialog(f4, "Fill all the information");

			}
		});


		f4.add(fe_acc_payment);
		f4.add(fe_amount_payment);
		f4.add(pass_payment);
		f4.add(la1);
		f4.add(la2);
		f4.add(la3);
		f4.add(submit_payment);
		
		f4.setSize(500,400);
		f4.setLayout(null);
		f4.setVisible(true);
		f4.setLocationRelativeTo(null);
		
		
		
	}

	// public static void main(String[]args)
	// {
	// 	new payment();
	// }
	
}