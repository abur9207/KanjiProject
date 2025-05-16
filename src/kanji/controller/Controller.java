package kanji.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JOptionPane;

import kanji.model.Kanji;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;
import kanji.view.KanjiCharacterPanel;
import kanji.view.KanjiFrame;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

public class Controller
{
	private String KanjiURLBase;

	public Kanji currentKanji;
	KanjiParser parser = new KanjiParser();
	
	private KanjiFrame window;
	
	public Controller()
	{
		this.KanjiURLBase = "https://kanjiapi.dev/v1/kanji/";
		this.window = new KanjiFrame(this);
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(window, error.getMessage(), "there was an error :/", JOptionPane.ERROR_MESSAGE);
	}

	public void start()
	{
		//testKanjiAPI();
		//getKanjiURL("猫");
		//JsonApiReader(encodeKanji("猫"));
	}
	
	public URL getKanjiURL (String character)
	{
		URL kanjiURL = null;
		
		try
		{
			kanjiURL = URI.create(KanjiURLBase + encodeKanji(character)).toURL();
		}
		catch (MalformedURLException error)
		{
			handleError(error);
		}
		System.out.println(kanjiURL);
		
		return kanjiURL;
	}
	
	public String encodeKanji(String kanji)
	{
		try
		{
			return URLEncoder.encode(kanji, StandardCharsets.UTF_8.toString());
		}
		catch (Exception error)
		{
			handleError(error);
			return null;
		}
	}
	
	public String JsonApiReader (String kanji)
	{
		String inline = "";
		try
		{
		URL url = getKanjiURL(kanji);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		
		//getting response code
		int responseCode = connection.getResponseCode();
		
			if (responseCode != 200)
			{
			throw new RuntimeException("HttpResponseCode: " + responseCode);
			}
			else
			{
			Scanner scanner = new Scanner(connection.getInputStream());
			
				//using scanner to write all the JSON data into a string
				while (scanner.hasNext()) 
				{
				inline += scanner.nextLine();	
				}
				scanner.close(); //close the scanner
			}
		}
		catch (IOException error)
		{
			handleError(error);
		}
		System.out.println(inline);
		return inline;
	}
	
	public KanjiInfo getKanjiInfo(String kanji) 
	{
		if (kanji == null || kanji.trim().isEmpty()) 
		{
	        System.out.println("No kanji provided for API call.");
	        return null;
	    }
		
        try 
        {
            // 1. Build the API URL
            String apiUrl = KanjiURLBase + encodeKanji(kanji);

            // 2. Set up the connection
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // 3. Read the response
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            StringBuilder responseBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) 
            {
                responseBuilder.append(line);
            }

            reader.close();
            connection.disconnect();

            // 4. Parse the JSON into a KanjiInfo object
            String json = responseBuilder.toString();
            return parser.parseKanjiJson(json);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null; // Return null if something went wrong
        }
    }
	
	public KanjiCharacterPanel getCharactersPanel() 
	{
	    return window.getCharactersPanel();
	}
	
	public void exportKanjiToPDF(KanjiInfo info, File saveFile) {
	    try {
	        // Load font from resources
	        InputStream fontStream = getClass().getResourceAsStream("/NotoSansJP-VariableFont_wght.ttf");
	        byte[] fontBytes = fontStream.readAllBytes();
	        BaseFont baseFont = BaseFont.createFont("NotoSansJP.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, fontBytes, null);

	        Font kanjiFont = new Font(baseFont, 100);
	        Font labelFont = new Font(baseFont, 18, Font.BOLD);
	        Font textFont = new Font(baseFont, 16);

	        Document document = new Document(PageSize.A6, 20, 20, 20, 20);
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(saveFile));
	        document.open();

	        // -------- FRONT (Kanji only) --------
	        Paragraph kanji = new Paragraph(info.getKanji(), kanjiFont);
	        kanji.setAlignment(Element.ALIGN_CENTER);
	        document.add(kanji);

	        document.newPage();

	        // -------- BACK (Readings + Meanings) --------
	        Paragraph onLabel = new Paragraph("On’yomi:", labelFont);
	        Paragraph onReading = new Paragraph(String.join(", ", info.getOnReadings()), textFont);
	        Paragraph kunLabel = new Paragraph("Kun’yomi:", labelFont);
	        Paragraph kunReading = new Paragraph(String.join(", ", info.getKunReadings()), textFont);
	        Paragraph meaningLabel = new Paragraph("Meaning(s):", labelFont);
	        Paragraph meanings = new Paragraph(info.getMeanings().toString(), textFont);

	        // Layout back with spacing
	        document.add(meaningLabel);
	        document.add(meanings);
	        document.add(Chunk.NEWLINE);
	        document.add(onLabel);
	        document.add(onReading);
	        document.add(Chunk.NEWLINE);
	        document.add(kunLabel);
	        document.add(kunReading);

	        document.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}