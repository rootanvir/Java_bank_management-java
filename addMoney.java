import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class addMoney
{
	public JFrame f1;
	private JLabel la1,la2,la3;
	public JTextField fe_acc_add,fe_amount_add;
	public JPasswordField pass_add;
	public JButton submit_add;
	private String catchUser,catchPass,v_acc,v_amount,v_pass;
	private char[] pss;
	private String balan;
	private int ibalan,iamount;
	
	addMoney(String psd,String acc)
	{
		catchUser=acc;
		catchPass=psd;
		add_init();
	}
	private void add_init()
	{
		f1=new JFrame();
		f1.setTitle("Add Money");
		
		f1.setDefaultCloseOperation(f1.DISPOSE_ON_CLOSE);
		f1.setResizable(false);
		la1=new JLabel("Credit card number ");
		la2=new JLabel("Enter amount");
		la3=new JLabel("Enter Password ");
		la1.setBounds(50,50,150,30);
		la2.setBounds(50,100,150,30);
		la3.setBounds(50,150,150,30);
		
		fe_acc_add=new JTextField();
		fe_acc_add.setBounds(200,50,150,30);
		fe_amount_add=new JTextField();
		fe_amount_add.setBounds(200,100,150,30);
		pass_add=new JPasswordField();
		pass_add.setBounds(200,150,150,30);
		
		fe_amount_add.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();  // if it's not a number, ignore the event
				}
			 }
		});



		submit_add=new JButton("Submit");
		submit_add.setBounds(100,250,200,40);
		
		submit_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				v_acc=fe_acc_add.getText();
				v_amount=fe_amount_add.getText();
				pss=pass_add.getPassword();
				v_pass=String.valueOf(pss);
				iamount=Integer.valueOf(v_amount);
				
				if(!v_acc.equals("")&&!v_amount.equals("")&&!v_pass.equals(""))
				{
					if(v_pass.equals(catchPass))
					{
						if(iamount<100001&&iamount>0)
						{
						try {
							File file=new File("balance\\"+catchUser+".txt");
							Scanner read= new Scanner(file);
							balan=read.nextLine();
							System.out.println(balan);
							ibalan=Integer.valueOf(balan);
							ibalan=ibalan+iamount;
							System.out.println(ibalan);
							File file2=new File("balance\\"+catchUser+".txt");
							FileWriter wrt=new FileWriter(file2);
							wrt.write(String.valueOf(ibalan));
							wrt.close();
							read.close();

							try 
							{
								File file3=new File("history\\"+catchUser+".txt");
								FileWriter wrt3=new FileWriter(file3,true);
								wrt3.write("Add Money\t\t"+v_amount+"\t\t"+v_acc+"\n");
								wrt3.close();
							} 
							catch (Exception iee) 
							{
	
							}

							JOptionPane.showMessageDialog(f1,"Money Added Successfully to  "+catchUser,"Success",1);
							f1.dispose();
							
						} catch (Exception ee) {
							
							JOptionPane.showMessageDialog(f1,"Add money limit 10000");

						}
						}
						else
						{
							JOptionPane.showMessageDialog(f1,"Add money limit is 100000");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f1,"Wrong password","Message",2);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(f1, "Field are empty");
				}
			}
		});


		f1.add(fe_acc_add);
		f1.add(fe_amount_add);
		f1.add(pass_add);
		f1.add(la1);
		f1.add(la2);
		f1.add(la3);
		f1.add(submit_add);
		
		f1.setSize(500,500);
        f1.setLocationRelativeTo(null);
		f1.setLayout(null);
		f1.setVisible(true);
		
		
		
	}

	
	
}