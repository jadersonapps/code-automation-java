# language: pt
@Funcionalidade:DesempenhoCarregaemnto
@regressivo
Funcionalidade: Testes de desempenho do carregamento do site do ecommerce

  Contexto:
    Dado que o navegador esta carregado na pagina inicial

  @ID:DS-001 @Desempenho @regressivo
  Cenário: Página principal carrega em tempo aceitável
    Quando o usuário acessa a página principal
    Então o tempo de carregamento deve ser menor ou igual a 3 segundos

  @ID:DS-002 @Desempenho @regressivo
  Cenário: Tela de produtos carrega em tempo aceitável
    Quando o usuário navega até a tela de produtos
    Então o tempo de carregamento deve ser menor ou igual a 5 segundos

  @ID:DS-003 @Desempenho @regressivo
  Cenário: Pesquisa retorna resultados rapidamente
    Quando o usuário digita um termo na pesquisa
    Então os resultados devem aparecer em até 2 segundos

  @ID:DS-004 @Desempenho @regressivo
  Cenário: Uso de recursos no carregamento da página principal
    Quando o usuário acessa a página principal
    Então o uso de CPU deve ser menor que 60% e memória menor que 500MB durante o carregamento
