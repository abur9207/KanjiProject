package kanji.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import kanji.controller.Controller;

public class KanjiPanel extends JPanel
{
	private Controller app;
	private BorderLayout layout;
	
	private InputPanel inputPanel;
	private KanjiCharacterPanel charactersPanel;
	
	public KanjiPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new BorderLayout();
		this.charactersPanel = new KanjiCharacterPanel(app);
		this.inputPanel = new InputPanel(app, charactersPanel);
		
		
		
		setupPanel();
		setupListeners();
		setupLayout();
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
	
	private void setupLayout()
	{
		this.setLayout(layout);
	}
}
