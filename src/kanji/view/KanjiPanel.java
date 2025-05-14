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
	
	
	public KanjiPanel(Controller app)
	{
		super();
		
		this.layout = new SpringLayout();
		this.inputPanel = new InputPanel(app);
		
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		setBackground(Color.LIGHT_GRAY);
		
		
		this.add(inputPanel, BorderLayout.NORTH);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
}
