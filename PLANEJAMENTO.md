INTRODUÇÃO

PROBLEMA: Encontrar pessoas e fechar um time para jogar esportes coletivos pode ser difícil. Pense no vôlei, por exemplo, em que cada time conta com 6 jogadores.
No total, precisam-se de 12 pessoas para uma partida completa. E, para alguns, encontrar tudo isso de gente pode ser uma tarefa complicada. Durante a graduação,
por exemplo, eu e meus colegas vivíamos tentando fechar time para jogar futebol, mas quase sempre essa era a parte mais difícil.

PÚBLICO: Qualquer pessoa que gosta de esportes e quer encontrar alguém para jogar. Não há restrições, o mais importante é a vontade de querer jogar e conhecer 
pessoas novas.

ESPORTES: Qualquer esporte pode ser abrangido. O aplicativo inicialmente contará com os mais conhecidos: futebol, futsal, handebol, volei, basquete, tênis e badminton.

PERFIL: Para utilizar o aplicativo, é necessário criar um perfil. O perfil deve conter informações importantes para o público conhecer o usuário, como:
    1. Nome
    2. Data de nascimento
    3. Gênero - opção de não informar
    4. Localização (cidade, estado)
    5. Esportes que pratica (há quanto tempo, nível de habilidade)
    6. "Conhecendo o atleta" - bio em texto
    7. "Conquistas do campeão" - seção para postagens

BUSCA: Na funcionalidade de busca, podem ser aplicados os filtros de nome, idade, gênero, localização e esporte que pratica. Os filtros podem ser combinados para gerar
uma busca mais específica.

SEGUIR: Outra funcionalidade importante é a possibilidade de seguir outros usuários. Caso você siga um usuário, receberá atualização de seus posts na página inicial e
poderá adicioná-lo em grupos para organizar a jogatina. 

MOTIVO PARA VOLTAR: Os usuários gostarão de voltar ao aplicativo para registrar partidas, convidar pessoas para jogar, conversar nos grupos etc.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

REQUISITOS 

ÉPICO 01 - Perfil e busca:

    US001 - Cadastrar perfil: Como usuário, quero poder criar meu perfil para conseguir utilizar as funcionalidades do app.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu preenchi todos os campos obrigatórios corretamente, quando eu salvar, então aparece uma mensagem de confirmação e abre a página inicial.
        2. Dado que eu deixei de preencher ou preenchi incorretamente algum campo obrigatório, quando eu salvar, então aparece uma mensagem de erro e o campo                 faltante é informado.
        3. Dado que meu perfil foi criado com sucesso, então o Banco de Dados do sistema deve ser atualizado com as informações cadastrais novas.


    US002 - Atualizar perfil: Como usuário, quero poder atualizar meu perfil para alterar/remover/adicionar as informações inseridas no momento de sua criação.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu deixei todos os campos obrigatórios preenchidos corretamente, quando eu salvar, então aparece uma mensagem de confirmação e abre a página              de perfil.
        2. Dado que eu deixei algum campo obrigatório vazio ou preenchido incorretamente, quando eu salvar, então aparece uma mensagem de erro e o campo faltante             é informado.
        3. Dado que meu perfil foi atualizado com sucesso, então o Banco de Dados do sistema deve ser atualizado com as informações novas.


    US003 - Excluir perfil: Como usuário, quero poder excluir permanentemente meu perfil para finalizar minha conta no aplicativo.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu selecionei a opção de excluir perfil, então aparece uma mensagem de confirmação informando que a exlusão deletará permanentemente todos os             dados do perfil.
        2. Dado que eu confirmei a exclusão, então o meu perfil é removido do Banco de Dados do sistema e abre a tela de início do aplicativo.


    US004 - Buscar perfil: Como usuário, quero poder buscar perfis de outros usuários com filtros combináveis para expandir minha rede de contatos.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que estou escolhendo os filtros, então devem aparecer as opções de nome, idade, gênero, localização e esportes praticados.
        2. Dado que eu selecionei os filtros a serem usados e os preenchi corretamente, quando eu buscar, então deve aparecer uma lista com todos os perfis que se         enquadram nos meus critérios de busca.
        3. Dado que nenhum perfil foi encontrado, então deve aparecer a mensagem "Nenhum perfil encontrado".


    US005 - Ver perfil: Como usuário, quero poder ver perfis de outros usuários para conhecê-los melhor.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu selecionei o perfil de um usuário, então deve abrir a tela de seu perfil, contendo suas informações.

ÉPICO 02 - Conexões:

    US006 - Seguir perfil: Como usuário, quero poder seguir perfis de outros usuários para conseguir ver as partidas que eles estão criando.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu comecei a seguir um perfil, quando seleciono a opção seguir, então meu perfil deve ser adicionado em seus seguidores e seu perfil deve ser             adicionado em meus seguindo.


    US007 - Deixar de seguir: Como usuário, quero poder deixar de seguir perfis de outros usuários para encerrar meu contato com eles.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que selecionei a opção deixar de seguir, então deve aparecer uma mensagem de confirmação.
        2. Dado que eu deixei de seguir um perfil, quando confirmo a ação, então ele deve ser removido de meus seguindo e eu devo ser removido de seus seguidores.
        3. Dado que a ação foi realizada, então a relação estabelecida deve ser finalizada no sistema.


    US008 - Excluir seguidor: Como usuário, quero poder excluir meus seguidores para encerrar conexões com outros perfis.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que selecionei a opção remover seguidor, então deve aparecer uma mensagem de confirmação.
        2. Dado que eu removi o seguidor, quando confirmo a ação, então ele é removido de meus seguidores e eu sou removido de seus seguindo.
        3. Dado que a remoção foi realizada, então a relação estabelecida deve ser finalizada no sistema.

    
    US009 - Ver seguidores e seguindo: Como usuário, quero poder visualizar os seguidores e seguindo do meu perfil e dos outros perfis para conhecer 
    mais sobre eles e encontrar possíveis conexões em comum.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu acessei meus seguidores, quando pressiono seguidores em meu perfil, então deve aparecer a lista de todos os perfis que me seguem.
        2. Dado que eu acessei meus seguindo, quando pressiono seguindo em meu perfil, então deve aparecer a lista e todos os perfis que estou seguindo.
        3. Dado que acessei os seguidores de um perfil, quando pressiono seguidores em seu perfil, então deve aparecer uma lista de todos os perfis que o seguem.
        4. Dado que acessei os seguindo de um perfil, quando pressiono seguindo em seu perfil, então deve aparecer uma lista de todos os perfis que ele segue.

ÉPICO 03 - Partidas:

    US010 - Criar partida: Como usuário, quero poder criar partidas de esportes para formar times para jogar.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu preenchi todos os campos necessários, quando crio a partida, então ela deve aparecer na página inicial de todos os perfis que estão me                 seguindo.
        

    US011 - Cancelar partida: Como usuário criador de partida, quero poder cancelar a partida que criei para caso ela não faça mais sentido.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu selecionei a opção cancelar partida, quando pressiono cancelar, então deve aparecer uma mensagem de confirmação.
        2. Dado que eu cancelei a partida, quando confirmo o cancelamento, então a partida deve ser excluída da lista de partidas inscritas dos usuários que                  tinham entrado nela.


    US012 - Ver partidas: Como usuário, quero poder ver partidas dos perfis que sigo na página inicial para decidir em qual entrar.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que estou na minha tela inicial, todas as partidas criadas pelos perfis que eu sigo devem aparecer para mim.
        

    US013 - Entrar em partida: Como usuário, quero poder entrar em partidas criadas para jogar. 

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu selecionei a opção entrar em partida, quando pressiono entrar, então deve aparecer uma mensagem de confirmação, reforçando as informações              principais da partida (local, data e horário).
        2. Dado que eu entrei na partida, quando confirmo minha entrada, então eu devo ser adicionado na lista de participantes e a partida deve aparecer em minha            tela de partidas inscritas.


    US014 - Lista de partidas: Como usuário, quero poder visualizar uma lista com todas as partidas que estou inscrito.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu selecionei a página partidas inscritas, quando pressiono seu ícone na barra de navegação, então a lista de partidas inscritas deve ser                 mostrada.

    US015 - Sair de partida: Como usuário, quero poder sair de partidas que eu entrei para caso eu não possa mais ir jogar.

        CRITÉRIOS DE ACEITAÇÃO:
        1. Dado que eu selecionei a opção sair de partida, quando pressiono sair, então deve aparecer uma mensagem de confirmação.
        2. Dado que eu sai da partida, quando confirmo minha saída, então eu devo ser removido da lista de participantes e a partida deve ser removida da minha               tela de partidas inscritas.
