# Inodevs - Back-End Spring
Repositório para aplicação Back-end em Spring para persistência de dados.

# Guia de Instalação Local

## Ferramentas:

- [Java](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR) - pode ser a última versão, mas recomendamos a 17;
- [Spring](https://start.spring.io/) (utilizado apenas para a criação inicial do projeto);
- [Maven](https://maven.apache.org/download.cgi);
- [Mysql](https://dev.mysql.com/downloads/) - baixar o 'MySQL Community Server';
- [VScode](https://code.visualstudio.com/download).

> **Importante:** Coloque a pasta bin do maven e do mysql nas variaveis de ambiente. 

- Instale também a extensão 'Extension Pack for Java' no VSCode.

## Manual de instalação local:

1. Clone o github:
```console
git clone https://github.com/Inodevs-6/Inodevs-back-spring.git
```

2. Entre na pasta clonada:
```console
cd Inodevs-back-spring
```

3. Criei o banco de dados:
```console
mysql -u root -p < DDL.sql
```

4. Teste o ambiente para checar se tudo está configurado:
```console
mvn clean test
```

5. Rode a aplicação:
```console
mvn spring-boot:run
```
