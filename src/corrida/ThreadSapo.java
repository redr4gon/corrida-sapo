package corrida;

import entidade.Sapo;

import java.util.concurrent.Semaphore;

public class ThreadSapo implements Runnable {

    private Thread thread;

    private Sapo sapo;

    private Semaphore semaphore;

    private static final int MAXIMO_DESCANSO = 500;
    private static final int MAXIMO_PULO = 50;

    private int distanciaTotal;
    private int distanciaPercorrida;
    private int ultimoPulo;

    private CorridaControle corridaControle;

    public ThreadSapo(Sapo sapo, Integer distanciaTotal) {
        this.sapo = sapo;
        this.distanciaTotal = distanciaTotal;
        this.corridaControle = CorridaControle.getInstance();

        thread = new Thread(this, sapo.getNome());

        semaphore = new Semaphore(1);

        thread.start();
    }

    private void pular() {

        ultimoPulo = (int) (Math.random() * MAXIMO_PULO);
        distanciaPercorrida += ultimoPulo;

        if(distanciaPercorrida > distanciaTotal) {
            distanciaPercorrida = distanciaTotal;
        }
    }

    private void avisarSituacao() {
        System.out.println(sapo.getNome() + " pulou " + ultimoPulo + " cm. A distância percorrida foi de "
                + distanciaPercorrida + "cm!");
    }

    private void cruzarLinhaDeChegada() {
        try {
            semaphore.acquire();
            corridaControle.sapoChegou(sapo);
            semaphore.release();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void descansar() {
        int tempo = (int) (Math.random() * MAXIMO_DESCANSO);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (distanciaPercorrida < distanciaTotal) {
            pular();
            avisarSituacao();
            descansar();
        }
        cruzarLinhaDeChegada();
    }
}
