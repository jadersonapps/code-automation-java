package br.com.codegroup.step.amazon;

import br.com.codegroup.config.DriverFactory;
import br.com.codegroup.page.HomePage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AutocompleteStep {

    @Dado("que o navegador esta carregado na pagina inicial")
    public void queONavegadorEstaCarregadoNaPaginaInicial() {
        DriverFactory.initializeDriver();
        HomePage.navigateToHomePage();
    }

    @Quando("o usuario digita {string} na barra de pesquisa")
    public void oUsuarioDigitaNaBarraDePesquisa(String termo) {
        HomePage.typeInSearchBar(termo);
    }

    @Quando("o usuario clica na barra de pesquisa sem digitar")
    public void oUsuarioClicaNaBarraDePesquisaSemDigitar() {
        HomePage.clickSearchBar();
    }

    @Então("devem ser exibidas sugestoes relacionadas")
    public void devemSerExibidasSugestoesRelacionadas() throws InterruptedException {
        HomePage.assertSuggestionsAreVisible();
    }

    @Então("nenhuma sugestao deve ser exibida")
    public void nenhumaSugestaoDeveSerExibida() throws InterruptedException {
        HomePage.assertSuggestionsAreNotVisible();
    }
}
