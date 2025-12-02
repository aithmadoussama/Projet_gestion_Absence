package UI;

import UI.Principal;
import entities.Employe;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modifier_Employe extends javax.swing.JFrame {

    private Principal apprincipal;
    private int employe_id_selected;

    public Modifier_Employe(Principal apprincipal, int i) {
        initComponents();
        this.apprincipal = apprincipal;
        this.employe_id_selected = i;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnewnom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btannulerMenploye = new javax.swing.JButton();
        btnmodifieremploye2 = new javax.swing.JButton();
        boxdepatement = new javax.swing.JComboBox();
        boxposte = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modifier Employe");

        jPanel2.setBackground(new java.awt.Color(37, 138, 122));

        jLabel1.setForeground(new java.awt.Color(233, 247, 245));
        jLabel1.setText("Nouveau nom");

        jLabel2.setForeground(new java.awt.Color(233, 247, 245));
        jLabel2.setText("Nouveau poste");

        jLabel3.setForeground(new java.awt.Color(233, 247, 245));
        jLabel3.setText("Nouveau département");

        txtnewnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnewnomActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("URW Gothic", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(233, 247, 245));
        jLabel4.setText("MODIFIER EMPLOYE");

        btannulerMenploye.setBackground(new java.awt.Color(191, 215, 85));
        btannulerMenploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btannulerMenploye.setText("Annuler");
        btannulerMenploye.setBorderPainted(false);
        btannulerMenploye.setOpaque(true);
        btannulerMenploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btannulerMenployeActionPerformed(evt);
            }
        });

        btnmodifieremploye2.setBackground(new java.awt.Color(233, 215, 157));
        btnmodifieremploye2.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnmodifieremploye2.setText("Modifier");
        btnmodifieremploye2.setBorderPainted(false);
        btnmodifieremploye2.setOpaque(true);
        btnmodifieremploye2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifieremploye2ActionPerformed(evt);
            }
        });

        boxdepatement.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Développement", "Réseau", "RH", "Finance" }));

        boxposte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Développeur", "Testeur", "Chef de projet", "Scrum Master", "Technicien Réseau", "Admin Système", "Technicien Support", "Responsable RH", "Assistant RH", "Responsable Formation", "Chargée Recrutement", "Chargé Recrutement", "Comptable", "Responsable Financier", "Contrôleur", "Assistant Comptable", " " }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnmodifieremploye2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(btannulerMenploye, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(txtnewnom, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(boxdepatement, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boxposte, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnewnom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxdepatement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxposte, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnmodifieremploye2)
                    .addComponent(btannulerMenploye))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txtnewnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnewnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnewnomActionPerformed

    private void btannulerMenployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btannulerMenployeActionPerformed
        this.setVisible(false);
        apprincipal.employe_id_selected = -1 ;
    }//GEN-LAST:event_btannulerMenployeActionPerformed

    private void btnmodifieremploye2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifieremploye2ActionPerformed
        String new_nom = txtnewnom.getText();
        String new_departement = boxdepatement.getSelectedItem().toString();
        String new_poste = boxposte.getSelectedItem().toString();

        if (new_nom.isEmpty() || new_departement.isEmpty() || new_poste.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Un champ manquant",
                    "Important",JOptionPane.WARNING_MESSAGE);
            apprincipal.employe_id_selected = -1;
        } else {
            Employe e = apprincipal.employeservice.getEmplyeByid(employe_id_selected);
            e.setNom(new_nom);
            e.setDepartement(new_departement);
            e.setPoste(new_poste);
            int choix = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment modifier cet employé ?",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (choix == JOptionPane.OK_OPTION) {
                apprincipal.employeservice.updateEmplye(e);
                apprincipal.charger_Employe_Table();
                JOptionPane.showMessageDialog(this, "Employé modifié avec succès");
                this.setVisible(false);
            }
            apprincipal.employe_id_selected = -1 ;
        }
    }//GEN-LAST:event_btnmodifieremploye2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxdepatement;
    private javax.swing.JComboBox boxposte;
    private javax.swing.JButton btannulerMenploye;
    private javax.swing.JButton btnmodifieremploye2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtnewnom;
    // End of variables declaration//GEN-END:variables
}
