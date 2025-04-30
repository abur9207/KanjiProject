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

public class KanjiCharacterPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JButton getKanjiButton;
	private JLabel displayedCharacter;
	
	private JPanel displayPanel;
	
	
	public KanjiCharacterPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new SpringLayout();
		this.displayedCharacter = new JLabel("猫");
		this.displayPanel = new JPanel(new GridLayout(1,0));
		
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
	
	private void getKanji(String character)
	{
		app.getKanjiURL(character);
	}
	

}
















