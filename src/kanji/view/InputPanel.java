package kanji.view;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kanji.controller.Controller;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;

public class InputPanel extends JPanel
{
	private JTextField inputField;
    private JButton searchButton;
    private JLabel displayedCharacter;
    private JLabel resultLabel;
    private Controller controller;
    private FlowLayout layout;
    
    public InputPanel(Controller app)
    {
    	super();
    	
    	this.layout = new FlowLayout();
    	this.inputField = new JTextField(10);
    	this.searchButton = new JButton("Search");
    	this.displayedCharacter = new JLabel("Kanji will appear here", SwingConstants.CENTER);
    	this.resultLabel = new JLabel("info will appear here", SwingConstants.CENTER);

    	displayedCharacter.setFont(new Font("Serif", Font.PLAIN, 32));
    	
    	setupPanel();
    	setupLayout();
    	setupListeners();
    }
    
    private void setupPanel()
	{
    	this.add(new JLabel("Enter Kanji"));
    	this.add(inputField);
    	this.add(searchButton);
    	this.add(resultLabel);
    	
	}
	
	private void setupListeners()
	{
		searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String enteredKanji = inputField.getText().trim();
				if (!enteredKanji.isEmpty())
				{
					controller.getKanjiInfo(enteredKanji);
				}
				else
				{
				resultLabel.setText("enter a kanji");
				}
				
			}
		}); 
	}
	
	private void setupLayout()
	{
		
	}
	
	
	
	
}
