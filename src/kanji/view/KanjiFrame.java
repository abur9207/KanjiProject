package kanji.view;

import javax.swing.JFrame;

import kanji.controller.Controller;

public class KanjiFrame extends JFrame
{
	private Controller app;
	private KanjiPanel panel;
	
	
	public KanjiFrame(Controller app)
	{
		super();
		
		this.app = app;
		this.panel = new KanjiPanel(this.app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		setContentPane(panel);
		setTitle("Kanji Project");
		setSize(1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
