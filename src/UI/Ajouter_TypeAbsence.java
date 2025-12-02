package UI;

import services.Typeabsenceservice;
import entities.TypeAbsence;
import javax.swing.JOptionPane;
import UI.Principal;
import javax.swing.JFrame;

public class Ajouter_TypeAbsence extends javax.swing.JFrame {

    Principal principal = null;

    public Ajouter_TypeAbsence(Principal principal) {
        initComponents();
        this.principal = principal;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtjustification = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtlibelle = new javax.swing.JTextField();
        btnEajouter = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnannulerajouter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(37, 138, 122));

        jLabel1.setForeground(new java.awt.Color(233, 247, 245));
        jLabel1.setText("Libelle");

        txtjustification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjustificationActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(233, 247, 245));

        jLabel3.setForeground(new java.awt.Color(233, 247, 245));
        jLabel3.setText("justification");

        txtlibelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlibelleActionPerformed(evt);
            }
        });

        btnEajouter.setBackground(new java.awt.Color(97, 189, 114));
        btnEajouter.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnEajouter.setText("Ajouter");
        btnEajouter.setBorderPainted(false);
        btnEajouter.setOpaque(true);
        btnEajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEajouterActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("URW Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(233, 247, 245));
        jLabel4.setText("AJOUTER TYPE D'ABSENCE");

        btnannulerajouter.setBackground(new java.awt.Color(191, 215, 85));
        btnannulerajouter.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnannulerajouter.setText("Annuler");
        btnannulerajouter.setBorderPainted(false);
        btnannulerajouter.setOpaque(true);
        btnannulerajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnannulerajouterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(txtlibelle)
                        .addComponent(txtjustification)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnEajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(btnannulerajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtlibelle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtjustification, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEajouter)
                    .addComponent(btnannulerajouter))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtjustificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjustificationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjustificationActionPerformed

    private void txtlibelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlibelleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlibelleActionPerformed

    private void btnEajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEajouterActionPerformed
        String libelle = txtlibelle.getText();
        String justification = txtjustification.getText();

        Typeabsenceservice typeabsenceservice = new Typeabsenceservice();
        TypeAbsence type = new TypeAbsence(libelle, justification);

        if (libelle.isEmpty() || justification.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Un champ manquant","Important",
                    JOptionPane.WARNING_MESSAGE);
        } else {

            int choix = JOptionPane.showConfirmDialog(
                    this,
                    "Voullez-vous vraiment ajouter ce type d'absence ?",
                    "Confermation",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (choix == JOptionPane.OK_OPTION) {
                typeabsenceservice.createTypeabsence(type);
                principal.charger_TypesAbsence_Table();
//                principal.charger_graphe_absence();
//                principal.charger_graphe_employe();
                JOptionPane.showMessageDialog(this, "Type d'absence ajouté avec succès");
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_btnEajouterActionPerformed

    private void btnannulerajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnannulerajouterActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnannulerajouterActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEajouter;
    private javax.swing.JButton btnannulerajouter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtjustification;
    private javax.swing.JTextField txtlibelle;
    // End of variables declaration//GEN-END:variables
}
