package kanji.controller;

import java.io.IOException;

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
import kanji.view.KanjiFrame;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;


@SuppressWarnings("unused")
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
	
	public String JsonApiReader (String character)
	{
		String inline = "";
		try
		{
		URL url = getKanjiURL(character);
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
}
	
































