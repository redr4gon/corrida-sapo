package principal;

import corrida.CorridaSapo;

public class Principal {

    public static final int QNTD_SAPOS = 5;
    public static final int DISTANCIA_TOTAL = 200;

    public static void main(String[] args) {
        CorridaSapo corridaSapo = new CorridaSapo(QNTD_SAPOS, DISTANCIA_TOTAL);
        corridaSapo.preparar();
        corridaSapo.iniciar();
        corridaSapo.exibirResultado();
    }
}
