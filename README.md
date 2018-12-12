# README #

O foco deste projeto é fazer a integração bancária do SAP do Cliente Dpaschoel.

### Configuração ###
Utilizar o wildfly pré configurado desse link:
http://efcs.com.br/citro/wildfly-8.1.0.Final.zip
Ele está configurado para o SQL server.

Para ter acesso ao sistema, basta rodar o import.sql
Usuário: admin
Senha: admin

### MYSQL ###
Para subir o projeto utilizando o MYsql server, é preciso configurar o persistence.xml e o standalone.
Obs.: Precisa recolocar o datasource ExampleDS.
