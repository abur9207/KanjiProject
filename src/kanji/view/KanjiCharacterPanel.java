package kanji.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import kanji.controller.Controller;
import kanji.model.Kanji;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;

public class KanjiCharacterPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JButton getKanjiButton;
	private JLabel displayedCharacter;
	private JLabel meaningLabel;
    private JLabel onLabel;
    private JLabel kunLabel;
	
	private JPanel displayPanel;
	
	KanjiParser parser = new KanjiParser();
	
	
	public KanjiCharacterPanel(Controller app)
	{
		super();
		this.app = app;
		
		
		
		this.layout = new SpringLayout();
		
		this.displayPanel = new JPanel(new GridLayout(1,0));
		
		//String jsonData = app.JsonApiReader(app.selectedKanji);
		//KanjiInfo info = parser.parseKanjiJson(jsonData);
		this.displayedCharacter = new JLabel(app.selectedKanji);
		this.meaningLabel = new JLabel("meanings: ");
    	this.onLabel = new JLabel("onyomi readings: ");
    	this.kunLabel = new JLabel("kunyomi readings: ");
		
		Font characterFont = new Font("Ariel", Font.PLAIN, 30);
		displayedCharacter.setFont(characterFont);
		
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		displayPanel.add(displayedCharacter);
		
		this.add(displayPanel);
		this.add(meaningLabel);
    	this.add(onLabel);
    	this.add(kunLabel);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
	
	public void updateDisplay(KanjiInfo info)
	{
		if (info == null)
		{
			 displayedCharacter.setText("No data found.");
		     meaningLabel.setText("");
		     onLabel.setText("");
		     kunLabel.setText("");
		}
		
		displayedCharacter.setText("Kanji: " + info.getKanji());
	    meaningLabel.setText("Meaning: " + info.getMeanings());
	    onLabel.setText("On'yomi: " + String.join(", ", info.getOnReadings()));
	    kunLabel.setText("Kun'yomi: " + String.join(", ", info.getKunReadings()));
	}
	

}
















