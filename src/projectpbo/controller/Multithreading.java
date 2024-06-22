package projectpbo.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public class Multithreading implements Runnable {
    private final String username;
    private final String feedback;
    private final String filename;

    public Multithreading(String username, String feedback, String filename) {
        this.username = username;
        this.feedback = feedback;
        this.filename = filename;
    }

    @Override
    public void run() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Username: " + username);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Feedback: " + feedback);
            contentStream.endText();
            contentStream.close();

            document.save(filename);
            document.close();

            File file = new File(filename);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

