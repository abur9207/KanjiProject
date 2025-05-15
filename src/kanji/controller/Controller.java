package kanji.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
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
	
	public void exportKanjiInfoToPDF(KanjiInfo info, String filePath) throws Exception 
	{
	    Document document = new Document();
	    PdfWriter.getInstance(document, new FileOutputStream(filePath));
	    document.open();

	    document.add(new Paragraph("Kanji: " + info.getKanji()));
	    document.add(new Paragraph("Meaning: " + info.getMeanings()));
	    document.add(new Paragraph("On'yomi: " + String.join(", ", info.getOnReadings())));
	    document.add(new Paragraph("Kun'yomi: " + String.join(", ", info.getKunReadings())));

	    document.close();
	}
}