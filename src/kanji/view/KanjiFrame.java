package kanji.view;

import javax.swing.JFrame;

import kanji.controller.Controller;

public class KanjiFrame extends JFrame
{
	public KanjiFrame(Controller app)
	{
		super();

		setContentPane(new KanjiPanel(app));
		
		setTitle("Kanji Dictionary");
		//setSize(1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	public KanjiCharacterPanel getCharactersPanel() 
	{
	    return ((KanjiPanel) getContentPane()).getCharactersPanel();
	}
}
