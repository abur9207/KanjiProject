package kanji.view;

import javax.swing.*;
import java.awt.*;

/**
 * KanjiInfoPanel is a custom JPanel used to display detailed information
 * about a selected kanji character, such as meanings and readings.
 * It uses a JTextArea within a JScrollPane for formatted, scrollable text.
 */
public class KanjiInfoPanel extends JPanel 
{
    /** Text area for displaying kanji information. */
    private JTextArea infoArea;

    /**
     * Constructs a KanjiInfoPanel with the specified font for displaying Japanese text.
     *
     * @param textFont the Font to apply to the text area (should support Japanese characters)
     */
    public KanjiInfoPanel(Font textFont) 
    {
        infoArea = new JTextArea();
        infoArea.setLineWrap(true);                      // Enable line wrapping
        infoArea.setWrapStyleWord(true);                 // Wrap on word boundaries
        infoArea.setFont(textFont);                      // Set font for Japanese support
        infoArea.setEditable(false);                     // Make text area read-only
        infoArea.setOpaque(false);                       // Transparent background
        infoArea.setFocusable(false);                    // No focus highlight
        infoArea.setMargin(new Insets(10, 10, 10, 10));  // Padding around the text

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 200));

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);                      // No border around scroll pane

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Updates the text content displayed in the panel.
     *
     * @param text the kanji information to display (e.g., meanings and readings)
     */
    public void updateText(String text) 
    {
        infoArea.setText(text);
    }
}
