package br.com.valdelar.leilao.servico;

import br.com.valdelar.leilao.dominio.Lance;
import br.com.valdelar.leilao.dominio.Leilao;

public class Avaliador {
    private double maior = Double.NEGATIVE_INFINITY;
    private double menor = Double.POSITIVE_INFINITY;

    public void avalia(Leilao leilao) {
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maior)
                maior = lance.getValor();
            if (lance.getValor() < menor)
                menor = lance.getValor();
        }
    }

    public double getMaiorLance() {
        return maior;
    }

    public double getMenorLance() {
        return menor;
    }
}
