package kanji.view;

import java.awt.BorderLayout;
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
	
	private InputPanel inputPanel;
	private KanjiCharacterPanel charactersPanel;
	
	public KanjiPanel(Controller app)
	{
		super();
		
		this.layout = new SpringLayout();
		this.inputPanel = new InputPanel(app);
		this.charactersPanel = new KanjiCharacterPanel(app);
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		setBackground(Color.LIGHT_GRAY);
		
		this.add(charactersPanel);
		this.add(inputPanel, BorderLayout.NORTH);
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
