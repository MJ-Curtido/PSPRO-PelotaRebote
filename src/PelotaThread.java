import java.util.ArrayList;

public class PelotaThread extends Thread {
	private PanelRebote zonaRebote;
	private static final int NUM_MOVIMIENTOS = 1000;
	private Boolean detenido;
	private ArrayList<PelotaThread> hilos;
	
	public PelotaThread(PanelRebote zonaRebote, ArrayList<PelotaThread> hilos) {
		this.zonaRebote = zonaRebote;
		this.detenido = false;
		this.hilos = hilos;
	}
	
	public void setDetenidoTrue() {
        this.detenido = true;
    }

    public synchronized void setDetenidoFalse() {
        this.detenido = false;

        notifyAll();
    }

    public Boolean getDetenido() {
        return this.detenido;
    }

	@Override
	public void run() {
		lanzarJuego();
		
		eliminarPelotaHilo();
	}
	
	public void eliminarPelotaHilo()  {
		zonaRebote.eliminarPelota();
		hilos.remove(this);
	}
	
	public synchronized void lanzarJuego () {		
		Pelota pelota=new Pelota(zonaRebote);//pasarle zonarebote y que se pinte a s� misma
		
		zonaRebote.add(pelota);
		
		for (int i=1; i<=NUM_MOVIMIENTOS; i++){
			if (this.getDetenido()) {
	            try {
	                wait();
	            } catch (InterruptedException ex) {
	                
	            }
	        }
			
			pelota.paint();
			
			//zonaRebote.paint(zonaRebote.getGraphics());//borrar y que paint sea llamado desde update o update desde un paint de pelota
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
	
}
}