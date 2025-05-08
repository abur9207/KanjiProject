package kanji.view;

import java.awt.BorderLayout;
import java.awt.Font;

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
    private JLabel meaningLabel;
    
    public InputPanel(Controller app)
    {
    	super();
    	
    	this.inputField = new JTextField(5);
    	this.searchButton = new JButton("Search");
    	this.displayedCharacter = new JLabel("Kanji will appear here", SwingConstants.CENTER);
    	this.meaningLabel = new JLabel("", SwingConstants.CENTER);

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
    	this.add(displayedCharacter, BorderLayout.CENTER);
    	this.add(meaningLabel, BorderLayout.SOUTH);
	}
	
	private void setupListeners()
	{
		searchButton.addActionListener(e -> {
		    String userInput = inputField.getText().trim(); // Get value from input field
		    Controller controller = new Controller();       // Create a controller
		    String json = controller.JsonApiReader(userInput); // Pass the input to the method
		});
	}
	
	private void setupLayout()
	{
		
	}
	
	private void fetchKanjiInfo() {
        String userInput = inputField.getText().trim();
        if (userInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Kanji character.");
            return;
        }

        try {
            Controller controller = new Controller();
            String json = controller.JsonApiReader(userInput);

            KanjiParser parser = new KanjiParser();
            KanjiInfo info = parser.parseKanjiJson(json);

            displayedCharacter.setText(info.getKanji());
            meaningLabel.setText("Meaning: " + String.join(", ", info.getMeanings()));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching kanji: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
