import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class history 
{

        public JFrame f6;
        public JTextArea ta_his;
        public JScrollPane sclpane;
        private JLabel l_type,l_amount,l_number;
          private String catchAcc,data="";
       history(String acc)
       {
          catchAcc=acc;
          init();
       } 
       public void init()
       {
            f6=new JFrame("History");
            f6.setResizable(false);

            f6.setLayout(null);
            l_type=new JLabel("Type");
            l_amount=new JLabel("Amount");
            l_number=new JLabel("Number");
            l_type.setBounds(30,20,50,30);
            l_amount.setBounds(180,20,50,30);
            l_number.setBounds(350,20,50,30);
            


               try {
                    File file=new File("history\\"+catchAcc+".txt");
                    Scanner reader=new Scanner(file);
                    while(reader.hasNextLine())
                    {
                         String line=reader.nextLine();
                        
                         data=data+line+"\n";
                         

                    }

                    reader.close();
               } catch (Exception e) {

               }


            ta_his=new JTextArea(data);
            ta_his.setEditable(false);;
            sclpane=new JScrollPane(ta_his);
            
            sclpane.setBounds(20,50,450,300);
            ta_his.setWrapStyleWord(true);
            ta_his.setLineWrap(true);





          f6.add(sclpane);
          f6.add(l_type);
          f6.add(l_amount);
          f6.add(l_number);

          f6.setSize(500,400);
		f6.setDefaultCloseOperation(f6.DISPOSE_ON_CLOSE);
		f6.setLocationRelativeTo(null);
	     f6.setVisible(true);
       }

}
