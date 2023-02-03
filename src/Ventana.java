

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//Marco con l�mina y botones------------------------------------------------------------------------------

class Ventana extends JFrame{
	private ArrayList<PelotaThread> hilos;
	private PanelRebote zonaRebote;
	JButton btnNuevaPelota, btnSalir, btnParar, btnBorrar;
	JLabel lblContPelotas;
	private int contPelotas;
	
	public Ventana(){
		hilos = new ArrayList<PelotaThread>();
		this.contPelotas = 0;
		
		setBounds(700,300,400,350);
		
		setTitle ("Juego pelotas");
		
		zonaRebote=new PanelRebote();
		
		add(zonaRebote, BorderLayout.CENTER);
		
		JPanel zonaBotones=new JPanel();
		
		btnNuevaPelota = new JButton("Nueva pelota");
		zonaBotones.add(btnNuevaPelota);
		btnNuevaPelota.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				if (zonaRebote.numPelotas() <= 7) {
					PelotaThread hilo = new PelotaThread(zonaRebote, hilos);
					hilos.add(hilo);
					lblContPelotas.setText((Integer.parseInt(lblContPelotas.getText()) + 1) + "");
					hilo.start();
				}
			}
			
		});
		
		btnSalir = new JButton("Salir");
		zonaBotones.add(btnSalir);
		btnSalir.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				
				System.exit(0);
				
			}
			
		});
		
		btnParar = new JButton("Parar");
		zonaBotones.add(btnParar);
		btnParar.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				
				if (btnParar.getText().equals("Parar")) {
					for (PelotaThread pelotaThread : hilos) {
						pelotaThread.setDetenidoTrue();
					}	
					btnParar.setText("Continuar");
				}
				else {
					for (PelotaThread pelotaThread : hilos) {
						pelotaThread.setDetenidoFalse();
					}	
					btnParar.setText("Parar");
				}
			}
			
		});
		
		btnBorrar = new JButton("Borrar");
		zonaBotones.add(btnBorrar);
		btnBorrar.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				if (hilos.size() > 0) {
					hilos.get(0).setDetenidoTrue();
					hilos.get(0).eliminarPelotaHilo();
				}
			}
			
		});
		
		lblContPelotas = new JLabel("0");
		zonaBotones.add(lblContPelotas);
		
		add(zonaBotones, BorderLayout.SOUTH);
	}
	
	
	
	
}
