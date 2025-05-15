package kanji.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFExporter
{
	public static void exportKanjiToPDF(KanjiInfo info) throws FileNotFoundException, DocumentException 
	{
        Document document = new Document();
        String filename = "kanji_" + info.getKanji() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
        document.add(new Paragraph("Kanji: " + info.getKanji()));
        document.add(new Paragraph("Meaning: " + info.getMeanings()));
        document.add(new Paragraph("On'yomi: " + String.join(", ", info.getOnReadings())));
        document.add(new Paragraph("Kun'yomi: " + String.join(", ", info.getKunReadings())));
        document.close();
    }
}
