**REST API para uma loja virtual criada com Spring Boot e PostgreSQL.**

Para configuração do banco de dados, execute o script _schema.db_ no seu console do psql.
Ele deverá criar um banco de dados chamado _vstore_db_. Seu se dá a partir doz usuárioz vstore_adm (para configuração) e vstore_usr (utilizado pela API). Suas senhas são seus próprios nomes de usuários.

A versão do PostgreSQL utilizada neste projeto foi a 9.6.5.

-----

A API consiste em chamadas HTTP padrão (GET, POST, PUT, DELETE), seguindo as especificações de sistemas REST. 
Sua comunicação é feita através de objetos JSON e possui as seguintes chamadas:


-- Relacionadas ao Usuário --

 * /users - método _POST_ cria um novo usuário.
            retorna o Status 201 (Created) e a uri do novo usuário criado no header da resposta.
 * /users - método _GET_ lista todos os usuários do sistema.
            retorna um JSON Array.
 * /users/{id} - método _GET_ retorna o usuário com o id em questão.
            retorna um JSON Object.
 * /users/{id} - método _PUT_ atualiza o usuário com o id em questão.
            retorna o Status 200 (OK), confirmando a operação.
 * /users/{id} - método _DELETE_ remove o usuário com o id em questão.
            retorna o Status 204 (No Content), confirmando a operação.
 * /users/{id}/cards - método _POST_ cria um novo cartão de crédito para o usuário com o id especificado.
            retorna o Status 201 (Created) e a uri do novo cartão criado no header da resposta.
 * /users/{id}/cards - método _GET_ lista todos os cartões cadastrados do usuário com o id especificado.
            retorna um JSON Array.
 * /users/{id}/cards/{cardId} - método _GET_ retorna os dados do cartão com id especificado do usuário com o id em questão.
            retorna um JSON Object.
 * /users/{id}/cards/{cardId} - método _DELETE_ remove o cartão com o id especificado do usuário com o id em questão.
            retorna o Status 204 (No Content), confirmando a operação.
 * /users/{id}/chart/products/ - método _GET_ lista todos os produtos do sistema.
            retorna um JSON Object.
 * /users/{id}/chart/products/{productId} - método _PUT_ adiciona o produto com o id especificado do carrinho do usuário.
            retorna o Status 200 (OK), confirmando a operação.
 * /users/{id}/chart/products/{productId} - método _DELETE_ remove o produto com o id especificado do carrinho do usuário.
            retorna o Status 204 (No Content), confirmando a operação.
 * /users/{id}/chart/buy - método _POST_ inicia o processo de compra dos produtos inseridos no carrinho do usuário.
            retorna o Status 201 (Created) e a uri da compra no header da resposta.
 * /users/{id}/purchases - método _GET_ lista todas as compras realizadas pelo usuário.
            retorna um JSON Array.
 * /users/{id}/{id} - método _GET_ mostra os detalhes de uma compra do usuário.
            retorna um JSON Object.
 * /users/{id}/{id} - método _DELETE_ cancela/remove uma compra do usuário.
            retorna o Status 204 (No Content), confirmando a operação.
            
-- Relacionadas aos Produtos --
 
 * /products - método _POST_ adiciona um novo produto no sistema.
            retorna o Status 201 (Created) e a uri do novo produto no header da resposta.
 * /products - método _GET_ lista todos os produtos do sistema.
            retorna um JSON Array.
 * /products/{id} - método _GET_ retorna os detalhes de um produto no sistema.
            retorna um JSON Object.
 * /products/{id} - método _PUT_ atualiza os detalhes de um produto no sistema.
            retorna o Status 200 (OK), confirmando a operação.
 * /products/{id} - método _DELETE_ remove os detalhes de um produto no sistema.
            retorna o Status 204 (No Content), confirmando a operação.
            
-- Relacionadas ao armazenamento de arquivos (para imagens a serem inseridas nos produtos) -- 
 * /media - método _POST_ adiciona um novo arquivo ao sistema.
            retorna o Status 201 (Created) e a uri do novo produto no header da resposta.
 * /media/{uuid} - método _GET_ retorna o arquivo especificado.
            retorna um JSON Object.
            
 