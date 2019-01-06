# README #

Projeto SetupBox da Astar Labs.

### Configuração ###

Para executar a aplicação de fora do Eclipse/STS:

  > mvn spring-boot:run
  
  
  Antes da execução deve-se configurar o nome do banco e as credenciais do MYSQL no arquivo:
  
  application.properties
  
  ** Se a versão do MYSQL for >= 5.7 o Flyway já criará o banco automaticamente conforme o nome que estiver no application.properties, se a versão for inferior deve-se criar o banco antes da execução do jar.
  
  Para execução em ambiente de Testes/Produção por linha de comando, deve-se copiar o arquivo 
  setupbox-1.0.0.jar para a pasta no servidor que disponibilizará a API, e executar com o comando:
  
  > java -jar setupbox-1.0.0.jar


A API estará disponibilizando na porta 8080 do servidor, caso tenha que rodar em outra porta deve-se alterar a porta no arquivo application.properties


### CURL ###

Para enviar por linha de comando, pode-se usar o exemplo abaixo:

curl -i -X POST -H "Content-Type: multipart/form-data" -F "file=@titan-TestCycleExport_20180828.xml"  http://localhost:8080/laboratorios -u astar:123456



### MYSQL ###

