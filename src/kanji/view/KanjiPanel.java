package kanji.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import kanji.controller.Controller;

public class KanjiPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JButton kanjiButton;
	
	private KanjiCharacterPanel charactersPanel;
	private JPanel buttonPanel;
	
	public KanjiPanel(Controller app)
	{
		super();
		
		this.app = app;
		this.layout = new SpringLayout();
		
		this.kanjiButton = new JButton("kanjiButton");
		
		this.charactersPanel = new KanjiCharacterPanel(app);
		this.buttonPanel = new JPanel(new GridLayout(1,0));
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		setBackground(Color.LIGHT_GRAY);
		
		buttonPanel.add(kanjiButton);
		
		this.add(charactersPanel);
		this.add(buttonPanel);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, charactersPanel, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, charactersPanel, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, charactersPanel, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST,  charactersPanel,  0, SpringLayout.WEST, this);
	}
}
