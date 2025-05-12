package kanji.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import javax.swing.JOptionPane;
import kanji.model.Kanji;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;
import kanji.view.InputPanel;
import kanji.view.KanjiFrame;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Controller
{
	private String KanjiURLBase;
	public String selectedKanji;
	public Kanji currentKanji;
	private InputPanel inputPanel;
	KanjiParser parser = new KanjiParser();
	
	private KanjiFrame window;
	
	public Controller()
	{
		this.KanjiURLBase = "https://kanjiapi.dev/v1/kanji/";
		this.inputPanel = inputPanel;
		this.selectedKanji = "蛍";
		
		KanjiInfo info = parser.parseKanjiJson(JsonApiReader(selectedKanji));
		
		this.window = new KanjiFrame(this);
		
		
		
		
		if (info != null) 
		{
            System.out.println("Kanji: " + info.getKanji());
            System.out.println("Meanings: " + info.getMeanings());
            System.out.println("Kunyomi: " + info.getKunReadings());
            System.out.println("Onyomi: " + info.getOnReadings());
        } else 
        {
            System.out.println("Failed to parse JSON.");
        }
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(window, error.getMessage(), "there was an error :/", JOptionPane.ERROR_MESSAGE);
	}

	public void save(String content, String filePath)
	{
		IOController.writeTextForFile(this, content, filePath);
	}
	
	public String load(String pathToFile)
	{
		String data = IOController.readTextFromFile(this, pathToFile);
		
		return data;
	}
	
	public void start()
	{
		//testKanjiAPI();
		//getKanjiURL("猫");
		//JsonApiReader(encodeKanji("猫"));
		
	}
	
	public void testKanjiAPI()
	{
		Kanji demoKanji = new Kanji("person", "N5", null, null, null);
		
		String result = "toString:\n" + demoKanji.toString();
		System.out.println(result);
	}
	
	public URL getKanjiURL (String character)
	{
		URL kanjiURL = null;
		
		String kanjiName = "";
		
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
		kanji = selectedKanji;
		
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
	
	public String getKanjiInfo(String kanji) 
	{
	    try {
	        String apiUrl = "https://kanjiapi.dev/v1/kanji/" + URLEncoder.encode(kanji, "UTF-8");
	        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
	        conn.setRequestMethod("GET");

	        if (conn.getResponseCode() != 200) {
	            return "Kanji not found.";
	        }

	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder response = new StringBuilder();
	        String line;
	        while ((line = in.readLine()) != null) {
	            response.append(line);
	        }
	        in.close();

	        // Parse using the parser
	        KanjiParser parser = new KanjiParser();
	        KanjiInfo info = parser.parseKanjiJson(response.toString());

	        if (info == null) {
	            return "Failed to parse Kanji info.";
	        }

	        return "<html>" +
	               "Kanji: " + info.kanji + "<br>" +
	               "Grade: " + info.grade + "<br>" +
	               "Strokes: " + info.strokeCount + "<br>" +
	               "Onyomi: " + String.join(", ", info.onReadings) + "<br>" +
	               "Kunyomi: " + String.join(", ", info.kunReadings) + "<br>" +
	               "Meanings: " + String.join(", ", info.meanings) +
	               "</html>";

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error retrieving Kanji info.";
	    }
	}
	
}
	
































