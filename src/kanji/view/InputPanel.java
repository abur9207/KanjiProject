package kanji.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kanji.controller.Controller;

public class InputPanel extends JPanel
{
	private JTextField inputField;
    private JButton searchButton;
    private JLabel displayedCharacter;
    private JLabel meaningLabel;
    
    public InputPanel(Controller app)
    {
    	super();
    	
    	this.inputField = new JTextField(5);
    	this.searchButton = new JButton("Search");
    	displayedCharacter = new JLabel("Kanji will appear here", SwingConstants.CENTER);
    	meaningLabel = new JLabel("", SwingConstants.CENTER);

    	
    	setupPanel();
    	setupLayout();
    	setupListeners();
    }
    private void setupPanel()
	{
    	this.add(new JLabel("Enter Kanji"));
    	this.add(inputField);
    	this.add(searchButton);
    	
    	
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
}
