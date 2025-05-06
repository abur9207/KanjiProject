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
import kanji.view.KanjiFrame;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Controller
{
	private String KanjiURLBase;
	
	private String selectedKanji;
	public Kanji currentKanji;
	KanjiParser parser = new KanjiParser();
	
	private KanjiFrame window;
	
	public Controller()
	{
		this.KanjiURLBase = "https://kanjiapi.dev/v1/kanji/";
		this.selectedKanji = "猫";
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
	
	
	public class KanjiInfo 
	{
	    private int grade;
	    private int jlpt;
	    private String kanji;
	    
	    @JsonProperty("freq_mainichi_shinbun")
	    private int frequency;

	    @JsonProperty("kun_readings")
	    private List<String> kunReadings;

	    private List<String> meanings;

	    @JsonProperty("name_readings")
	    private List<String> nameReadings;

	    private List<String> notes;

	    @JsonProperty("on_readings")
	    private List<String> onReadings;

	    @JsonProperty("stroke_count")
	    private int strokeCount;

	    private String unicode;

	    public int getFreqMainichiShinbun() 
	    {
	        return frequency;
	    }

	    public void setFreqMainichiShinbun(int freqMainichiShinbun) 
	    {
	        this.frequency = freqMainichiShinbun;
	    }

	    public int getGrade() 
	    {
	        return grade;
	    }

	    public void setGrade(int grade) 
	    {
	        this.grade = grade;
	    }

	    public int getJlpt() 
	    {
	        return jlpt;
	    }

	    public void setJlpt(int jlpt) 
	    {
	        this.jlpt = jlpt;
	    }

	    public String getKanji() 
	    {
	        return kanji;
	    }

	    public void setKanji(String kanji) 
	    {
	        this.kanji = kanji;
	    }

	    public List<String> getKunReadings() 
	    {
	        return kunReadings;
	    }

	    public void setKunReadings(List<String> kunReadings) 
	    {
	        this.kunReadings = kunReadings;
	    }

	    public List<String> getMeanings() 
	    {
	        return meanings;
	    }

	    public void setMeanings(List<String> meanings) 
	    {
	        this.meanings = meanings;
	    }

	    public List<String> getNameReadings() 
	    {
	        return nameReadings;
	    }

	    public void setNameReadings(List<String> nameReadings) 
	    {
	        this.nameReadings = nameReadings;
	    }

	    public List<String> getNotes() 
	    {
	        return notes;
	    }

	    public void setNotes(List<String> notes) 
	    {
	        this.notes = notes;
	    }

	    public List<String> getOnReadings() 
	    {
	        return onReadings;
	    }

	    public void setOnReadings(List<String> onReadings) 
	    {
	        this.onReadings = onReadings;
	    }

	    public int getStrokeCount() 
	    {
	        return strokeCount;
	    }

	    public void setStrokeCount(int strokeCount) 
	    {
	        this.strokeCount = strokeCount;
	    }

	    public String getUnicode() 
	    {
	        return unicode;
	    }

	    public void setUnicode(String unicode) 
	    {
	        this.unicode = unicode;
	    }
	}
	
	public class KanjiParser 
	{

	    public KanjiInfo parseKanjiJson(String jsonString) 
	    {
	        ObjectMapper mapper = new ObjectMapper();

	        try 
	        {
	            // Convert the JSON string into a KanjiInfo object
	            KanjiInfo info = mapper.readValue(jsonString, KanjiInfo.class);
	            return info;

	        } 
	        catch (Exception error) 
	        {
	            handleError(error);
	            return null;
	        }
	    }
	}
	
}
	
	
































