package UI;

import entities.Employe;
import java.util.ArrayList;
import javax.swing.JFrame;
import services.Employeservice;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import UI.Ajouter_Employe;
import UI.Modifier_Employe;
import javax.swing.JOptionPane;
import java.awt.Font;
import entities.TypeAbsence;
import services.Typeabsenceservice;
import UI.Modifier_TypeAbsence;
import UI.Chercher_Typeabsence;
import java.util.Locale;
import connexion.User;
import UI.Main;
import java.awt.BorderLayout;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import entities.Absence;
import java.awt.Dimension;
import services.Absenceservice;

public class Principal extends javax.swing.JFrame {

    Employeservice employeservice;
    Typeabsenceservice typeabsenceservise;
    Absenceservice absenceservice;
    User login_user = null;
    public int employe_id_selected = -1;
    public int type_id_selected = -1;
    public int absence_id_selected = -1;

    public void charger_Employe_Table() {
        List<Employe> employes = employeservice.getAllemplye();

        DefaultTableModel model_emplye = (DefaultTableModel) tblemployes.getModel();
        model_emplye.setRowCount(0);

        for (Employe e : employes) {
            int nb = this.absenceservice.getNombreAbsenceEmploye(e.getId());
            model_emplye.addRow(new Object[]{
                e.getId(),
                e.getNom(),
                e.getDepartement(),
                e.getPoste(),
                nb});
        }
    }

    public void charger_TypesAbsence_Table() {
        List<TypeAbsence> typesabsence = typeabsenceservise.getAllTypeabsence();

        DefaultTableModel model = (DefaultTableModel) tblTypeAbsence.getModel();
        model.setRowCount(0);

        for (TypeAbsence t : typesabsence) {
            model.addRow(new Object[]{
                t.getId(),
                t.getLibelle(),
                t.getJustification(),});
        }

    }

    public void charger_Absence_Table() {
        List<Absence> absences = absenceservice.getAllAbsence();
        DefaultTableModel model = (DefaultTableModel) tableAbsences.getModel();
        model.setRowCount(0);

        for (Absence absence : absences) {
            model.addRow(new Object[]{
                absence.getId(),
                absence.getEmploye().getNom(),
                absence.getDate_debut(),
                absence.getDate_fin(),
                absence.getTypeabence().getJustification(),});
        }
    }

    public void charger_graphe_employe() {
        DefaultPieDataset dataset = employeservice.getPieDataset();

        JFreeChart chart = ChartFactory.createPieChart(
                "Nombre d’employés par département",
                dataset,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(925, 340));
        graphe_employe.removeAll();
        graphe_employe.setLayout(new java.awt.BorderLayout());
        graphe_employe.add(chartPanel, BorderLayout.CENTER);

        graphe_employe.validate();
        graphe_employe.repaint();
    }

    public void charger_graphe_absence() {
        DefaultCategoryDataset dataset = absenceservice.getBardataset();

        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre d'absences par département",
                "Département",
                "Nombre d'absences",
                dataset
        );

        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setPreferredSize(new Dimension(520, 330));

        graphe_absence.removeAll();
        graphe_absence.setLayout(new BorderLayout());
        graphe_absence.add(chartpanel, BorderLayout.CENTER);

        graphe_absence.revalidate();
        graphe_absence.repaint();
    }

    public void afficherGrapheAbsencesMois() {
        DefaultCategoryDataset dataset = this.absenceservice.getDatasetAbsenceParMois();

        JFreeChart chart = ChartFactory.createBarChart(
                "Jours d'absence par mois",
                "Mois",
                "Nombre de jours",
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(600, 400));

        absence_mois.removeAll();
        absence_mois.setLayout(new BorderLayout());
        absence_mois.add(panel, BorderLayout.CENTER);
        absence_mois.validate();
        absence_mois.repaint();
    }

//    public void afficherGrapheAbsenceParEmploye() {
//        DefaultCategoryDataset dataset = this.absenceservice.getDatasetAbsenceParEmploye();
//        JFreeChart chart = ChartFactory.createBarChart(
//                "Nombre d'absences par employé",
//                "Employé",
//                "Nombre d'absences",
//                dataset
//        );
//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
//        graphe_employe.removeAll();
//        graphe_employe.setLayout(new BorderLayout());
//        graphe_employe.add(chartPanel, BorderLayout.CENTER);
//        graphe_employe.validate();
//        graphe_employe.repaint();
//    }

    public Principal(User login_user) {
        initComponents();
        this.employeservice = new Employeservice();
        this.absenceservice = new Absenceservice();
        this.typeabsenceservise = new Typeabsenceservice();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.charger_Employe_Table();
        this.charger_TypesAbsence_Table();
        this.charger_Absence_Table();
        pnlstatestiques.setVisible(true);
        pnlabsence.setVisible(false);
        pnltypeabsence.setVisible(false);
        pnlemploye.setVisible(false);
        tblemployes.setRowHeight(30);
        tblTypeAbsence.setRowHeight(30);
        tableAbsences.setRowHeight(30);
        tableAbsences.setFont(new Font("Arial", Font.PLAIN, 16));
        tblemployes.setFont(new Font("Arial", Font.PLAIN, 16));
        tblTypeAbsence.setFont(new Font("Arial", Font.PLAIN, 16));
        this.login_user = login_user;
        nom_utilisateur.setText(this.login_user.getNom());
        date_actuel.setText(LocalDate.now().toString());
        this.charger_graphe_employe();
        this.charger_graphe_absence();
        this.afficherGrapheAbsencesMois();
//        this.afficherGrapheAbsenceParEmploye();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        pnlprincipal = new javax.swing.JPanel();
        pnltypeabsence = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTypeAbsence = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnajouterTypeabsenc = new javax.swing.JButton();
        btnchercherTypeabsence = new javax.swing.JButton();
        btnmodifierTypeabsence = new javax.swing.JButton();
        btnsupprimerTypeabsence = new javax.swing.JButton();
        pnlabsence = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnajouterAbsence = new javax.swing.JButton();
        btnsupprimerabsence = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableAbsences = new javax.swing.JTable();
        pnlemploye = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblemployes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnajouterEMploye = new javax.swing.JButton();
        btnchercherEmploye = new javax.swing.JButton();
        btnmodifierEmploye = new javax.swing.JButton();
        btnsupprimerEmploye = new javax.swing.JButton();
        pnlstatestiques = new javax.swing.JPanel();
        graphe_employe = new javax.swing.JPanel();
        graphe_absence = new javax.swing.JPanel();
        absence_mois = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btNstatestiques = new javax.swing.JButton();
        btnabsence = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        btntypeabcence = new javax.swing.JButton();
        btnemplye1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nom_utilisateur = new javax.swing.JLabel();
        date_actuel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(230);

        pnlprincipal.setLayout(new java.awt.CardLayout());

        pnltypeabsence.setBackground(new java.awt.Color(191, 47, 47));

        tblTypeAbsence.setBackground(new java.awt.Color(194, 209, 209));
        tblTypeAbsence.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "LIBELLE", "JUSTIFICATION"
            }
        ));
        tblTypeAbsence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTypeAbsenceMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTypeAbsence);
        tblTypeAbsence.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );

        jPanel9.setPreferredSize(new java.awt.Dimension(904, 109));

        jPanel10.setBackground(new java.awt.Color(219, 235, 237));
        jPanel10.setPreferredSize(new java.awt.Dimension(904, 109));

        btnajouterTypeabsenc.setBackground(new java.awt.Color(150, 215, 154));
        btnajouterTypeabsenc.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnajouterTypeabsenc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ajouter-un-bouton.png"))); // NOI18N
        btnajouterTypeabsenc.setText("AJOUTER");
        btnajouterTypeabsenc.setBorderPainted(false);
        btnajouterTypeabsenc.setOpaque(true);
        btnajouterTypeabsenc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnajouterTypeabsencActionPerformed(evt);
            }
        });

        btnchercherTypeabsence.setBackground(new java.awt.Color(157, 233, 177));
        btnchercherTypeabsence.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnchercherTypeabsence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loupe.png"))); // NOI18N
        btnchercherTypeabsence.setText("CHERCHER");
        btnchercherTypeabsence.setToolTipText("");
        btnchercherTypeabsence.setBorderPainted(false);
        btnchercherTypeabsence.setOpaque(true);
        btnchercherTypeabsence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchercherTypeabsenceActionPerformed(evt);
            }
        });

        btnmodifierTypeabsence.setBackground(new java.awt.Color(233, 215, 157));
        btnmodifierTypeabsence.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnmodifierTypeabsence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editer.png"))); // NOI18N
        btnmodifierTypeabsence.setText("MODIFIER");
        btnmodifierTypeabsence.setToolTipText("");
        btnmodifierTypeabsence.setBorderPainted(false);
        btnmodifierTypeabsence.setOpaque(true);
        btnmodifierTypeabsence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierTypeabsenceActionPerformed(evt);
            }
        });

        btnsupprimerTypeabsence.setBackground(new java.awt.Color(215, 85, 85));
        btnsupprimerTypeabsence.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnsupprimerTypeabsence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supprimer.png"))); // NOI18N
        btnsupprimerTypeabsence.setText("SUPPRIMER");
        btnsupprimerTypeabsence.setToolTipText("");
        btnsupprimerTypeabsence.setBorderPainted(false);
        btnsupprimerTypeabsence.setOpaque(true);
        btnsupprimerTypeabsence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerTypeabsenceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnajouterTypeabsenc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnchercherTypeabsence, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnmodifierTypeabsence, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsupprimerTypeabsence, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnajouterTypeabsenc)
                    .addComponent(btnchercherTypeabsence)
                    .addComponent(btnmodifierTypeabsence)
                    .addComponent(btnsupprimerTypeabsence))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnltypeabsenceLayout = new javax.swing.GroupLayout(pnltypeabsence);
        pnltypeabsence.setLayout(pnltypeabsenceLayout);
        pnltypeabsenceLayout.setHorizontalGroup(
            pnltypeabsenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
        );
        pnltypeabsenceLayout.setVerticalGroup(
            pnltypeabsenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnltypeabsenceLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlprincipal.add(pnltypeabsence, "card3");

        pnlabsence.setBackground(new java.awt.Color(191, 47, 47));

        jPanel5.setBackground(new java.awt.Color(219, 235, 237));
        jPanel5.setPreferredSize(new java.awt.Dimension(722, 109));

        btnajouterAbsence.setBackground(new java.awt.Color(150, 215, 154));
        btnajouterAbsence.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnajouterAbsence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ajouter-un-bouton.png"))); // NOI18N
        btnajouterAbsence.setText("AJOUTER");
        btnajouterAbsence.setBorderPainted(false);
        btnajouterAbsence.setOpaque(true);
        btnajouterAbsence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnajouterAbsenceActionPerformed(evt);
            }
        });

        btnsupprimerabsence.setBackground(new java.awt.Color(215, 85, 85));
        btnsupprimerabsence.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnsupprimerabsence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supprimer.png"))); // NOI18N
        btnsupprimerabsence.setText("SUPPRIMER");
        btnsupprimerabsence.setToolTipText("");
        btnsupprimerabsence.setBorderPainted(false);
        btnsupprimerabsence.setOpaque(true);
        btnsupprimerabsence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerabsenceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnajouterAbsence, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsupprimerabsence, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnajouterAbsence)
                    .addComponent(btnsupprimerabsence))
                .addGap(32, 32, 32))
        );

        tableAbsences.setBackground(new java.awt.Color(194, 209, 209));
        tableAbsences.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOM EMPLOYE", "DATE DE DEBUT D'ABSENCE", "DATE DE FIN D'ABSENCE", "JUSTIFICATION D'ABSENCE"
            }
        ));
        tableAbsences.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAbsencesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableAbsences);

        javax.swing.GroupLayout pnlabsenceLayout = new javax.swing.GroupLayout(pnlabsence);
        pnlabsence.setLayout(pnlabsenceLayout);
        pnlabsenceLayout.setHorizontalGroup(
            pnlabsenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
        );
        pnlabsenceLayout.setVerticalGroup(
            pnlabsenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlabsenceLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlprincipal.add(pnlabsence, "card4");

        pnlemploye.setBackground(new java.awt.Color(191, 47, 47));

        jPanel1.setPreferredSize(new java.awt.Dimension(904, 578));

        tblemployes.setBackground(new java.awt.Color(194, 209, 209));
        tblemployes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOM ET PRENOM", "DEPARTEMENT", "TYPE DE POSTE", "NOMBRE D'ABSENCE"
            }
        ));
        tblemployes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblemployes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblemployesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblemployes);
        tblemployes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(219, 235, 237));
        jPanel2.setPreferredSize(new java.awt.Dimension(722, 109));

        btnajouterEMploye.setBackground(new java.awt.Color(150, 215, 154));
        btnajouterEMploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnajouterEMploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ajouter-un-bouton.png"))); // NOI18N
        btnajouterEMploye.setText("AJOUTER");
        btnajouterEMploye.setBorderPainted(false);
        btnajouterEMploye.setOpaque(true);
        btnajouterEMploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnajouterEMployeActionPerformed(evt);
            }
        });

        btnchercherEmploye.setBackground(new java.awt.Color(157, 233, 177));
        btnchercherEmploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnchercherEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loupe.png"))); // NOI18N
        btnchercherEmploye.setText("CHERCHER");
        btnchercherEmploye.setToolTipText("");
        btnchercherEmploye.setBorderPainted(false);
        btnchercherEmploye.setOpaque(true);
        btnchercherEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchercherEmployeActionPerformed(evt);
            }
        });

        btnmodifierEmploye.setBackground(new java.awt.Color(233, 215, 157));
        btnmodifierEmploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnmodifierEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editer.png"))); // NOI18N
        btnmodifierEmploye.setText("MODIFIER");
        btnmodifierEmploye.setToolTipText("");
        btnmodifierEmploye.setBorderPainted(false);
        btnmodifierEmploye.setOpaque(true);
        btnmodifierEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierEmployeActionPerformed(evt);
            }
        });

        btnsupprimerEmploye.setBackground(new java.awt.Color(215, 85, 85));
        btnsupprimerEmploye.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnsupprimerEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supprimer.png"))); // NOI18N
        btnsupprimerEmploye.setText("SUPPRIMER");
        btnsupprimerEmploye.setToolTipText("");
        btnsupprimerEmploye.setBorderPainted(false);
        btnsupprimerEmploye.setOpaque(true);
        btnsupprimerEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerEmployeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnajouterEMploye, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnchercherEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnmodifierEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsupprimerEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnajouterEMploye)
                    .addComponent(btnchercherEmploye)
                    .addComponent(btnmodifierEmploye)
                    .addComponent(btnsupprimerEmploye))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout pnlemployeLayout = new javax.swing.GroupLayout(pnlemploye);
        pnlemploye.setLayout(pnlemployeLayout);
        pnlemployeLayout.setHorizontalGroup(
            pnlemployeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
        );
        pnlemployeLayout.setVerticalGroup(
            pnlemployeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlemployeLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlprincipal.add(pnlemploye, "card2");

        graphe_employe.setBackground(new java.awt.Color(209, 220, 225));

        javax.swing.GroupLayout graphe_employeLayout = new javax.swing.GroupLayout(graphe_employe);
        graphe_employe.setLayout(graphe_employeLayout);
        graphe_employeLayout.setHorizontalGroup(
            graphe_employeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 949, Short.MAX_VALUE)
        );
        graphe_employeLayout.setVerticalGroup(
            graphe_employeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );

        graphe_absence.setBackground(new java.awt.Color(95, 66, 66));

        javax.swing.GroupLayout graphe_absenceLayout = new javax.swing.GroupLayout(graphe_absence);
        graphe_absence.setLayout(graphe_absenceLayout);
        graphe_absenceLayout.setHorizontalGroup(
            graphe_absenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );
        graphe_absenceLayout.setVerticalGroup(
            graphe_absenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout absence_moisLayout = new javax.swing.GroupLayout(absence_mois);
        absence_mois.setLayout(absence_moisLayout);
        absence_moisLayout.setHorizontalGroup(
            absence_moisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        absence_moisLayout.setVerticalGroup(
            absence_moisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlstatestiquesLayout = new javax.swing.GroupLayout(pnlstatestiques);
        pnlstatestiques.setLayout(pnlstatestiquesLayout);
        pnlstatestiquesLayout.setHorizontalGroup(
            pnlstatestiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphe_employe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlstatestiquesLayout.createSequentialGroup()
                .addComponent(absence_mois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphe_absence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlstatestiquesLayout.setVerticalGroup(
            pnlstatestiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlstatestiquesLayout.createSequentialGroup()
                .addComponent(graphe_employe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlstatestiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphe_absence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(absence_mois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlprincipal.add(pnlstatestiques, "card5");

        jSplitPane2.setRightComponent(pnlprincipal);

        jPanel4.setBackground(new java.awt.Color(37, 138, 122));

        btNstatestiques.setBackground(new java.awt.Color(37, 138, 122));
        btNstatestiques.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btNstatestiques.setForeground(new java.awt.Color(241, 246, 246));
        btNstatestiques.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graphique (1).png"))); // NOI18N
        btNstatestiques.setText("STATESTIQUES");
        btNstatestiques.setBorderPainted(false);
        btNstatestiques.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btNstatestiques.setOpaque(true);
        btNstatestiques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNstatestiquesActionPerformed(evt);
            }
        });

        btnabsence.setBackground(new java.awt.Color(37, 138, 122));
        btnabsence.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnabsence.setForeground(new java.awt.Color(241, 246, 246));
        btnabsence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/absence (3) (1).png"))); // NOI18N
        btnabsence.setText("GESTION ABSENCE");
        btnabsence.setBorderPainted(false);
        btnabsence.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnabsence.setOpaque(true);
        btnabsence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnabsenceActionPerformed(evt);
            }
        });

        btnlogout.setBackground(new java.awt.Color(37, 138, 122));
        btnlogout.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnlogout.setForeground(new java.awt.Color(241, 246, 246));
        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/se-deconnecter (1).png"))); // NOI18N
        btnlogout.setText("DECONNECXION");
        btnlogout.setBorderPainted(false);
        btnlogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnlogout.setOpaque(true);
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        btntypeabcence.setBackground(new java.awt.Color(37, 138, 122));
        btntypeabcence.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btntypeabcence.setForeground(new java.awt.Color(241, 246, 246));
        btntypeabcence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/absence (2).png"))); // NOI18N
        btntypeabcence.setText("GESTION TYPEABSENCE");
        btntypeabcence.setBorderPainted(false);
        btntypeabcence.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btntypeabcence.setOpaque(true);
        btntypeabcence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntypeabcenceActionPerformed(evt);
            }
        });

        btnemplye1.setBackground(new java.awt.Color(37, 138, 122));
        btnemplye1.setFont(new java.awt.Font("URW Gothic", 1, 15)); // NOI18N
        btnemplye1.setForeground(new java.awt.Color(241, 246, 246));
        btnemplye1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/femme (2).png"))); // NOI18N
        btnemplye1.setText("GESTION EMPLYE");
        btnemplye1.setBorderPainted(false);
        btnemplye1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnemplye1.setOpaque(true);
        btnemplye1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnemplye1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profillogo.png"))); // NOI18N

        nom_utilisateur.setFont(new java.awt.Font("URW Gothic", 1, 18)); // NOI18N
        nom_utilisateur.setForeground(new java.awt.Color(241, 246, 246));
        nom_utilisateur.setText("jLabel3");

        date_actuel.setFont(new java.awt.Font("URW Gothic", 0, 14)); // NOI18N
        date_actuel.setForeground(new java.awt.Color(241, 246, 246));
        date_actuel.setText("jLabel2");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-removebg-preview (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNstatestiques, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnabsence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btntypeabcence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnemplye1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nom_utilisateur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(date_actuel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(nom_utilisateur)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_actuel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(btnemplye1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btntypeabcence, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnabsence, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btNstatestiques, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnabsenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnabsenceActionPerformed
        pnlstatestiques.setVisible(false);
        pnlemploye.setVisible(false);
        pnlabsence.setVisible(true);
        pnltypeabsence.setVisible(false);
    }//GEN-LAST:event_btnabsenceActionPerformed

    private void btNstatestiquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNstatestiquesActionPerformed
        pnlabsence.setVisible(false);
        pnltypeabsence.setVisible(false);
        pnlemploye.setVisible(false);
        pnlstatestiques.setVisible(true);
    }//GEN-LAST:event_btNstatestiquesActionPerformed

    private void btntypeabcenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntypeabcenceActionPerformed
        pnlstatestiques.setVisible(false);
        pnlemploye.setVisible(false);
        pnlabsence.setVisible(false);
        pnltypeabsence.setVisible(true);
    }//GEN-LAST:event_btntypeabcenceActionPerformed

    private void btnajouterEMployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnajouterEMployeActionPerformed
        Ajouter_Employe ajouteremploye = new Ajouter_Employe(this);
        ajouteremploye.setLocationRelativeTo(this);
        ajouteremploye.setVisible(true);
    }//GEN-LAST:event_btnajouterEMployeActionPerformed

    private void btnmodifierEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierEmployeActionPerformed
        if (employe_id_selected != -1) {
            Modifier_Employe modifieremploye = new Modifier_Employe(this, employe_id_selected);
            modifieremploye.setLocationRelativeTo(null);
            modifieremploye.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Aucun employé sélectionné",
                    "Important", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnmodifierEmployeActionPerformed

    private void btnsupprimerEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerEmployeActionPerformed
        if (employe_id_selected != -1) {
            int choix = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer cet employe ?",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (choix == JOptionPane.OK_OPTION) {
                Employe e = employeservice.getEmplyeByid(employe_id_selected);
                employeservice.deleteEmplye(e);
                JOptionPane.showMessageDialog(this, "Employé supprimé avec succès");
                this.charger_Employe_Table();
            }
            employe_id_selected = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Aucun employé sélectionné",
                    "Important", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnsupprimerEmployeActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        this.setVisible(false);
        Main login = new Main();
        login.setVisible(true);
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnchercherEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchercherEmployeActionPerformed
        Chercher_Employe chercheremployepvl = new Chercher_Employe(this);
        chercheremployepvl.setLocationRelativeTo(null);
        chercheremployepvl.setVisible(true);
    }//GEN-LAST:event_btnchercherEmployeActionPerformed

    private void btnemplye1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnemplye1ActionPerformed
        pnlstatestiques.setVisible(false);
        pnlemploye.setVisible(true);
        pnlabsence.setVisible(false);
        pnltypeabsence.setVisible(false);
    }//GEN-LAST:event_btnemplye1ActionPerformed

    private void btnsupprimerTypeabsenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerTypeabsenceActionPerformed

        if (type_id_selected != -1) {
            int choix = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer ce type d'absence?",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (choix == JOptionPane.OK_OPTION) {
                TypeAbsence type_supprime = typeabsenceservise.getTypeabsenceByid(type_id_selected);
                typeabsenceservise.deleteTypeabsence(type_supprime);
                this.charger_TypesAbsence_Table();
                JOptionPane.showMessageDialog(this, "Type d'absence supprime avec succes");
            }
            type_id_selected = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Aucun type sélectionné",
                    "Important", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnsupprimerTypeabsenceActionPerformed

    private void btnmodifierTypeabsenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierTypeabsenceActionPerformed
        if (type_id_selected != -1) {
            Modifier_TypeAbsence modifierTypePnl = new Modifier_TypeAbsence(this, type_id_selected);
            modifierTypePnl.setLocationRelativeTo(null);
            modifierTypePnl.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Aucun type sélectionné",
                    "Important", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnmodifierTypeabsenceActionPerformed

    private void btnchercherTypeabsenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchercherTypeabsenceActionPerformed
        Chercher_Typeabsence ct = new Chercher_Typeabsence(this);
        ct.setLocationRelativeTo(null);
        ct.setVisible(true);
    }//GEN-LAST:event_btnchercherTypeabsenceActionPerformed

    private void btnajouterTypeabsencActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnajouterTypeabsencActionPerformed
        Ajouter_TypeAbsence ajouterTypeAbsence = new Ajouter_TypeAbsence(this);
        ajouterTypeAbsence.setLocationRelativeTo(null);
        ajouterTypeAbsence.setVisible(true);
    }//GEN-LAST:event_btnajouterTypeabsencActionPerformed

    private void btnajouterAbsenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnajouterAbsenceActionPerformed
        Ajouter_Absence ajouterabsence = new Ajouter_Absence(this);
        ajouterabsence.setLocationRelativeTo(null);
        ajouterabsence.setVisible(true);
    }//GEN-LAST:event_btnajouterAbsenceActionPerformed

    private void btnsupprimerabsenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerabsenceActionPerformed
        if (absence_id_selected != -1) {
            int choix = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer cette absence?",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (choix == JOptionPane.OK_OPTION) {
                Absence absence_supprime = this.absenceservice.getAbsenceByid(absence_id_selected);
                this.absenceservice.deleteAbsence(absence_supprime);
                this.charger_Absence_Table();
                JOptionPane.showMessageDialog(this, "Absence supprimée avec succès");
            }
            absence_id_selected = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Aucune absence selectionneé a supprimer",
                    "Important", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnsupprimerabsenceActionPerformed

    private void tblemployesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblemployesMouseClicked
        int selectedRow = tblemployes.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblemployes.getModel();
            employe_id_selected = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Aucun employé sélectionné", "Important",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tblemployesMouseClicked

    private void tblTypeAbsenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTypeAbsenceMouseClicked
        int selectedRow = tblTypeAbsence.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblTypeAbsence.getModel();
            type_id_selected = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Aucun employé sélectionné", "Important",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tblTypeAbsenceMouseClicked

    private void tableAbsencesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAbsencesMouseClicked
        int selectedRow = tableAbsences.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tableAbsences.getModel();
            absence_id_selected = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        }
    }//GEN-LAST:event_tableAbsencesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel absence_mois;
    private javax.swing.JButton btNstatestiques;
    private javax.swing.JButton btnabsence;
    private javax.swing.JButton btnajouterAbsence;
    private javax.swing.JButton btnajouterEMploye;
    private javax.swing.JButton btnajouterTypeabsenc;
    private javax.swing.JButton btnchercherEmploye;
    private javax.swing.JButton btnchercherTypeabsence;
    private javax.swing.JButton btnemplye1;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnmodifierEmploye;
    private javax.swing.JButton btnmodifierTypeabsence;
    private javax.swing.JButton btnsupprimerEmploye;
    private javax.swing.JButton btnsupprimerTypeabsence;
    private javax.swing.JButton btnsupprimerabsence;
    private javax.swing.JButton btntypeabcence;
    private javax.swing.JLabel date_actuel;
    private javax.swing.JPanel graphe_absence;
    private javax.swing.JPanel graphe_employe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel nom_utilisateur;
    private javax.swing.JPanel pnlabsence;
    private javax.swing.JPanel pnlemploye;
    private javax.swing.JPanel pnlprincipal;
    private javax.swing.JPanel pnlstatestiques;
    private javax.swing.JPanel pnltypeabsence;
    private javax.swing.JTable tableAbsences;
    private javax.swing.JTable tblTypeAbsence;
    private javax.swing.JTable tblemployes;
    // End of variables declaration//GEN-END:variables
}
