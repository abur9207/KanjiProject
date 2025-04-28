package kanji.view;

import javax.swing.JButton;
import javax.swing.SpringLayout;

import kanji.controller.Controller;

public class KanjiPanel
{
	private Controller controller;
	private SpringLayout layout;
	private JButton kanjiButton;
	
	public KanjiPanel(Controller app)
	{
		super();
		
		this.controller = controller;
		this.layout = new SpringLayout();
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
}
