import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class signup
{
	public JFrame frame_signup;
	public JLabel la,lname,ldob,lpstadd,lperadd,lnid,lblood,lgender,lphone,lpass,lconfirmpass;
	public JTextField signup_name,signup_dob,signup_pstadd,signup_peradd,signup_nid,signup_blood,signup_gender,signup_phone;
	public JPasswordField signup_pass,signup_confirmpass;
	public JButton btn_signup;
    public JRadioButton r_male,r_female;
	public ButtonGroup grpGender;
	private Font fnt2;
	public String name,dob,pstadd,peradd,nid,blood,gender,phone,pass,confirmpass;
	public char[] pss,conpass;
	
	signup()
	{
		signup_init();
	}
	public void signup_init()
	{
		frame_signup=new JFrame("Signup");
		frame_signup.setDefaultCloseOperation(frame_signup.DISPOSE_ON_CLOSE);
		frame_signup.setResizable(false);

		lname=new JLabel("Name ");
		fnt2=new Font("Arial",Font.BOLD,30);
		
		frame_signup=new JFrame("Signup");
		
	
		lname=new JLabel("Name");
		ldob=new JLabel("Date Of Birth");
		lpstadd=new JLabel("Present Adress");
		lperadd=new JLabel("Permanent Adress");
		lnid=new JLabel("National Id Number");
		lblood=new JLabel("Blood Group");
		lgender=new JLabel("Gender");
		lphone=new JLabel("Phone Number");
		lpass=new JLabel("Enter Password");
		lconfirmpass=new JLabel("Confirm Password");
		
		la=new JLabel("Signup");
        r_male=new JRadioButton("Male");
        r_female=new JRadioButton("Female");
		grpGender=new ButtonGroup();
		grpGender.add(r_male);
		grpGender.add(r_female);
		r_male.setBounds(200,400,100,30);
		r_female.setBounds(300,400,100,30);
		
		la.setFont(fnt2);
		signup_name=new JTextField();
        signup_dob=new JTextField();
        signup_pstadd=new JTextField();
        signup_peradd=new JTextField();
        signup_nid=new JTextField();
        signup_blood=new JTextField();
        //signup_gender=new JTextField();
        signup_phone=new JTextField();
		signup_pass=new JPasswordField();
		signup_confirmpass=new JPasswordField();
		btn_signup=new JButton("Submit");
		
		
		
		la.setBounds(50,20,100,50);
		signup_name.setBounds(200,100,200,30);
		signup_dob.setBounds(200,150,200,30);
		signup_pstadd.setBounds(200,200,200,30);
		signup_peradd.setBounds(200,250,200,30);
		signup_nid.setBounds(200,300,200,30);
		signup_blood.setBounds(200,350,200,30);
		//signup_gender.setBounds(200,400,200,30);
		signup_phone.setBounds(200,450,200,30);
		
		signup_pass.setBounds(200,500,200,30);
		signup_confirmpass.setBounds(200,550,200,30);
		btn_signup.setBounds(150,600,200,40);
		
		lname.setBounds(50,100,150,30);
		ldob.setBounds(50,150,150,30);
		lpstadd.setBounds(50,200,150,30);
		lperadd.setBounds(50,250,150,30);
		lnid.setBounds(50,300,150,30);
		lblood.setBounds(50,350,150,30);
		lgender.setBounds(50,400,150,30);
		lphone.setBounds(50,450,150,30);
		lpass.setBounds(50,500,150,30);
		lconfirmpass.setBounds(50,550,150,30);
		
		r_male.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				gender="male";
			}
		});
		r_male.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				gender="female";
			}
		});



		btn_signup.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){

					name=signup_name.getText();
					dob=signup_dob.getText();
					pstadd=signup_pstadd.getText();
					peradd=signup_peradd.getText();
					nid=signup_nid.getText();
					blood=signup_blood.getText();
					phone=signup_phone.getText();
					pss=signup_pass.getPassword();
					conpass=signup_confirmpass.getPassword();
					pass=String.valueOf(pss);
					confirmpass=String.valueOf(conpass);
					System.out.println(name.equals(""));
					if(!name.equals("")&&!dob.equals("")&&!pstadd.equals("")&&!peradd.equals("")&&!nid.equals("")&&!blood.equals("")&&!phone.equals("")&&!pass.equals("")&&!confirmpass.equals(""))
					{	


				try 
				{
					File file=new File("userinfo\\"+phone+".txt");
					File file2=new File("balance\\"+phone+".txt");
					if(!file.exists())
					{
						if(pass.equals(confirmpass))
						{			FileWriter wrt=new FileWriter(file);
									wrt.write(phone+"\n");
									wrt.write(pass+"\n");
									wrt.write(name+"\n");
									wrt.write(dob+"\n");
									wrt.write(pstadd+"\n");
									wrt.write(peradd+"\n");
									wrt.write(nid+"\n");
									wrt.write(blood+"\n");
									wrt.write(gender+"\n");
									wrt.close();
									JOptionPane.showMessageDialog(frame_signup,"Account Created Successfully and \n Your Phone number is your account number");
									frame_signup.dispose();
									signup_name.setEditable(false);
									signup_dob.setEditable(false);
									signup_pstadd.setEditable(false);
									signup_peradd.setEditable(false);
									signup_nid.setEditable(false);
									signup_blood.setEditable(false);
									signup_gender.setEditable(false);
									signup_phone.setEditable(false);
									signup_pass.setEditable(false);
									signup_confirmpass.setEditable(false);
									r_male.setEnabled(false);
									r_female.setEnabled(false);
									btn_signup.setEnabled(false);


						}
						else{JOptionPane.showMessageDialog(frame_signup,"Password are not matching");}				


					}
					else{JOptionPane.showMessageDialog(frame_signup,"Account already exist");}				
		
							

					
				} 
				catch (Exception ee) 
				{
					
				}
			}
					
			else{JOptionPane.showMessageDialog(frame_signup,"Fill all the information");}				
			 


			}
			});  
		



		

		frame_signup.add(lname);
        frame_signup.add(ldob);
        frame_signup.add(lpstadd);
        frame_signup.add(lperadd);
        frame_signup.add(lnid);
        frame_signup.add(lblood);
        frame_signup.add(lgender);
        frame_signup.add(lphone);
        frame_signup.add(lpass);
        frame_signup.add(lconfirmpass);
		frame_signup.add(signup_name);
		frame_signup.add(signup_dob);
		frame_signup.add(signup_pstadd);
		frame_signup.add(signup_peradd);
		frame_signup.add(signup_blood);
		frame_signup.add(signup_nid);
		frame_signup.add(r_male);
		frame_signup.add(r_female);
		frame_signup.add(signup_phone);
		frame_signup.add(signup_pass);
		frame_signup.add(signup_confirmpass);
		frame_signup.add(btn_signup);
		frame_signup.add(la);
		
		
		frame_signup.setSize(500,700);
		frame_signup.setLayout(null);
		frame_signup.setLocationRelativeTo(null);
		frame_signup.setVisible(true);
	}
	
	public static void main(String[]args)
	{
		new signup();
	}
}
