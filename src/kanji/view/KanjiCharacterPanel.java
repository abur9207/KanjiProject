package kanji.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private JLabel kanjiLabel;
	private JLabel meaningLabel;
    private JLabel onLabel;
    private JLabel kunLabel;
	
	private JPanel displayPanel;
	
	KanjiParser parser = new KanjiParser();
	
	
	public KanjiCharacterPanel(Controller app)
	{
		super();
		this.app = app;

		this.displayPanel = new JPanel(new GridLayout(1,0));
		
		//String jsonData = app.JsonApiReader(app.selectedKanji);
		//KanjiInfo info = parser.parseKanjiJson(jsonData);
		this.kanjiLabel = new JLabel(app.selectedKanji);
		this.meaningLabel = new JLabel("meanings: ");
    	this.onLabel = new JLabel("onyomi readings: ");
    	this.kunLabel = new JLabel("kunyomi readings: ");
		
		Font characterFont = new Font("Serif", Font.PLAIN, 48);
		kanjiLabel.setFont(characterFont);
		
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.setPreferredSize(new Dimension(600, 400));
		
		this.add(kanjiLabel);
		this.add(Box.createVerticalStrut(10));
		this.add(meaningLabel);
		this.add(Box.createVerticalStrut(5));
    	this.add(onLabel);
    	this.add(Box.createVerticalStrut(5));
    	this.add(kunLabel);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setAlignmentX(LEFT_ALIGNMENT);
	}
	
	public void updateDisplay(KanjiInfo info)
	{
		if (info == null)
		{
			 kanjiLabel.setText("No data found.");
		     meaningLabel.setText("");
		     onLabel.setText("");
		     kunLabel.setText("");
		}
		
		kanjiLabel.setText("Kanji: " + info.getKanji());
	    meaningLabel.setText("Meaning: " + info.getMeanings());
	    onLabel.setText("On'yomi: " + String.join(", ", info.getOnReadings()));
	    kunLabel.setText("Kun'yomi: " + String.join(", ", info.getKunReadings()));
	}
	

}
















