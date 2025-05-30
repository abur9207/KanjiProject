package kanji.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import kanji.controller.Controller;

/**
 * KanjiPanel is the main view panel of the application.
 * It combines the input area (for user interaction) and the display area
 * (for showing kanji details) using a BorderLayout.
 */
public class KanjiPanel extends JPanel
{
    /** Reference to the main controller of the application. */
    private Controller app;

    /** Panel for user input such as kanji search and PDF export. */
    private InputPanel inputPanel;

    /** Panel that displays kanji characters and their associated info. */
    private KanjiCharacterPanel charactersPanel;

    /**
     * Constructs the main KanjiPanel with input and character sub-panels.
     *
     * @param app the application controller used to coordinate data and behavior
     */
    public KanjiPanel(Controller app)
    {
        super();
        this.app = app;

        this.setLayout(new BorderLayout());
        this.charactersPanel = new KanjiCharacterPanel(app);
        this.inputPanel = new InputPanel(app, charactersPanel);

        setupPanel();
    }

    /**
     * Configures the layout and appearance of the panel,
     * adding input and character panels to the appropriate layout regions.
     */
    private void setupPanel()
    {
        setBackground(Color.LIGHT_GRAY);

        this.add(inputPanel, BorderLayout.SOUTH);
        this.add(charactersPanel, BorderLayout.CENTER);
    }

    /**
     * Returns the KanjiCharacterPanel used to display kanji data.
     *
     * @return the KanjiCharacterPanel instance
     */
    public KanjiCharacterPanel getCharactersPanel() 
    {
        return charactersPanel;
    }
}
