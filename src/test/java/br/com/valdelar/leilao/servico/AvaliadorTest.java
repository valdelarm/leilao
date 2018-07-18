package br.com.valdelar.leilao.servico;

import br.com.valdelar.leilao.dominio.Lance;
import br.com.valdelar.leilao.dominio.Leilao;
import br.com.valdelar.leilao.dominio.Usuario;
import br.com.valdelar.leilao.servico.Avaliador;
import org.junit.Assert;
import org.junit.Test;

public class AvaliadorTest {

    @Test
    public void verificarLancesEmOrdemCrescente() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Casa");
        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
    }
}
