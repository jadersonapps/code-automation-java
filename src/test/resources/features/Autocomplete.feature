# language: pt
@AmazonAutocomplete
Funcionalidade: Autocomplete na barra de pesquisa

  Como um usuário na página inicial
  Deseja receber sugestões relevantes ao digitar na barra de pesquisa
  Para facilitar a localização de conteúdos de interesse

  Contexto:
    Dado que o navegador esta carregado na pagina inicial

  @TC01 @Autocomplete @Regressivo
  Cenário: TC01 - Validar sugestões com termo comum
    Quando o usuario digita "livro" na barra de pesquisa
    Então devem ser exibidas sugestoes relacionadas

  @TC02 @Autocomplete @Regressivo
  Cenário: TC02 - Validar ausência de sugestões com termo aleatório
    Quando o usuario digita "xzywqtpr" na barra de pesquisa
    Então nenhuma sugestao deve ser exibida

  @TC03 @Autocomplete @Regressivo
  Cenário: TC03 - Validar ausência de sugestões com campo vazio
    Quando o usuario clica na barra de pesquisa sem digitar
    Então nenhuma sugestao deve ser exibida

  @TC04 @Autocomplete @Regressivo
  Cenário: TC04 - Validar não exibição de sugestões com número aleatório longo
    Quando o usuario digita "12345678901234567890" na barra de pesquisa
    Então nenhuma sugestao deve ser exibida
