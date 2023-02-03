

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
//L�mina que dibuja las pelotas----------------------------------------------------------------------

class PanelRebote extends JPanel{
	
	
	private ArrayList<Pelota> pelotas=new ArrayList<Pelota>();
	
	
	public void add(Pelota b){
		
		pelotas.add(b);
	}
	
	public void eliminarPelota() {
		pelotas.remove(0);
		updateUI();
	}
	
	public int numPelotas() {
		return pelotas.size();
	}

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		for (int i = 0; i < pelotas.size(); i++) {
			g2.fill(pelotas.get(i).getForma());
		}
		
	}
	
	
}
