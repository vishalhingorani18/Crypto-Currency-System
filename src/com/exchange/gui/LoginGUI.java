

package com.exchange.gui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.exchange.CurrencySystem;
import com.exchange.User;

public class LoginGUI extends javax.swing.JFrame 
{

    private CurrencySystem system;
    User currentUser;
    public LoginGUI(CurrencySystem system) 
    {
    	   this.system=system;
        initComponents();
    }

                      
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Password");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Log In");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                try 
                {
					jButton1ActionPerformed(evt);
				} 
                catch (ClassNotFoundException e)
                {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                catch (IOException e)
                {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Login to Crypto Currency");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(212, 212, 212))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(316, 316, 316))))
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(jButton1)
                .addGap(148, 148, 148))
        );

        pack();
    }// </editor-fold>                        

 
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException, IOException
    {                                      
       
   
                    if(jTextField1.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "username is required");
                    }
                    else if(jTextField2.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "password is required");
                    }
                    else if(system.checkUser(jTextField1.getText(), jTextField2.getText())!=null)
                    {
                       currentUser = system.checkUser(jTextField1.getText(), jTextField2.getText());
                     
                       //read previous transactions if any
           			Scanner input = null;
           			File file = new   File(jTextField2.getText()+"TransactionLog.txt"); 
           			if(file.exists())
           			{
           				 System.out.println("Previous Transactions:");
           				try 
           				{
           					
           					 input = new   Scanner(file); 
           					
           					 
           					while (input.hasNextLine()) 
           					{ 
           						String line = input.nextLine(); 
           						System.out.println( line); 
           					} 
           				} catch (IOException ioe)
           				{     
           						System.out.println(ioe.toString());       
           				}  
           				finally  {    if (input != null)  input.close();    }
           				
           			}
                       
                       
                       
                       
                       JOptionPane.showMessageDialog(null, "Logged In");
                    
                       DashboardGUI g = new DashboardGUI(currentUser,system);
                       g.setVisible(true);
                       
                       Thread dashboard=new Thread(g);
                       dashboard.start();
                       
                       
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                        

                    }
                    super.dispose();
      
    }   
    
   

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration                   
}

