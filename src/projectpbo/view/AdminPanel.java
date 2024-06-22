package projectpbo.view;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import projectpbo.controller.Controller;
import projectpbo.controller.Multithreading;
import projectpbo.model.Model;

public class AdminPanel extends javax.swing.JPanel {
    private Controller controller;
    private List<Model> feedbackEntries;
    private int currentIndex;
    
    public AdminPanel(Controller controller) {
        this.controller = controller;
        initComponents();
        initializePanel();
        displayFeedbackEntry(feedbackEntries.get(currentIndex));
    }
    
    public void initializePanel() {
        feedbackEntries = controller.getFeedbackEntries();

        if (feedbackEntries.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada entry yang ditemukan.");
            return;
        }

        currentIndex = 0;
        displayFeedbackEntry(feedbackEntries.get(currentIndex));
    }
    
    private void displayFeedbackEntry(Model entry) {
        user.setText(entry.getUsername());
        feedback.setText(entry.getFeedback());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        next = new javax.swing.JButton();
        previous = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        feedback = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        toPDF = new javax.swing.JButton();
        toPDFAll = new javax.swing.JButton();

        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        previous.setText("Prev");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        jLabel1.setText("Submitted By:");

        jLabel2.setText("Feedback:");

        user.setEditable(false);

        feedback.setEditable(false);
        feedback.setColumns(20);
        feedback.setRows(5);
        jScrollPane1.setViewportView(feedback);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Admin Panel");

        toPDF.setText("Current to PDF");
        toPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toPDFActionPerformed(evt);
            }
        });

        toPDFAll.setText("All to PDF");
        toPDFAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toPDFAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toPDFAll, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(previous)
                    .addComponent(toPDF)
                    .addComponent(toPDFAll))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        if (currentIndex > 0) {
            currentIndex--;
            displayFeedbackEntry(feedbackEntries.get(currentIndex));
        } else {
            JOptionPane.showMessageDialog(this, "Sudah berada di entry pertama.");
        }
    }//GEN-LAST:event_previousActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        if (currentIndex < feedbackEntries.size() - 1) {
            currentIndex++;
            displayFeedbackEntry(feedbackEntries.get(currentIndex));
        } else {
            JOptionPane.showMessageDialog(this, "Sudah berada di entry terakhir.");
        }
    }//GEN-LAST:event_nextActionPerformed

    private void toPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toPDFActionPerformed
        for (Model entry : feedbackEntries) {
            generatePDF(entry);
        }
        JOptionPane.showMessageDialog(this, "Entry sedang di ekspor ke PDF.");
    }//GEN-LAST:event_toPDFActionPerformed

    private void toPDFAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toPDFAllActionPerformed
        StringBuilder allFeedbacks = new StringBuilder();
       for (Model entry : feedbackEntries) {
           allFeedbacks.append("Username: ").append(entry.getUsername()).append("\n");
           allFeedbacks.append("Feedback: ").append(entry.getFeedback()).append("\n\n");
       }

       generateCombinedPDF(allFeedbacks.toString());
       JOptionPane.showMessageDialog(this, "Semua entry sedang di ekspor ke PDF.");
    }//GEN-LAST:event_toPDFAllActionPerformed

    private void generatePDF(Model entry) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String directoryPath = "PDFs";
        String filename = directoryPath + "/" + entry.getUsername() + "_feedback_" + timestamp + ".pdf";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        Multithreading task = new Multithreading(entry.getUsername(), entry.getFeedback(), filename);
        Thread thread = new Thread(task);
        thread.start();
    }

    
    private void generateCombinedPDF(String content) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String directoryPath = "PDFs";
        String filename = directoryPath + "/All_Feedback_" + timestamp + ".pdf";
        
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            float yPosition = 700;
            String[] lines = content.split("\n");
            for (String line : lines) {
                contentStream.showText(line);
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
            }

            contentStream.endText();
            contentStream.close();

            document.save(filename);
            document.close();

            File file = new File(filename);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error memuat PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea feedback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    private javax.swing.JButton toPDF;
    private javax.swing.JButton toPDFAll;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
