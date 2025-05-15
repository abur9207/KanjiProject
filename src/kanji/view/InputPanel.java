package kanji.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import kanji.model.PDFExporter;

public class InputPanel extends JPanel
{
	
	private JTextField inputField;
    private JButton searchButton;
    private JButton downloadPDFButton;
    private Controller app;
    private JPanel inputBar;
    
    public InputPanel(Controller app, KanjiCharacterPanel charactersPanel)
    {
    	super();
    	this.app = app;
    	
    	this.inputBar = new JPanel();
    	this.inputField = new JTextField(10);
    	this.searchButton = new JButton("Search");
    	this.downloadPDFButton = new JButton("Download as PDF");
    	
    	setupLayout();
    	setupPanel();
    	setupListeners();
    }
    
    private void setupPanel()
	{
    	this.setPreferredSize(new Dimension(600, 50));
    	
    	inputBar.add(inputField);
    	inputBar.add(searchButton);
    	inputBar.add(downloadPDFButton);
    	
    	this.add(inputBar, BorderLayout.CENTER);
	}
	
	private void setupListeners()
	{
		searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String enteredKanji = inputField.getText().trim();
				KanjiInfo info = app.getKanjiInfo(enteredKanji);

				if (!enteredKanji.isEmpty())
				{
					app.getCharactersPanel().updateDisplay(info);
				}
			}
		}); 
		
		downloadPDFButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        KanjiInfo info = app.getKanjiInfo(inputField.getText().trim());
		        if (info != null) {
		            try {
		                PDFExporter.exportKanjiToPDF(info);
		                JOptionPane.showMessageDialog(null, "PDF saved successfully.");
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Failed to save PDF: " + ex.getMessage());
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "No kanji information to export.");
		        }
		    }
		});
	}
	
	private void setupLayout()
	{
		setLayout(new BorderLayout());
		inputBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(new Dimension(600,60));
	}	
}