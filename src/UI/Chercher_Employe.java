package UI;

import javax.swing.JFrame;
import entities.Employe;
import UI.Principal;
import javax.swing.JOptionPane;
import UI.Modifier_Employe_Trouve;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chercher_Employe extends javax.swing.JFrame {

    Principal principal = null;
    Employe emptrouve = new Employe();

    public Employe getEmptrouve() {
        return emptrouve;
    }
    
    public Chercher_Employe(Principal principal) {
        initComponents();
        this.principal = principal;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        employecherche = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chercheremploye = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        employetrouve = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        supprimeremploye = new javax.swing.JButton();
        modifieremploye = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chercher employe");

        jPanel1.setBackground(new java.awt.Color(37, 138, 122));

        jLabel1.setFont(new java.awt.Font("URW Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(244, 253, 252));
        jLabel1.setText("CHERCHER EMPLOYÉ");

        employecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employechercheActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(252, 245, 245));
        jLabel2.setText("Nom de l’employé à rechercher");

        chercheremploye.setBackground(new java.awt.Color(157, 233, 177));
        chercheremploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        chercheremploye.setText("Chercher");
        chercheremploye.setBorderPainted(false);
        chercheremploye.setOpaque(true);
        chercheremploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chercheremployeActionPerformed(evt);
            }
        });

        employetrouve.setColumns(20);
        employetrouve.setRows(5);
        jScrollPane1.setViewportView(employetrouve);

        jLabel3.setForeground(new java.awt.Color(238, 247, 248));
        jLabel3.setText("Employe trouve");

        supprimeremploye.setBackground(new java.awt.Color(215, 85, 85));
        supprimeremploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        supprimeremploye.setText("Supprimer");
        supprimeremploye.setBorderPainted(false);
        supprimeremploye.setOpaque(true);
        supprimeremploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimeremployeActionPerformed(evt);
            }
        });

        modifieremploye.setBackground(new java.awt.Color(233, 215, 157));
        modifieremploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        modifieremploye.setText("Modifier");
        modifieremploye.setBorderPainted(false);
        modifieremploye.setOpaque(true);
        modifieremploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifieremployeActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(191, 215, 85));
        jButton1.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        jButton1.setText("Annuler");
        jButton1.setBorderPainted(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chercheremploye, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(employecherche))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(supprimeremploye, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(modifieremploye, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(employecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chercheremploye)
                            .addComponent(jButton1)))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifieremploye)
                    .addComponent(supprimeremploye))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employechercheActionPerformed

    private void chercheremployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chercheremployeActionPerformed
        String nom = employecherche.getText();
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Le champ est vide",
                    "Important",JOptionPane.WARNING_MESSAGE);
        } else {
            emptrouve = this.principal.employeservice.getEmployeBynom(nom);
            if (emptrouve != null) {
                String txt = "Id: " + emptrouve.getId()
                        + "\nNom: " + emptrouve.getNom()
                        + "\nDépartement: " + emptrouve.getDepartement()
                        + "\nPoste: " + emptrouve.getPoste();
                JOptionPane.showMessageDialog(this, nom + " trouvé avec succès");
                employetrouve.setText(txt);
            } else {
                JOptionPane.showMessageDialog(this, nom + " non trouvable");
                employecherche.setText("");
                employetrouve.setText("");
            }
        }
    }//GEN-LAST:event_chercheremployeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void supprimeremployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimeremployeActionPerformed

        if (!employetrouve.getText().isEmpty()) {
            int choix = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer cet mployé ?",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (choix == JOptionPane.OK_OPTION) {
                this.principal.employeservice.deleteEmplye(emptrouve);
                this.principal.charger_Employe_Table();
                JOptionPane.showMessageDialog(this, "Employé supprimé avec succès");
                employecherche.setText("");
                employetrouve.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Aucun employé trouvé\n"
                    + "Veuillez en chercher un");
        }
    }//GEN-LAST:event_supprimeremployeActionPerformed

    private void modifieremployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifieremployeActionPerformed
        if (!employecherche.getText().isEmpty()) {
            Modifier_Employe_Trouve modifierEmployeRechPnl = new Modifier_Employe_Trouve(this);
            modifierEmployeRechPnl.setLocationRelativeTo(null);
            modifierEmployeRechPnl.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Aucun employé trouvé\n"
                    + "Veuillez en chercher un");
        }

    }//GEN-LAST:event_modifieremployeActionPerformed

    public JTextField getEmployecherche() {
        return employecherche;
    }

    public JTextArea getEmployetrouve() {
        return employetrouve;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chercheremploye;
    private javax.swing.JTextField employecherche;
    private javax.swing.JTextArea employetrouve;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifieremploye;
    private javax.swing.JButton supprimeremploye;
    // End of variables declaration//GEN-END:variables
}
