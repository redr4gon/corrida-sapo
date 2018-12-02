package corrida;

import entidade.Sapo;

import java.util.HashMap;
import java.util.Map;

public class CorridaControle {

    private boolean finalizada;

    private Map<Integer, Sapo> saposChegada;

    private static CorridaControle corridaControle;

    private static int posicao;

    private int qtdeSapos;

    private CorridaControle() {
        finalizada = false;
        posicao = 1;
    }

    public static CorridaControle getInstance() {

        if (corridaControle == null) {
            corridaControle = new CorridaControle();
        }

        return corridaControle;
    }

    public void sapoChegou(Sapo sapo) {

        if (saposChegada == null) {
            saposChegada = new HashMap<>();
            posicao = 1;
        }

        saposChegada.put(posicao++, sapo);

        if (saposChegada.size() == qtdeSapos) {
            finalizada = true;
        }
    }

    public void setQtdeSapos(int qtdeSapos) {
        this.qtdeSapos = qtdeSapos;
    }

    public int getQtdeSapos() {
        return qtdeSapos;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Map<Integer, Sapo> getSaposChegada() {
        return saposChegada;
    }
}
