package kanji.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import kanji.controller.Controller;
import kanji.model.KanjiInfo;

/**
 * InputPanel is a GUI component that allows users to search for a kanji,
 * generate a random kanji, and export kanji information as a PDF.
 */
public class InputPanel extends JPanel
{
    private JTextField inputField;
    private JButton searchButton;
    private JButton downloadPDFButton;
    private JButton randomButton;
    private Controller app;
    private JPanel inputBar;
    private KanjiCharacterPanel charactersPanel;

    /**
     * Constructs the InputPanel with user input field, buttons, and layout setup.
     *
     * @param app the Controller that provides kanji data and functionality
     * @param charactersPanel the panel used to display selected kanji information
     */
    public InputPanel(Controller app, KanjiCharacterPanel charactersPanel)
    {
        super();
        this.app = app;
        this.charactersPanel = charactersPanel;

        this.inputBar = new JPanel();
        this.inputField = new JTextField(10);
        this.searchButton = new JButton("Search");
        this.randomButton = new JButton("Random Kanji");
        this.downloadPDFButton = new JButton("Download as PDF");

        setupLayout();
        setupPanel();
        setupListeners();
    }

    /**
     * Configures the layout and adds components to the panel.
     */
    private void setupPanel()
    {
        this.setPreferredSize(new Dimension(600, 50));

        inputBar.add(inputField);
        inputBar.add(searchButton);
        inputBar.add(downloadPDFButton);
        inputBar.add(randomButton);

        this.add(inputBar, BorderLayout.CENTER);
    }

    /**
     * Sets up all event listeners for button interactions:
     * - Search for a kanji and display its details.
     * - Export the displayed kanji to a PDF.
     * - Display a random kanji.
     */
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

                // Ensure filename ends with .pdf
                if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
                    fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".pdf");
                }

                try {
                    app.exportKanjiToPDF(info, fileToSave);
                    JOptionPane.showMessageDialog(this, "PDF saved successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to save PDF.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        randomButton.addActionListener(e -> {
            String randomKanji = app.getRandomKanji();
            KanjiInfo info = app.getKanjiInfo(randomKanji);
            app.getCharactersPanel().updateDisplay(info);
        });
    }

    /**
     * Sets up the panel layout and flow for positioning components.
     */
    private void setupLayout()
    {
        setLayout(new BorderLayout());
        inputBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(600, 60));
    }
}
