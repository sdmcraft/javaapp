/*
 * FolderDiffGUI.java
 *
 * Created on November 5, 2008, 5:21 PM
 */
package com.folderdiff.gui;

import com.folderdiff.core.DiffEngine;
import com.folderdiff.core.DiffFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

/**
 *
 * @author  satyam
 */
public class FolderDiffGUI extends javax.swing.JFrame {
	private static final Logger  logger = Logger.getLogger(com.folderdiff.gui.FolderDiffGUI.class);
    /** Creates new form FolderDiffGUI */
    public FolderDiffGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root1Field = new javax.swing.JTextField();
        root1Label = new javax.swing.JLabel();
        doDiffButton = new javax.swing.JButton();
        root2Label = new javax.swing.JLabel();
        root2Field = new javax.swing.JTextField();
        chooseRoot1Button = new javax.swing.JButton();
        chooseRoot2Button = new javax.swing.JButton();
        resultMsgLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        root3Label = new javax.swing.JLabel();
        root4Label = new javax.swing.JLabel();
        root5Label = new javax.swing.JLabel();
        root6Label = new javax.swing.JLabel();
        root7Label = new javax.swing.JLabel();
        root8Label = new javax.swing.JLabel();
        root9Label = new javax.swing.JLabel();
        root10Label = new javax.swing.JLabel();
        root3Field = new javax.swing.JTextField();
        root4Field = new javax.swing.JTextField();
        root5Field = new javax.swing.JTextField();
        root6Field = new javax.swing.JTextField();
        root7Field = new javax.swing.JTextField();
        root8Field = new javax.swing.JTextField();
        root9Field = new javax.swing.JTextField();
        root10Field = new javax.swing.JTextField();
        chooseRoot3Button = new javax.swing.JButton();
        chooseRoot4Button = new javax.swing.JButton();
        chooseRoot5Button = new javax.swing.JButton();
        chooseRoot6Button = new javax.swing.JButton();
        chooseRoot7Button = new javax.swing.JButton();
        chooseRoot8Button = new javax.swing.JButton();
        chooseRoot9Button = new javax.swing.JButton();
        chooseRoot10Button = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Folder Diff - By Satya Deep Maheshwari");
        setName("Folder Diff"); // NOI18N

        root1Field.setEditable(false);

        root1Label.setText("Root1:");

        doDiffButton.setText("Do Diff");
        doDiffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doDiffButtonActionPerformed(evt);
            }
        });

        root2Label.setText("Root2:");

        root2Field.setEditable(false);

        chooseRoot1Button.setText("Browse");
        chooseRoot1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot1ButtonActionPerformed(evt);
            }
        });

        chooseRoot2Button.setText("Browse");
        chooseRoot2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot2ButtonActionPerformed(evt);
            }
        });

        resultMsgLabel.setForeground(new java.awt.Color(0, 153, 0));

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        root3Label.setText("Root3:");

        root4Label.setText("Root4:");

        root5Label.setText("Root5:");

        root6Label.setText("Root6:");

        root7Label.setText("Root7:");

        root8Label.setText("Root8:");

        root9Label.setText("Root9:");

        root10Label.setText("Root10:");

        root3Field.setEditable(false);

        root4Field.setEditable(false);

        root5Field.setEditable(false);

        root6Field.setEditable(false);

        root7Field.setEditable(false);

        root8Field.setEditable(false);

        root9Field.setEditable(false);

        root10Field.setEditable(false);

        chooseRoot3Button.setText("Browse");
        chooseRoot3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot3ButtonActionPerformed(evt);
            }
        });

        chooseRoot4Button.setText("Browse");
        chooseRoot4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot4ButtonActionPerformed(evt);
            }
        });

        chooseRoot5Button.setText("Browse");
        chooseRoot5Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot5ButtonActionPerformed(evt);
            }
        });

        chooseRoot6Button.setText("Browse");
        chooseRoot6Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot6ButtonActionPerformed(evt);
            }
        });

        chooseRoot7Button.setText("Browse");
        chooseRoot7Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot7ButtonActionPerformed(evt);
            }
        });

        chooseRoot8Button.setText("Browse");
        chooseRoot8Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot8ButtonActionPerformed(evt);
            }
        });

        chooseRoot9Button.setText("Browse");
        chooseRoot9Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot9ButtonActionPerformed(evt);
            }
        });

        chooseRoot10Button.setText("Browse");
        chooseRoot10Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRoot10ButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(root1Label)
                            .addComponent(root3Label)
                            .addComponent(root4Label)
                            .addComponent(root5Label)
                            .addComponent(root6Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(root1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chooseRoot1Button))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(root3Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chooseRoot3Button))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(root4Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chooseRoot4Button))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(root5Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chooseRoot5Button))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(root6Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chooseRoot6Button)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resultMsgLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(root2Label)
                                    .addComponent(root7Label)
                                    .addComponent(root8Label)
                                    .addComponent(root9Label)
                                    .addComponent(root10Label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(root10Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chooseRoot10Button))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(root9Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chooseRoot9Button))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(root8Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chooseRoot8Button))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(root7Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chooseRoot7Button))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(root2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chooseRoot2Button))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(resetButton)
                                .addGap(18, 18, 18)
                                .addComponent(doDiffButton)
                                .addGap(18, 18, 18)
                                .addComponent(exitButton)))
                        .addGap(18, 18, 18)
                        .addComponent(helpButton)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chooseRoot10Button, chooseRoot1Button, chooseRoot2Button, chooseRoot3Button, chooseRoot4Button, chooseRoot5Button, chooseRoot6Button, chooseRoot7Button, chooseRoot8Button, chooseRoot9Button, doDiffButton, exitButton, helpButton, resetButton, root10Field, root1Field, root2Field, root3Field, root4Field, root5Field, root6Field, root7Field, root8Field, root9Field});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {root10Label, root1Label, root2Label, root3Label, root4Label, root5Label, root6Label, root7Label, root8Label, root9Label});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(root1Label)
                            .addComponent(chooseRoot1Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root3Label)
                            .addComponent(root3Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseRoot3Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root4Label)
                            .addComponent(root4Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseRoot4Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root5Label)
                            .addComponent(chooseRoot5Button)
                            .addComponent(root5Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root6Label)
                            .addComponent(chooseRoot6Button)
                            .addComponent(root6Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(resultMsgLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(root2Label)
                            .addComponent(chooseRoot2Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root7Label)
                            .addComponent(root7Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseRoot7Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root8Label)
                            .addComponent(root8Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseRoot8Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root9Label)
                            .addComponent(root9Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseRoot9Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(root10Label)
                            .addComponent(root10Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseRoot10Button))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton)
                    .addComponent(doDiffButton)
                    .addComponent(exitButton)
                    .addComponent(helpButton))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void doDiffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doDiffButtonActionPerformed
	logger.info("(+)doDiffButtonActionPerformed(+)");
    //progressBar.setVisible(true);
    progressBar.setIndeterminate(true);
    try {
        //List<DiffFile> list = null;
        Task task = new Task();
        task.execute();
        try {
            task.get(1, TimeUnit.MILLISECONDS);
        } catch (TimeoutException ex) {
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
	logger.info("(-)doDiffButtonActionPerformed(-)");
//progressBar.setVisible(false);
}//GEN-LAST:event_doDiffButtonActionPerformed

private void chooseRoot1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot1ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root1Field.setText(file.toString() + file.separator);
    }
}//GEN-LAST:event_chooseRoot1ButtonActionPerformed

private void chooseRoot2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot2ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root2Field.setText(file.toString() + file.separator);
    }
}//GEN-LAST:event_chooseRoot2ButtonActionPerformed

private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
    System.exit(0);
}//GEN-LAST:event_exitButtonActionPerformed

private void chooseRoot3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot3ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root3Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot3ButtonActionPerformed

private void chooseRoot4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot4ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root4Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot4ButtonActionPerformed

private void chooseRoot5ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot5ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root5Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot5ButtonActionPerformed

private void chooseRoot6ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot6ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root6Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot6ButtonActionPerformed

private void chooseRoot8ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot8ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root8Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot8ButtonActionPerformed

private void chooseRoot9ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot9ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root9Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot9ButtonActionPerformed

private void chooseRoot10ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot10ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root10Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot10ButtonActionPerformed

private void chooseRoot7ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRoot7ButtonActionPerformed
    int returnVal = fc.showOpenDialog(rootPane);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        root7Field.setText(file.toString() + file.separator);
    }

}//GEN-LAST:event_chooseRoot7ButtonActionPerformed

private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
    root1Field.setText(null);
    root2Field.setText(null);
    root3Field.setText(null);
    root4Field.setText(null);
    root5Field.setText(null);
    root6Field.setText(null);
    root7Field.setText(null);
    root8Field.setText(null);
    root9Field.setText(null);
    root10Field.setText(null);
}//GEN-LAST:event_resetButtonActionPerformed

private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
	logger.info("(+)helpButtonActionPerformed(+)");
    BrowserControl.displayURL(System.getProperty("user.dir") + File.separator + "bin" + File.separator + "Help.html");
    logger.info("(-)helpButtonActionPerformed(-)");
}//GEN-LAST:event_helpButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FolderDiffGUI().setVisible(true);   
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }
        });
    }
    
    class Task extends  SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() throws Exception{    
            List<String> rootList = new ArrayList<String>();
            if (root1Field.getText() != null && !("".equals(root1Field.getText()))) {
                rootList.add(root1Field.getText());
            }
            if (root2Field.getText() != null && !("".equals(root2Field.getText()))) {
                rootList.add(root2Field.getText());
            }
            if (root3Field.getText() != null && !("".equals(root3Field.getText()))) {
                rootList.add(root3Field.getText());
            }
            if (root4Field.getText() != null && !("".equals(root4Field.getText()))) {
                rootList.add(root4Field.getText());
            }
            if (root5Field.getText() != null && !("".equals(root5Field.getText()))) {
                rootList.add(root5Field.getText());
            }
            if (root6Field.getText() != null && !("".equals(root6Field.getText()))) {
                rootList.add(root6Field.getText());
            }
            if (root7Field.getText() != null && !("".equals(root7Field.getText()))) {
                rootList.add(root7Field.getText());
            }
            if (root8Field.getText() != null && !("".equals(root8Field.getText()))) {
                rootList.add(root8Field.getText());
            }
            if (root9Field.getText() != null && !("".equals(root9Field.getText()))) {
                rootList.add(root9Field.getText());
            }
            if (root10Field.getText() != null && !("".equals(root10Field.getText()))) {
                rootList.add(root10Field.getText());
            }
            String[] rootArray = new String[rootList.size()];
            rootArray = rootList.toArray(rootArray);
            
            List<DiffFile> list = DiffEngine.doDiff(rootArray);
            File html = DiffEngine.writeToHtml(list);
            BrowserControl.displayURL(html.toString());            
            return null;
        }

        @Override
        public void done() {
                progressBar.setIndeterminate(false);
    }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseRoot10Button;
    private javax.swing.JButton chooseRoot1Button;
    private javax.swing.JButton chooseRoot2Button;
    private javax.swing.JButton chooseRoot3Button;
    private javax.swing.JButton chooseRoot4Button;
    private javax.swing.JButton chooseRoot5Button;
    private javax.swing.JButton chooseRoot6Button;
    private javax.swing.JButton chooseRoot7Button;
    private javax.swing.JButton chooseRoot8Button;
    private javax.swing.JButton chooseRoot9Button;
    private javax.swing.JButton doDiffButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel resultMsgLabel;
    private javax.swing.JTextField root10Field;
    private javax.swing.JLabel root10Label;
    private javax.swing.JTextField root1Field;
    private javax.swing.JLabel root1Label;
    private javax.swing.JTextField root2Field;
    private javax.swing.JLabel root2Label;
    private javax.swing.JTextField root3Field;
    private javax.swing.JLabel root3Label;
    private javax.swing.JTextField root4Field;
    private javax.swing.JLabel root4Label;
    private javax.swing.JTextField root5Field;
    private javax.swing.JLabel root5Label;
    private javax.swing.JTextField root6Field;
    private javax.swing.JLabel root6Label;
    private javax.swing.JTextField root7Field;
    private javax.swing.JLabel root7Label;
    private javax.swing.JTextField root8Field;
    private javax.swing.JLabel root8Label;
    private javax.swing.JTextField root9Field;
    private javax.swing.JLabel root9Label;
    // End of variables declaration//GEN-END:variables
    private static final JFileChooser fc = new JFileChooser();    

}
