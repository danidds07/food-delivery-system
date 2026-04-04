<h1 align="center">📦 Food Delivery System</h1>

<p align="center">
  A Java-based food delivery system built with a command-line interface (CLI), featuring
  client registration, restaurant management, and order processing.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-Core-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/Application-CLI-green?style=for-the-badge">
  <img src="https://img.shields.io/badge/Architecture-OOP-blue?style=for-the-badge">
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
</ul>

<h2>🧠 About the Project</h2>
<p>
  This project simulates a food delivery platform in a terminal environment.
  It allows users to register clients, restaurants, and delivery personnel,
  create orders from restaurant menus, assign deliverymen, and track order status.
</p>

<p>
  The main goal of this project is to practice <strong>Java programming</strong>,
  <strong>object-oriented programming concepts</strong>, and <strong>system organization</strong>
  through a real-world inspired application.
</p>

<h2>🏗️ Project Structure</h2>

<pre>
food-delivery-system/
│
├── Main.java
├── system/
│   └── AppSystem.java
│
├── users/
│   └── User.java
│
├── client/
│   └── Client.java
│
├── deliveryman/
│   └── Deliveryman.java
│
├── restaurant/
│   ├── Restaurant.java
│   └── Menu.java
│
├── orders/
│   ├── Order.java
│   └── Status.java
</pre>

<h2>⚙️ Technologies Used</h2>
<ul>
  <li>Java</li>
  <li>Object-Oriented Programming (OOP)</li>
  <li>Command-Line Interface (CLI)</li>
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
</ul>

<h2>▶️ How to Run</h2>

<p><strong>1. Compile the project:</strong></p>
<pre><code>javac Main.java</code></pre>

<p><strong>2. Run the application:</strong></p>
<pre><code>java Main</code></pre>

<h2>🖥️ Menu Preview</h2>
<pre>
1. Register client
2. Register restaurant
3. Register deliveryman
4. New order
5. Assign deliveryman
6. Update order status
7. Order list
8. Exit
</pre>

<h2>📌 Highlights</h2>
<ul>
  <li>Automatic ID generation for all entities</li>
  <li>Order total calculation based on selected items</li>
  <li>Delivery assignment based on availability</li>
  <li>Status-based order tracking</li>
  <li>Terminal-based interaction for simplicity and learning</li>
</ul>

<h2>🎯 Purpose</h2>
<p>
  This project was created for learning and practice purposes, focusing on Java fundamentals,
  object-oriented design, and building structured console applications.
</p>

<h2>📈 Future Improvements</h2>
<ul>
  <li>Add data persistence with files or a database</li>
  <li>Create a graphical user interface</li>
  <li>Improve input validation and exception handling</li>
  <li>Add authentication for different user roles</li>
  <li>Implement more advanced order tracking features</li>
</ul>

<h2>👨‍💻 Author</h2>

<p align="center">
  Developed by <strong>Daniel Augusto Silva</strong><br>
</p>
