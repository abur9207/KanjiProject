package kanji.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private KanjiCharacterPanel charactersPanel;
    
    public InputPanel(Controller app, KanjiCharacterPanel charactersPanel)
    {
    	super();
    	this.app = app;
    	this.charactersPanel = charactersPanel;
    	
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
		        if (!enteredKanji.isEmpty()) {
		            KanjiInfo info = app.getKanjiInfo(enteredKanji);
		            charactersPanel.updateDisplay(info);
		        }
		    }
		});
		
		downloadPDFButton.addActionListener(e -> {
			String enteredKanji = inputField.getText().trim();
			KanjiInfo info = app.getKanjiInfo(enteredKanji);
		    if (info == null) 
		    {
		        JOptionPane.showMessageDialog(this, "No kanji selected.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Save Kanji PDF");
		    fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));

		    int userSelection = fileChooser.showSaveDialog(this);

		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		        File fileToSave = fileChooser.getSelectedFile();

		        // Ensure it ends in ".pdf"
		        if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) 
		        {
		            fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".pdf");
		        }

		        try 
		        {
		            app.exportKanjiInfoToPDF(info, fileToSave.getAbsolutePath());
		            JOptionPane.showMessageDialog(this, "PDF saved successfully.");
		        } 
		        catch (Exception ex) 
		        {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(this, "Failed to save PDF.", "Error", JOptionPane.ERROR_MESSAGE);
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