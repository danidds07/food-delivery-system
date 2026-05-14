<h1 align="center">📦 Sistema de Delivery</h1>

<p align="center">
  Sistema de delivery desenvolvido em Java, com interface via terminal (CLI) e interface gráfica (GUI),
  integrado ao banco de dados SQLite para armazenamento persistente de clientes, restaurantes e pedidos.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-Core-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/Aplicação-CLI%20%2B%20GUI-green?style=for-the-badge">
  <img src="https://img.shields.io/badge/Banco%20de%20Dados-SQLite-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Arquitetura-OOP-purple?style=for-the-badge">
</p>

<hr>

<h2>🚀 Funcionalidades</h2>
<ul>
  <li>Cadastro de clientes</li>
  <li>Cadastro de restaurantes com itens no cardápio</li>
  <li>Cadastro de entregadores</li>
  <li>Criação de pedidos com múltiplos itens</li>
  <li>Atualização do status dos pedidos</li>
  <li>Listagem detalhada dos pedidos</li>
  <li>Atribuição automática de entregadores</li>
  <li>Interface gráfica para interação com o usuário (GUI)</li>
</ul>

<h2>🧠 Sobre o Projeto</h2>
<p>
  Este projeto simula uma plataforma de delivery utilizando tanto interface gráfica quanto terminal.
  O sistema permite cadastrar clientes, restaurantes e entregadores,
  criar pedidos a partir dos cardápios dos restaurantes,
  atribuir entregadores automaticamente e acompanhar o status das entregas.
</p>

<p>
  O principal objetivo deste projeto é praticar <strong>programação em Java</strong>,
  <strong>programação orientada a objetos</strong>,
  <strong>arquitetura de sistemas</strong> e
  <strong>integração com banco de dados</strong>,
  incluindo a evolução de aplicações CLI para aplicações com interface gráfica.
</p>

<h2>🏗️ Estrutura do Projeto</h2>

<pre>
food-delivery-system/
│
├── lib/
│   └── sqlite-jdbc-3.45.3.0.jar
│
├── src/
│   ├── Main.java
│   │
│   ├── client/
│   │   └── Client.java
│   │
│   ├── database/
│   │   └── Database.java
│   │
│   ├── deliveryman/
│   │   └── Deliveryman.java
│   │
│   ├── orders/
│   │   ├── Order.java
│   │   └── Status.java
│   │
│   ├── restaurant/
│   │   ├── Menu.java
│   │   └── Restaurant.java
│   │
│   ├── system/
│   │   └── AppSystem.java
│   │
│   └── users/
│       └── User.java
│
├── bin/
│   └── arquivos compilados
│
├── delivery.db
├── COMO_RODAR.txt
└── README.md
</pre>

<h2>⚙️ Tecnologias Utilizadas</h2>
<ul>
  <li>Java</li>
  <li>Programação Orientada a Objetos (OOP)</li>
  <li>Interface via Terminal (CLI)</li>
  <li>Interface Gráfica (GUI)</li>
  <li>SQLite Database</li>
  <li>JDBC (SQLite Driver)</li>
</ul>

<h2>🔄 Fluxo do Pedido</h2>
<ol>
  <li>Cadastrar um cliente</li>
  <li>Cadastrar um restaurante e adicionar itens ao cardápio</li>
  <li>Criar um novo pedido</li>
  <li>Atribuir um entregador disponível</li>
  <li>Atualizar o status do pedido:
    <ul>
      <li><code>REALIZADO</code></li>
      <li><code>EM_PREPARO</code></li>
      <li><code>EM_ENTREGA</code></li>
      <li><code>ENTREGUE</code></li>
    </ul>
  </li>
</ol>

<h2>💡 Conceitos Aplicados</h2>
<ul>
  <li>Herança</li>
  <li>Encapsulamento</li>
  <li>Classes abstratas</li>
  <li>Enums</li>
  <li>Listas e coleções</li>
  <li>Separação de responsabilidades</li>
  <li>Design e interação GUI</li>
  <li>Integração com banco de dados SQLite</li>
  <li>Persistência de dados</li>
  <li>Conectividade JDBC</li>
</ul>

<h2>▶️ Como Executar</h2>

<p>
  Este projeto utiliza <strong>SQLite via JDBC</strong>. Antes de executar,
  baixe o driver JDBC do SQLite e coloque-o dentro da pasta <code>lib/</code>.
</p>

<p><strong>Exemplo do driver:</strong></p>
<pre><code>sqlite-jdbc-3.45.3.0.jar</code></pre>

<p><strong>Windows - Compilar:</strong></p>
<pre><code>javac -cp "lib/sqlite-jdbc-3.45.3.0.jar;src" -d bin src\Main.java src\client\*.java src\database\*.java src\deliveryman\*.java src\orders\*.java src\restaurant\*.java src\system\*.java src\users\*.java</code></pre>

<p><strong>Windows - Executar:</strong></p>
<pre><code>java -cp "bin;lib/sqlite-jdbc-3.45.3.0.jar" Main</code></pre>

<p><strong>Linux/Mac - Compilar:</strong></p>
<pre><code>javac -cp "lib/sqlite-jdbc-3.45.3.0.jar:src" -d bin $(find src -name "*.java")</code></pre>

<p><strong>Linux/Mac - Executar:</strong></p>
<pre><code>java -cp "bin:lib/sqlite-jdbc-3.45.3.0.jar" Main</code></pre>

<p>
  O arquivo <code>delivery.db</code> será criado automaticamente na primeira execução do programa.
</p>

<h2>📌 Destaques</h2>
<ul>
  <li>Interface dupla: CLI e GUI</li>
  <li>Geração automática de IDs para todas as entidades</li>
  <li>Cálculo automático do valor total do pedido</li>
  <li>Atribuição de entregadores baseada em disponibilidade</li>
  <li>Controle completo de status dos pedidos</li>
  <li>Persistência de dados com SQLite</li>
</ul>

<h2>🎯 Objetivo</h2>
<p>
  Este projeto foi desenvolvido com foco em aprendizado e prática,
  aplicando conceitos de Java, orientação a objetos,
  interfaces gráficas e persistência de dados em aplicações reais.
</p>

<h2>👨‍💻 Autor</h2>

<p align="center">
  Desenvolvido por <strong>Daniel Augusto Silva</strong><br>
</p>
