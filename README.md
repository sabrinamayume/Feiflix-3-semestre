## PLATAFORMA DE FILMES DO STUDIO GHIBLI
Aluna: Sabrina Mayume Silva Uezu

Curso: Ciências da Computação – 3° Semestre

FEIFLIX é um sistema desktop desenvolvido em Java com interface gráfica em Swing/JFrame, utilizando JDBC para conexão com banco de dados PostgreSQL. O projeto segue a arquitetura MVC, separando as responsabilidades entre Model, View, Controller e DAO.

O sistema permite cadastro e login de usuários, busca de animes/vídeos, avaliação com curtidas e descurtidas, gerenciamento de favoritos e visualização das avaliações feitas pelo usuário.
## TECNOLOGIAS UTILIZADAS
	- Java
	- Java Swing / JFrame
	- JDBC
	- PostgreSQL
	- pgAdmin
	- Maven
	- NetBeans IDE
	- dotenv-java
## FUNCIONALIDADES

Usuário

	- Cadastro de novo usuário
	- Login de usuário
	- Validação de email único
	- Controle de usuário logado durante a navegação

Animes

	- Buscar anime por nome
	- Listar informações dos animes encontrados
	- Exibir dados como:
		- ID
		- Título
		- Descrição
		- Duração
		- Gênero

Avaliações

	- Curtir anime
	- Descurtir anime
	- Atualizar avaliação caso o usuário mude de curtido para descurtido, ou o contrário
	- Visualizar animes curtidos
	- Visualizar animes descurtidos

Favoritos

	- Criar lista de favoritos do usuário
	- Adicionar animes aos favoritos
	- Remover animes dos favoritos
	- Excluir lista de favoritos
	- Listar animes favoritos do usuário
## ARQUITETURA DO PROJETO
O projeto utiliza o padrão MVC com uma camada DAO para acesso ao banco de dados.

	br.edu.fei.feiflix
	    Feiflix.java
	
	br.edu.fei.model
	    Usuarios.java
	    Animes.java
	
	br.edu.fei.model.dao
	    Conexao.java
	    UsuariosDAO.java
	    AnimesDAO.java
	    AvaliacoesDAO.java
	    FavoritosDAO.java
	
	br.edu.fei.controller
	    LoginController.java
	    CadastroController.java
	    MenuController.java
	    BuscarAnimeController.java
	    FavoritosController.java
	    AvaliacoesController.java
	
	br.edu.fei.view
	    JFrameLogin.java
	    JFrameCadastro.java
	    JFrameMenu.java
	    JFrameBuscarAnime.java
	    JFrameFavoritos.java
	    JFrameAvaliacoes.java
## ELEMENTOS JFRAME

Avaliações:

	private void btnBuscarActionPerformed \\busca o tipo de avaliação, se é curtidos ou descurtidos
	private void btnVoltarActionPerformed \\ volta para o menu
Buscar animes:

    private void btnBuscarActionPerformed \\busca o anime pelo nome 
    private void btnVoltarActionPerformed \\ volta para o menu
    private void btnCurtirActionPerformed \\ adiciona o anime na lista de curtidos
    private void btnDescurtirActionPerformed \\ adiciona o anime na lista de descurtidos (um anime só pode ter uma avaliação por usuario, ou seja, ele é curtido ou 													descurtido, não pode ser ambos)
    private void btnAdicionarFavorito2ActionPerformed \\ adiciona o anime na lista de favoritos
Cadastrar Usuario:

	private void btnCadastrarActionPerformed \\ cadastra o usuario
    private void btnVoltarActionPerformed \\ volta para a tela de login
Favoritos:

	private void btnExcluirAnimeActionPerformed \\ remove um anime da lista de favoritos
	private void btnExcluirListaActionPerformed \\ exclui a lista de favoritos
	private void btnVoltarActionPerformed \\ volta ao menu
    private void btnCriarListaActionPerformed \\ cria a lista de favoritos
Login: 

	private void btnEntrarActionPerformed \\ verifica o login e vai para a tela menu
    private void btnCadastrarActionPerformed \\ vai para a tela de cadastro
Menu:

    private void btnBuscarAnimeActionPerformed \\ vai para a atela de buscar anime
    private void btnFavoritosActionPerformed \\ vai para a tela de favoritos
    private void btnSairActionPerformed \\ fecha o programa 
    private void btnAvaliacoesActionPerformed \\ vai para a tela de avaliações
	
## CREATE TABLE
Usuarios

	CREATE TABLE "Usuarios" (
	    "idUsuario" SERIAL PRIMARY KEY,
	    nome VARCHAR(100) NOT NULL,
	    email VARCHAR(150) NOT NULL UNIQUE,
	    senha VARCHAR(100) NOT NULL,
	    sexo VARCHAR(20) NOT NULL
	);
Animes

	CREATE TABLE "Animes" (
	    "idAnime" SERIAL PRIMARY KEY,
	    titulo VARCHAR(150) NOT NULL,
	    descricao TEXT,
	    duracao INTEGER,
	    genero VARCHAR(80)
	);
Avaliações

	CREATE TABLE "Avaliacoes" (
	    "idAvaliacao" SERIAL PRIMARY KEY,
	    "idUsuario" INTEGER NOT NULL,
	    "idAnime" INTEGER NOT NULL,
	    tipo VARCHAR(20) NOT NULL,

    CONSTRAINT fk_usuario_avaliacao
        FOREIGN KEY ("idUsuario")
        REFERENCES "Usuarios"("idUsuario")
        ON DELETE CASCADE,

    CONSTRAINT fk_anime_avaliacao
        FOREIGN KEY ("idAnime")
        REFERENCES "Animes"("idAnime")
        ON DELETE CASCADE,

    CONSTRAINT usuario_anime_unico
        UNIQUE ("idUsuario", "idAnime"),

    CONSTRAINT tipo_avaliacao_valido
        CHECK (tipo IN ('CURTIDO', 'DESCURTIDO'))
	);
Lista de favoritos

	CREATE TABLE "ListasFavoritos" (
	    "idLista" SERIAL PRIMARY KEY,
	    "idUsuario" INTEGER NOT NULL UNIQUE,
	
	    CONSTRAINT fk_usuario_lista
	        FOREIGN KEY ("idUsuario")
	        REFERENCES "Usuarios"("idUsuario")
	        ON DELETE CASCADE
	);
Animes favoritos

	CREATE TABLE "ListaFavoritosAnimes" (
	    "idLista" INTEGER NOT NULL,
	    "idAnime" INTEGER NOT NULL,
	
	    PRIMARY KEY ("idLista", "idAnime"),
	
	    CONSTRAINT fk_lista_favoritos
	        FOREIGN KEY ("idLista")
	        REFERENCES "ListasFavoritos"("idLista")
	        ON DELETE CASCADE,
	
	    CONSTRAINT fk_anime_favoritos
	        FOREIGN KEY ("idAnime")
	        REFERENCES "Animes"("idAnime")
	        ON DELETE CASCADE
	);
## FLUXOGRAMA DO PROJETO:
<img width="1929" height="608" alt="fluxogramaFeiflix drawio" src="https://github.com/user-attachments/assets/a4b9090a-139b-499b-bc29-fe2c6a65f833" />

# LINK DO VIDEO DO SISTEMA RODANDO:
https://youtu.be/BcNQUkH57gE
