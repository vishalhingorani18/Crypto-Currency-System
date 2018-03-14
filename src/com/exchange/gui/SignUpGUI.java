package com.exchange.gui;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.exchange.CurrencySystem;

public class SignUpGUI extends javax.swing.JFrame {

	CurrencySystem system;
	HomeGUI home;

	public SignUpGUI(CurrencySystem system) {
		this.system = system;
		initComponents();
	}

	private void initComponents() {

		jScrollBar1 = new javax.swing.JScrollBar();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

		jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		jTextField3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

		jTextField4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel1.setText("First Name");

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel2.setText("Last Name");

		jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jButton1.setText("Submit");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("Sign Up Page");

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel4.setText("Email ID");

		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		jLabel5.setText("Password");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(95, 95, 95)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel4)
						.addComponent(jLabel1))
				.addGap(98, 98, 98)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 100,
												Short.MAX_VALUE)
										.addComponent(jTextField4))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel2).addComponent(jLabel5))
								.addGap(60, 60, 60)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 101,
												Short.MAX_VALUE)
										.addComponent(jTextField3)))
						.addGroup(layout.createSequentialGroup().addGap(23, 23, 23)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel3,
								javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(215, 215, 215)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(32, 32, 32)
				.addComponent(
						jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(40, 40, 40)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1).addComponent(jLabel2))
				.addGap(64, 64, 64)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel4).addComponent(jLabel5))
				.addGap(54, 54, 54).addComponent(jButton1).addContainerGap(130, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {

		if (jTextField1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "first name is required");
		} else if (jTextField2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "last name is required");
		} else if (jTextField3.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "emailID is required");
		} else if (jTextField4.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "password is required");
		} 
		else 
		{

			boolean exists = system.createUser(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(),
					jTextField4.getText());
			
            System.out.println(jTextField3.getText()+" "+jTextField4.getText());


			System.out.println(exists);
			if (!exists)
				JOptionPane.showMessageDialog(null, "User with emaild id " + jTextField3.getText() + " alrady exists");
			else
			{
				JOptionPane.showMessageDialog(null, "Account Successfully Created");

			    super.dispose();

			}
		}
	}

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollBar jScrollBar1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	// End of variables declaration
}
