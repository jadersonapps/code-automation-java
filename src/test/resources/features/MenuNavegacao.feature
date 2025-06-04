# language: pt
@Funcionalidade:MenuNavegacao
@regressivo
Funcionalidade: Navegação via menu

  Contexto:
    Dado que o navegador esta carregado na pagina inicial

  @ID:MN-001 @regressivo
  Cenário: Usuário clica no menu "Produtos" e acessa a tela correta
    Quando o usuário clica no menu "Produtos"
    Então a tela "Produtos" deve ser exibida com o título "Lista de Produtos"

  @ID:MN-002 @regressivo
  Cenário: Usuário acessa o submenu "Cadastrar Produto"
    Quando o usuário passa o mouse sobre o menu "Produtos"
    E clica no submenu "Cadastrar Produto"
    Então a tela "Cadastro de Produto" deve ser exibida com o botão "Salvar"

  @ID:CT-MN-003 @regressivo
  Cenário: Menu exibe estado ativo para a seção atual
    Quando o usuário clica no menu "Relatórios"
    Então o item "Relatórios" deve estar destacado como ativo

  @ID:CT-MN-004 @regressivo
  Cenário: Menu oculta submenus ao clicar fora
    Dado que o submenu "Cadastrar Produto" está visível
    Quando o usuário clica fora do menu
    Então o submenu deve ser ocultado
