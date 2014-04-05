import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PianoPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3875360370099517808L;
	
	final int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(); //size of panel
	final int keylength = 50; //sizeofkeys
	final int offset = width / 30; //offset for formatting
	int widthWhite = width / 45;
	int widthBlack = widthWhite * 2 / 3;
	int space = widthWhite * 7;
	
	private Timer timer;
	private int beat = 0;
	
	public PianoPanel() {
		super();
		timer = new Timer(500, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 3 * width / 25));
		for(int i = 0; i < 4; i++) {
			if(i == beat)
				g2d.setColor(new Color(0x323232));
			else
				g2d.setColor(new Color(0x787878));
			g2d.drawString(String.valueOf(i + 1), i * width / 12 + width / 12, width / 12);
		}
	
		for (int i = 0; i < 45; i++) {
			drawWhite(i*widthWhite, offset, g);
		}

		//groups of 3
		 for (int i=(2*widthWhite-widthBlack/2);i<(width-3*widthWhite);i+=space){
		 drawBlack(i,offset,g);
		 drawBlack(i+widthWhite,offset,g);
		 drawBlack(i+2*widthWhite,offset,g);
		 }
		 
		 //groups of 2
		 for (int i=(6*widthWhite-widthBlack/2);i<width;i+=space){
			 drawBlack(i,offset,g);
			 drawBlack(i+widthWhite,offset,g);
		 }
		 
		 drawBlack(width-widthWhite*2-widthBlack/2,offset,g);

	}

	public void drawWhite(int x, int y, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, widthWhite, keylength);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, widthWhite, keylength);
	}

	public void drawBlack(int x, int y, Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, widthBlack, keylength*3/5);

	}
	
	public void actionPerformed(ActionEvent e) {
		beat = (beat + 1) % 4;
		timer.setDelay((int) ((1.0/(Game.gamedata.get_bpm()/60.0))*1000.0));
		repaint();
	}
}