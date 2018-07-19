package br.com.valdelar.leilao.servico;

import br.com.valdelar.leilao.dominio.Lance;
import br.com.valdelar.leilao.dominio.Leilao;

import java.util.*;

public class Avaliador {
    private double maior = Double.NEGATIVE_INFINITY;
    private double menor = Double.POSITIVE_INFINITY;
    private Optional<Double> media;
    private List<Lance> maiores;

    public void avalia(Leilao leilao) {
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maior)
                maior = lance.getValor();
            if (lance.getValor() < menor)
                menor = lance.getValor();
        }

        pegaOsMaiores(leilao);
    }

    public void media(Leilao leilao) {
        media = Optional.of(leilao.getLances().stream().mapToDouble(Lance::getValor).average().orElse(0.0));
    }

    private void pegaOsMaiores(Leilao leilao) {
        maiores = new ArrayList<>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            @Override
            public int compare(Lance o1, Lance o2) {
                if (o1.getValor() < o2.getValor()) return 1;
                if (o1.getValor() > o2.getValor()) return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

    public List<Lance> getMaiores() {
        return this.maiores;
    }

    public double getMaiorLance() {
        return maior;
    }

    public double getMenorLance() {
        return menor;
    }

    public Double getMedia() {
        return media.get();
    }
}
