package br.com.valdelar.leilao.servico;

import br.com.valdelar.leilao.dominio.Lance;
import br.com.valdelar.leilao.dominio.Leilao;

import java.util.OptionalDouble;

public class Avaliador {
    private double maior = Double.NEGATIVE_INFINITY;
    private double menor = Double.POSITIVE_INFINITY;
    private OptionalDouble media;

    public void avalia(Leilao leilao) {
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maior)
                maior = lance.getValor();
            if (lance.getValor() < menor)
                menor = lance.getValor();
        }
    }

    public void media(Leilao leilao) {
        media = OptionalDouble.of(leilao.getLances().stream().mapToDouble(p -> p.getValor()).average().orElse(0.0));
    }

    public double getMaiorLance() {
        return maior;
    }

    public double getMenorLance() {
        return menor;
    }

    public Double getMedia() {
        return media.getAsDouble();
    }
}
