package kanji.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kanji.controller.Controller;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;

public class KanjiCharacterPanel extends JPanel
{
	private JLabel kanjiLabel;
	private JLabel meaningLabel;
    private JLabel onLabel;
    private JLabel kunLabel;
	
    private JPanel ContentPanel;
	
	KanjiParser parser = new KanjiParser();
	
	
	public KanjiCharacterPanel(Controller app)
	{
		super();
		
		this.ContentPanel = new JPanel();
		this.kanjiLabel = new JLabel("");
		this.meaningLabel = new JLabel("Enter the Kanji you want to search");
    	this.onLabel = new JLabel("");
    	this.kunLabel = new JLabel("");
		
		Font characterFont = new Font("Serif", Font.PLAIN, 72);
		kanjiLabel.setFont(characterFont);
		
		setupPanel();
		setupLayout();
	}
	
	private void setupPanel()
	{
		ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.Y_AXIS));
		ContentPanel.setOpaque(false);
		
		kanjiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		meaningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		onLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		kunLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ContentPanel.add(kanjiLabel);
		ContentPanel.add(Box.createVerticalStrut(10));
		ContentPanel.add(meaningLabel);
		ContentPanel.add(Box.createVerticalStrut(5));
		ContentPanel.add(onLabel);
		ContentPanel.add(Box.createVerticalStrut(5));
		ContentPanel.add(kunLabel);
    	
    	this.add(ContentPanel);
	}
	
	private void setupLayout()
	{
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setPreferredSize(new Dimension(600, 300));
	}
	
	public void updateDisplay(KanjiInfo info)
	{
		if (info == null)
		{
			 kanjiLabel.setText("");
		     meaningLabel.setText("No kanji found. Try again");
		     onLabel.setText("");
		     kunLabel.setText("");
		}
		
		kanjiLabel.setText(info.getKanji());
	    meaningLabel.setText("Meaning: " + info.getMeanings());
	    onLabel.setText("On'yomi: " + String.join(", ", info.getOnReadings()));
	    kunLabel.setText("Kun'yomi: " + String.join(", ", info.getKunReadings()));
	}
}