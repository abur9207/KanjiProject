package kanji.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import kanji.model.Kanji;
import kanji.view.KanjiFrame;

public class Controller
{
	private String KanjiURLBase;
	private String appendForJSON;
	private String defaultTag;
	private ArrayList<Kanji> kanjiList;
	
	private KanjiFrame window;
	
	public Controller()
	{
		this.KanjiURLBase = "https://kanjiapi.dev/v1/kanji/";
		this.appendForJSON = "?json=true";
		this.defaultTag = "猫";
		this.kanjiList = new ArrayList<Kanji>();
		
		this.window = new KanjiFrame(this);
	}
	
	public void start()
	{
		testKanjiAPI();
	}
	
	public void testKanjiAPI()
	{
		Kanji demoKanji = new Kanji("person", "N5", null, null, null);
		
		String result = "toString:\n" + demoKanji.toString();
		System.out.println(result);
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
	public String addKanji (String tag)
	{
		String catDetails = "";
		
		if (tag == null || tag.length() < 3) // makes sure that the tag is valid
		{
			tag = defaultTag;
		}
		
		Kanji retrievedCat = (Kanji) IOController.readSingleJSON(this, KanjiURLBase, tag + appendForJSON);
		
		kanjiList.add(retrievedCat);
		
		if (retrievedCat != null) // makes sure we dont try to retrieve a toString of a null value. which would cause an error
		{
			catDetails = retrievedCat.toString();
		}
		
		
		return catDetails;
	}
}






























