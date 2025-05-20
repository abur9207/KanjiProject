package kanji.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Utility class responsible for exporting {@link KanjiInfo} data to a PDF file.
 */
public class PDFExporter
{
	/**
	 * Exports the details of a given {@link KanjiInfo} object to a PDF file.
	 * The output file will be named using the kanji character (e.g., "kanji_猫.pdf").
	 *
	 * @param info the {@link KanjiInfo} object containing data to be written to the PDF
	 * @throws FileNotFoundException if the output file cannot be created or opened
	 * @throws DocumentException if an error occurs while writing the PDF document
	 */
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
