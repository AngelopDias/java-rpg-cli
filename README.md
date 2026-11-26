# Java RPG CLI

Um mini RPG feito em Java utilizando Programação Orientada a Objetos (POO).

O projeto funciona diretamente no terminal (CLI) e permite criar dois personagens que podem:

* Atacar
* Receber dano
* Se curar
* Participar de turnos aleatórios
* Vencer a batalha ao derrotar o inimigo

## Conceitos utilizados

* Classes e Objetos
* Construtores
* Métodos
* Encapsulamento básico
* Random
* Loops
* Condicionais
* Manipulação de atributos

## Tecnologias

* Java
* Programação Orientada a Objetos (POO)

## Como executar

No terminal, entre na pasta do projeto e execute os comandos abaixo.

Compile os arquivos Java e coloque os arquivos `.class` gerados na pasta `bin`:

```powershell
javac -d bin src\App.java src\Personagem.java
```

Execute o programa:

```powershell
java -cp bin App
```

O comando `javac` compila o código-fonte. A opção `-d bin` indica que os arquivos compilados devem ser gerados dentro da pasta `bin`. Depois, o comando `java -cp bin App` procura a classe `App` dentro da pasta `bin` e inicia o programa.
javac -d bin src\App.java src\Personagem.java
java -cp bin App

## Objetivo

Projeto criado com foco em aprendizado de Java e prática de lógica de programação utilizando POO.
