package kanji.view;

import javax.swing.JFrame;
import kanji.controller.Controller;

/**
 * KanjiFrame is the main application window that holds the KanjiPanel UI.
 * It sets up the JFrame properties and initializes the interface.
 */
public class KanjiFrame extends JFrame
{
    /**
     * Constructs a KanjiFrame with the specified controller.
     *
     * @param app the Controller that manages application logic and data retrieval
     */
    public KanjiFrame(Controller app)
    {
        super();

        setContentPane(new KanjiPanel(app));

        setTitle("Kanji Dictionary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);
        setVisible(true);
    }

    /**
     * Returns the KanjiCharacterPanel used to display kanji information.
     *
     * @return the KanjiCharacterPanel from the KanjiPanel
     */
    public KanjiCharacterPanel getCharactersPanel() 
    {
        return ((KanjiPanel) getContentPane()).getCharactersPanel();
    }
}
