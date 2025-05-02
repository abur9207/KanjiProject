package kanji.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import kanji.model.Kanji;
import kanji.view.KanjiFrame;

public class Controller
{
	private String KanjiURLBase;
	
	private String appendForJSON;
	private String selectedKanji;
	private ArrayList<Kanji> kanjiList;
	public Kanji currentKanji;
	
	private KanjiFrame window;
	
	public Controller()
	{
		this.KanjiURLBase = "https://kanjiapi.dev/v1/kanji/";
		this.appendForJSON = "?json=true";
		this.selectedKanji = "猫";
		this.kanjiList = new ArrayList<Kanji>();
		
		this.window = new KanjiFrame(this);
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
		getKanjiURL("猫");
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
			kanjiURL = URI.create(KanjiURLBase + character).toURL();
		}
		catch (MalformedURLException error)
		{
			handleError(error);
		}
		System.out.println(kanjiURL);
		
		return kanjiURL;
		
	}
	
	public void JsonApiReader (String character)
	{
		
		try
		{
		URL url = getKanjiURL(character);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		
		int responseCode = connection.getResponseCode();
		
			if (responseCode != 200)
			{
			throw new RuntimeException("HttpResponseCode: " + responseCode);
			}
			else
			{
			String inline = "";
			Scanner scanner = new Scanner(url.openStream());
			
				while (scanner.hasNext()) 
				{
				inline += scanner.nextLine();	
				}
			}
		}
		catch (IOException error)
		{
			handleError(error);
		}
		
	}
}
	
	
































