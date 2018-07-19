package br.com.valdelar.leilao.servico;

import br.com.valdelar.leilao.dominio.Lance;
import br.com.valdelar.leilao.dominio.Leilao;
import br.com.valdelar.leilao.dominio.Usuario;
import br.com.valdelar.leilao.servico.Avaliador;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AvaliadorTest {

    @Test
    public void verificarLancesEmOrdemCrescente() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("Jose");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Casa");
        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void verificaMediaDosLances(){
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("Jose");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Casa");
        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.media(leilao);

        Double mediaEsperada = 300.0;

        assertEquals(mediaEsperada, leiloeiro.getMedia());
    }

    @Test
    public void verificaMediaDosLancesZero(){
        Usuario joao = new Usuario("Joao");

        Leilao leilao = new Leilao("Casa");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.media(leilao);

        Double mediaEsperada = 0.0;

        assertEquals(mediaEsperada, leiloeiro.getMedia());
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEncontrarOsMaioresLancesEmListaMenorQueTres() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getMaiores();

        assertEquals(2, maiores.size());
    }
}
