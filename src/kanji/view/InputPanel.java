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
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import kanji.controller.Controller;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;

public class InputPanel extends JPanel
{
	private SpringLayout layout;
	private JTextField inputField;
    private JButton searchButton;
    private Controller app;
    
    private KanjiCharacterPanel charactersPanel;
    
    public InputPanel(Controller app)
    {
    	super();
    	this.app = app;
    	this.charactersPanel = new KanjiCharacterPanel(app);
    	
    	this.layout = new SpringLayout();
    	this.inputField = new JTextField(10);
    	this.searchButton = new JButton("Search");
    	
    	
    	setupPanel();
    	setupLayout();
    	setupListeners();
    }
    
    private void setupPanel()
	{
    	this.add(charactersPanel);
    	
    	this.add(new JLabel("Enter Kanji"));
    	this.add(inputField);
    	this.add(searchButton);
    	
	}
	
	private void setupListeners()
	{
		searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String enteredKanji = inputField.getText().trim();
				app.selectedKanji = enteredKanji;
				KanjiInfo info = app.getKanjiInfo(enteredKanji);
				
				
				if (!enteredKanji.isEmpty())
				{
					
					charactersPanel.updateDisplay(info);
				}
				
				System.out.println(app.selectedKanji);
			}
		}); 
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, charactersPanel, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, charactersPanel, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, charactersPanel, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST,  charactersPanel,  0, SpringLayout.WEST, this);
	}
	
	
	
	
}
