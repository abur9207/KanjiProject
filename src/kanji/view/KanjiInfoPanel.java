package kanji.view;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class KanjiInfoPanel extends JPanel 
{
    private JTextArea infoArea;

    public KanjiInfoPanel(Font textFont) 
    {
        infoArea = new JTextArea();
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(textFont); // Supports Japanese
        infoArea.setEditable(false);
        infoArea.setOpaque(false);
        infoArea.setFocusable(false);
        infoArea.setMargin(new Insets(10, 10, 10, 10));
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 200));

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateText(String text) 
    {
        infoArea.setText(text);
    }
}
