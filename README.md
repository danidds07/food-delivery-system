<h1 align="center">📦 Food Delivery System</h1>

<p align="center">
  A Java-based food delivery system featuring both a command-line interface (CLI) and a graphical user interface (GUI),
  with SQLite database integration for persistent data storage, including client registration, restaurant management, and order processing.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-Core-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/Application-CLI%20%2B%20GUI-green?style=for-the-badge">
  <img src="https://img.shields.io/badge/Database-SQLite-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Architecture-OOP-purple?style=for-the-badge">
</p>

<hr>

<h2>🚀 Features</h2>
<ul>
  <li>Client registration</li>
  <li>Restaurant registration with menu items</li>
  <li>Deliveryman registration</li>
  <li>Order creation with multiple items</li>
  <li>Order status updates</li>
  <li>Order listing with details</li>
  <li>Automatic deliveryman assignment</li>
  <li>Graphical interface for user interaction (GUI)</li>
</ul>

<h2>🧠 About the Project</h2>
<p>
  This project simulates a food delivery platform with both terminal and graphical interfaces.
  It allows users to register clients, restaurants, and delivery personnel,
  create orders from restaurant menus, assign deliverymen, and track order status.
</p>

<p>
  The main goal of this project is to practice <strong>Java programming</strong>,
  <strong>object-oriented programming concepts</strong>, and <strong>system design</strong>,
  including the transition from CLI applications to graphical interfaces.
</p>

<h2>🏗️ Project Structure</h2>

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
│   └── compiled files
│
├── delivery.db
├── COMO_RODAR.txt
└── README.md
</pre>
<h2>⚙️ Technologies Used</h2>
<ul>
  <li>Java</li>
  <li>Object-Oriented Programming (OOP)</li>
  <li>Command-Line Interface (CLI)</li>
  <li>Graphical User Interface (GUI)</li>
  <li>SQLite Database</li>
  <li>JDBC (SQLite Driver)</li>
</ul>

<h2>🔄 Order Workflow</h2>
<ol>
  <li>Register a client</li>
  <li>Register a restaurant and add menu items</li>
  <li>Create a new order</li>
  <li>Assign an available deliveryman</li>
  <li>Update the order status:
    <ul>
      <li><code>REALIZADO</code></li>
      <li><code>EM_PREPARO</code></li>
      <li><code>EM_ENTREGA</code></li>
      <li><code>ENTREGUE</code></li>
    </ul>
  </li>
</ol>

<h2>💡 Concepts Applied</h2>
<ul>
  <li>Inheritance</li>
  <li>Encapsulation</li>
  <li>Abstract classes</li>
  <li>Enums</li>
  <li>Lists and collections</li>
  <li>Separation of responsibilities</li>
  <li>GUI design and interaction</li>
  <li>Database integration with SQLite</li>
  <li>Persistent data storage</li>
  <li>JDBC connectivity</li>
</ul>

<h2>▶️ How to Run</h2>

<p>
  This project uses <strong>SQLite via JDBC</strong>. Before running the application,
  download the SQLite JDBC driver and place it inside the <code>lib/</code> folder.
</p>

<p><strong>Example driver file:</strong></p>
<pre><code>sqlite-jdbc-3.45.3.0.jar</code></pre>

<p><strong>Windows - Compile:</strong></p>
<pre><code>javac -cp "lib/sqlite-jdbc-3.45.3.0.jar;src" -d bin src\Main.java src\client\*.java src\database\*.java src\deliveryman\*.java src\orders\*.java src\restaurant\*.java src\system\*.java src\users\*.java</code></pre>

<p><strong>Windows - Run:</strong></p>
<pre><code>java -cp "bin;lib/sqlite-jdbc-3.45.3.0.jar" Main</code></pre>

<p><strong>Linux/Mac - Compile:</strong></p>
<pre><code>javac -cp "lib/sqlite-jdbc-3.45.3.0.jar:src" -d bin $(find src -name "*.java")</code></pre>

<p><strong>Linux/Mac - Run:</strong></p>
<pre><code>java -cp "bin:lib/sqlite-jdbc-3.45.3.0.jar" Main</code></pre>

<p>
  The <code>delivery.db</code> file will be created automatically when the program runs for the first time.
</p>

<h2>📌 Highlights</h2>
<ul>
  <li>Dual interface: CLI and GUI</li>
  <li>Automatic ID generation for all entities</li>
  <li>Order total calculation based on selected items</li>
  <li>Delivery assignment based on availability</li>
  <li>Status-based order tracking</li>
</ul>

<h2>🎯 Purpose</h2>
<p>
  This project was created for learning and practice purposes, focusing on Java fundamentals,
  object-oriented design, and building both console and graphical applications.
</p>

<h2>📈 Future Improvements</h2>
<ul>
  <li>Improve database structure and queries</li>
  <li>Improve GUI usability and design</li>
  <li>Enhance input validation and exception handling</li>
  <li>Add authentication for different user roles</li>
  <li>Implement more advanced order tracking features</li>
</ul>

<h2>👨‍💻 Author</h2>

<p align="center">
  Developed by <strong>Daniel Augusto Silva</strong><br>
</p>
