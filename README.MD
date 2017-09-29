**REST API para uma loja virtual criada com Spring Boot e PostgreSQL.**

Para configuração do banco de dados, execute o script _schema.db_ no seu console do psql.
Ele deverá criar um banco de dados chamado _vstore_db_. Seu se dá a partir doz usuárioz vstore_adm (para configuração) e vstore_usr (utilizado pela API). Suas senhas são seus próprios nomes de usuários.

A versão do PostgreSQL utilizada neste projeto foi a 9.6.5.

-----

A API possui as seguintes chamadas.

 * /users - método POST cria um novo usuário.
 * /users - método GET lista todos os usuários do sistema.
 * /users/{id} - método GET retorna o usuário com o id em questão.