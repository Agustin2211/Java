package juego;

import java.awt.*;
import javax.swing.JFrame;

public class App extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private static JFrame ventana;
    private static Thread thread;

    private static final int ANCHO = 1024;
    private static final int LARGO = 768;

    private static volatile boolean enFuncionamiento = false;

    private static final String NOMBRE = "VentanaPrincipal";

    private static int APS = 0;
    private static int FPS = 0;

    /*CONSTRUCTOR*/
    private App(){
        setPreferredSize(new Dimension(ANCHO, LARGO));

        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    /*HILOS DE EJECUCION*/
    private synchronized void iniciar(){
        enFuncionamiento = true;

        thread = new Thread(this, "Graficos");
        thread.start();
    }

    private synchronized void detener(){
        enFuncionamiento = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void actualizar(){
        APS++;
    }

    private void mostrar(){
        FPS++;
    }

    /*HILO DE EJECUCION PRINCIPAL*/
    @Override
    public void run() {
        final int NS_POR_SEGUNDO = 1000000000;
        final byte FPS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = (NS_POR_SEGUNDO / FPS_OBJETIVO);

        long referenciaActualizacion = System.nanoTime();
    
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;

        while(enFuncionamiento){
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta = delta + (tiempoTranscurrido / NS_POR_ACTUALIZACION);

            while(delta >= 1){
                actualizar();
                delta--;
            }

            mostrar();

            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle("FOR THE KING " + " || APS: " + APS + " || FPS: " + FPS);
                APS = 0;
                FPS = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

    public static void main(String[] args) {
        App juego = new App();
        juego.iniciar();
    }
}
