import java.io.*;
import java.sql.*;

import com.sun.speech.freetts.*;

public class VEDEST extends javax.swing.JFrame {
	public final static Voice voice = Read.getVoice();
	private static final long serialVersionUID = 1L;
	char[] b = new char[100];
	private static String u;
    String ques[] = new String[10];
    String ans[] = new String[10];
    int i = 0;
    int k = 0;
    VoiceManager voiceManager = VoiceManager.getInstance();
    Voice syntheticVoice = voiceManager.getVoice("kevin16");
    
    private static void readInstructions() {
		String instructions = "Instructions for module two. Press F1 key to play the question, F3 to play the answer from the beginning. Press F4 to stop at the current position. Press F5 to read the current line of the answer, F11 for help, F12 for time alert and space bar to complete the test";
		voice.speak(instructions);		
	}

    public VEDEST() {
      	u = EScribe.user;
    	//System.out.println(u);
        initComponents();
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = null;
            Statement s = null;
            ResultSet rs = null;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escribe", "root", "lsklnc");
            s = con.createStatement();
            int z;
            for (z = 0; z <= 9; z++) {
                String q = "select * from description where sno='" + z + "'";
                s.executeQuery(q);
                rs = s.getResultSet();
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
                    ques[z] = rs.getString(2);
                }
            }
            for (z = 0; z <= 9; z++) {
             //   System.out.println(ques[z]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        i = 0;
        jLabel1.setText(ques[i]);

    }
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ke(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("jLabel1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ke(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ke(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ke
        // TODO add your handling code here:
        b[i++] = evt.getKeyChar();
        if (evt.getKeyCode() != 37 || evt.getKeyCode() != 39) {
            if (evt.getKeyChar() == '.') {
                syntheticVoice.setRate(130f);
                syntheticVoice.allocate();
                String c = new String(b);
                // System.out.println(c);
                syntheticVoice.speak(c);
                for (int j = 0; j <= 99; j++) {
                    b[j] = ' ';
                }
                i = 0;
            }
        }
        if (evt.getKeyCode() == 112) {
            syntheticVoice.allocate();
            syntheticVoice.speak(ques[k]);
        }
        if (evt.getKeyCode() == 113) {
            syntheticVoice.allocate();
            syntheticVoice.speak(ans[k]);
        }
        if (evt.getKeyCode() == 119) //if (evt.getKeyChar() == 'J')
        {
            ans[k] = jTextArea1.getText();
            if (k != 0) {
                --k;
                jTextArea1.setText(ans[k]);
                jLabel1.setText(ques[k]);
            }
        }
        if (evt.getKeyCode() == 120) //if (evt.getKeyChar() == 'K')
        {
            ans[k] = jTextArea1.getText();
            if (k != 9) {
                ++k;
                jTextArea1.setText(ans[k]);
                jLabel1.setText(ques[k]);
            }
        }
        
        if (evt.getKeyCode() == 27) {
            ans[k] = jTextArea1.getText();
            for (int z = 0; z <= 9; z++) {
               // System.out.println(ans[z]);
            }
            
            try {
            	String path = new String(u + ".doc");
                FileOutputStream fs = new FileOutputStream(path);
                OutputStreamWriter out = new OutputStreamWriter(fs);
              
                for (int x = 0; x <= 9; x++) {
                    out.write(ques[x] + "\n");
                    out.write(" " + ans[x] + "\n");
                    out.write("-------------------------------------------------------------------------" + "\n");
                }
                out.close();
            } catch (Exception e) {
                System.out.println(e);
            }

               	String msg="Thankyou for attempting the test";
            	voice.speak(msg);
            	System.exit(0);
            
        //new ThankYou().setVisible(true);
        }
        if (evt.getKeyCode() == 122) {
        	readInstructions();
        }
        
        
        
    }//GEN-LAST:event_ke
    	
    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
  
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VEDEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VEDEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VEDEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VEDEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VEDEST().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}