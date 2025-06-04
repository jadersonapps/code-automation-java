package br.com.amazonecommerce.step;

import br.com.amazonecommerce.config.DriverFactory;
import br.com.amazonecommerce.page.AutocompletePage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.*;

public class AutocompleteStep {

    @Dado("que o navegador esta carregado na pagina inicial")
    public void queONavegadorEstaCarregadoNaPaginaInicial() throws InterruptedException {
        DriverFactory.initializeDriver();
        AutocompletePage.navigateToHomePage();
    }

    @Quando("o usuario digita {string} na barra de pesquisa")
    public void oUsuarioDigitaNaBarraDePesquisa(String termo) {
        AutocompletePage.typeInSearchBar(termo);
    }

    @Quando("o usuario clica na barra de pesquisa sem digitar")
    public void oUsuarioClicaNaBarraDePesquisaSemDigitar() {
        AutocompletePage.clickSearchBar();
    }

    @Então("devem ser exibidas sugestoes relacionadas")
    public void devemSerExibidasSugestoesRelacionadas() throws InterruptedException {
        AutocompletePage.assertSuggestionsAreVisible();
    }

    @Então("nenhuma sugestao deve ser exibida")
    public void nenhumaSugestaoDeveSerExibida() throws InterruptedException {
        AutocompletePage.assertSuggestionsAreNotVisible();
    }
}