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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import kanji.controller.Controller;
import kanji.model.KanjiInfo;
import kanji.model.KanjiParser;

/**
 * KanjiCharacterPanel is responsible for displaying detailed kanji information,
 * including the character, meanings, and readings, in a structured layout.
 */
public class KanjiCharacterPanel extends JPanel
{
	private JLabel kanjiLabel;
	private JScrollPane scrollPane;
    private JTextArea textArea;
    
    private JPanel ContentPanel;
    private KanjiInfoPanel infoPanel;
    
    private KanjiInfo currentInfo;
	
	KanjiParser parser = new KanjiParser();
	
	/**
	 * Constructs a KanjiCharacterPanel used to display kanji details.
	 *
	 * @param app the main controller instance (unused in this class but can be extended)
	 */
	public KanjiCharacterPanel(Controller app)
	{
		super();
		
		Font kanjiFont = new Font("Serif", Font.PLAIN, 48); 
		Font textFont = new Font("Serif", Font.PLAIN, 16);
		
		this.ContentPanel = new JPanel();
		this.infoPanel = new KanjiInfoPanel(textFont);
		
		this.kanjiLabel = new JLabel("");
    	this.textArea = new JTextArea("");
    	this.scrollPane = new JScrollPane(textArea);
		
		kanjiLabel.setFont(kanjiFont);
		
		setupPanel();
		setupLayout();
	}

	/**
	 * Initializes and configures the components used for displaying kanji information.
	 */
	private void setupPanel()
	{
		infoPanel.updateText("Enter a kanji in the search bar, or click \"Random Kanji Button\" to begin.");
		
		ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.Y_AXIS));
		ContentPanel.setOpaque(false);
		
		kanjiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ContentPanel.add(kanjiLabel);
		ContentPanel.add(Box.createVerticalStrut(10));
		ContentPanel.add(Box.createVerticalStrut(5));
		ContentPanel.add(Box.createVerticalStrut(5));
    	
    	this.add(ContentPanel);
    	
    	textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setBackground(null);
        textArea.setFocusable(false);
        
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
	}

	/**
	 * Sets up the overall layout and spacing of the panel.
	 */
	private void setupLayout()
	{
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setPreferredSize(new Dimension(600, 300));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalStrut(10));
		this.add(kanjiLabel);
		this.add(Box.createVerticalStrut(20));
		this.add(infoPanel);
	}

	/**
	 * Updates the kanji character and its associated details on the panel.
	 *
	 * @param info the KanjiInfo object containing kanji data to display
	 */
	public void updateDisplay(KanjiInfo info) {
	    if (info == null) {
	        kanjiLabel.setText("No kanji found.");
	        infoPanel.updateText("Enter a kanji above or click \"Random Kanji Button\" to begin.");
	        return;
	    }

	    kanjiLabel.setText(info.getKanji());

	    StringBuilder text = new StringBuilder();
	    text.append("Meanings: ").append(String.join(", ", info.getMeanings())).append("\n\n");
	    text.append("On'yomi: ").append(String.join(", ", info.getOnReadings())).append("\n\n");
	    text.append("Kun'yomi: ").append(String.join(", ", info.getKunReadings())).append("\n");

	    infoPanel.updateText(text.toString());
	}

	/**
	 * Gets the currently displayed KanjiInfo.
	 *
	 * @return the current KanjiInfo object
	 */
	public KanjiInfo getCurrentKanjiInfo() 
	{
	    return currentInfo;
	}
}
