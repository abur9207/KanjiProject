package kanji.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import kanji.controller.Controller;

public class KanjiPanel extends JPanel
{
	private Controller app;
	
	private InputPanel inputPanel;
	private KanjiCharacterPanel charactersPanel;
	
	public KanjiPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.setLayout(new BorderLayout());
		this.charactersPanel = new KanjiCharacterPanel(app);
		this.inputPanel = new InputPanel(app, charactersPanel);
		
		
		
		setupPanel();
		setupListeners();
	}
	
	private void setupPanel()
	{
		setBackground(Color.LIGHT_GRAY);
		
		this.add(inputPanel, BorderLayout.NORTH);
		this.add(charactersPanel, BorderLayout.CENTER);
	}
	
	private void setupListeners()
	{
		
	}
	
	public KanjiCharacterPanel getCharactersPanel() {
	    return charactersPanel;
	}
}
