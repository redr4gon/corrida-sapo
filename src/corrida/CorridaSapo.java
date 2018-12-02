package corrida;

import entidade.Sapo;

import java.util.ArrayList;
import java.util.List;

public class CorridaSapo {

    private int distanciaTotal;

    private CorridaControle corridaControle;

    private List<Sapo> sapoList;

    private int quantidadeSapos;

    public CorridaSapo(int quantidadeSapos, int distanciaTotal) {

        this.distanciaTotal = distanciaTotal;
        this.quantidadeSapos = quantidadeSapos;

        this.corridaControle = CorridaControle.getInstance();

        corridaControle.setQtdeSapos(quantidadeSapos);
    }

    public void preparar() {

        sapoList = new ArrayList<>();

        System.out.println("A corrida vai começar. São " + quantidadeSapos + " sapos correndo um percurso de " + distanciaTotal + " cm. ");

        for (int i = 0; i < corridaControle.getQtdeSapos(); i++) {
            sapoList.add(new Sapo("Sapo_" + (i+1)));
            System.out.println("Sapo " + (i+1) + " preparado para corrida!");
        }
    }

    public void iniciar() {

        if (sapoList == null) {
            System.out.println("Os sapos devem ser preparados antes da corrida iniciar!");
        }

        for (Sapo sapo : sapoList ) {
            new Thread(new ThreadSapo(sapo, distanciaTotal));
        }

        while(!corridaControle.isFinalizada()) {

            // pequeno intervalo para o while
            try {
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void exibirResultado() {

        if (!corridaControle.isFinalizada()) {
            System.out.println("A corrida não foi finalizada. O resultado não pode ser exibido!");
        }

        corridaControle.getSaposChegada().forEach((k, v) -> System.out.println(new String(k  + "º lugar - " + ((Sapo)v).getNome())));
    }
}
