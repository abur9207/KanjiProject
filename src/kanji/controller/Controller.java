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
import java.util.Scanner;

import javax.swing.JOptionPane;

import kanji.model.Kanji;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;
import kanji.view.KanjiCharacterPanel;
import kanji.view.KanjiFrame;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller
{
	private String KanjiURLBase;
	public String selectedKanji;
	public Kanji currentKanji;
	KanjiParser parser = new KanjiParser();
	
	private KanjiFrame window;
	
	public Controller()
	{
		this.KanjiURLBase = "https://kanjiapi.dev/v1/kanji/";
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
	
	public KanjiInfo getKanjiInfo(String kanji) {
        try {
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

            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }

            reader.close();
            connection.disconnect();

            // 4. Parse the JSON into a KanjiInfo object
            String json = responseBuilder.toString();
            return parser.parseKanjiJson(json);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if something went wrong
        }
    }
	
	public KanjiCharacterPanel getCharactersPanel() 
	{
	    return window.getCharactersPanel();
	}
}
































