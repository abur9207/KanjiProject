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
	
	private JPanel displayPanel;
	
	KanjiParser parser = new KanjiParser();
	
	
	public KanjiCharacterPanel(Controller app)
	{
		super();
		this.app = app;
		
		
		
		this.layout = new SpringLayout();
		
		this.displayPanel = new JPanel(new GridLayout(1,0));
		
		String jsonData = app.JsonApiReader(app.selectedKanji);
		KanjiInfo info = parser.parseKanjiJson(jsonData);
		this.displayedCharacter = new JLabel(info.getKanji());
		
		Font characterFont = new Font("Ariel", Font.PLAIN, 300);
		displayedCharacter.setFont(characterFont);
		
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		displayPanel.add(displayedCharacter);
		
		this.add(displayPanel);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
	

}
















