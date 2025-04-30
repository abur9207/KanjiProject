package kanji.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import kanji.controller.Controller;

public class KanjiCharacterPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JButton getKanjiButton;
	private JLabel displayedCharacter;
	
	public KanjiCharacterPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new SpringLayout();
		this.getKanjiButton = new JButton("Get Kanji");
		this.displayedCharacter = new JLabel("");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.add(getKanjiButton);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
	

}
