Java Programming Labs

This repository contains a collection of laboratory works and practical assignments completed as part of studying the Java programming language. The project covers a wide range of topics, from fundamental algorithms and unit testing to graphical user interface (GUI) development, 2D graphics, and database integration.

🎯 Core Focus Areas

Rather than listing individual files, the assignments in this repository are organized around key Java development concepts:

Algorithms & Mathematical Computations: Working with arrays, calculating numerical series, and performing geometric calculations (distances, perimeters, point-in-shape checks).

Graphical User Interface (GUI): Building multi-window desktop applications, handling mouse and keyboard events, configuring hotkeys, and customizing UI components.

2D Graphics & File I/O: Parsing configuration data from external text files, validating input, and rendering 2D geometric primitives on a canvas.

Database Integration (CRUD): Connecting to relational databases, executing SQL queries to create, read, and delete records, and displaying data in custom table models.

Unit Testing: Writing automated unit tests to verify algorithm correctness and edge cases.

🛠 Tech Stack

Programming Language: Java (JDK 8+)

GUI & 2D Graphics: Java Swing, Java AWT (Graphics2D)

Database: JDBC, UCanAccess (MS Access .accdb)

Testing: JUnit 5 (org.junit.jupiter)

File Handling: Java I/O

🚀 Requirements & Running

Core & GUI Applications: Requires JDK (version 8 or higher). For tasks that read external files (such as INPUT), ensure the required file is placed in the project root directory.

Database Applications: Add the UCanAccess driver and its dependencies (jackcess, hsqldb, commons-lang, commons-logging) to the project Classpath, and place the .accdb database file in the working directory.

Unit Tests: Requires the JUnit Jupiter API (org.junit.jupiter:junit-jupiter-api) dependency configured in your IDE or build tool (Maven / Gradle).