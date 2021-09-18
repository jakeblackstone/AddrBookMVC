CFM Intern Assessment - Address book

Created by Jacob Blackstone, 2021

--

The function of this application involves a basic address book. The purpose here is to demonstrate an application written with the Model-View-Controller framework in mind, while also demonstrating CRUD functionality and data persistence.


INSTALLATION

In this ZIP file are all project directories and files. You can open and build it in an IDE of your choice (preferably IntelliJ).


DEPENDENCIES

This application requires at the latest version of Java/JDK installed (16 at time of writing).


OPERATION

You will be presented with a 2-panel window, one for the table view of contacts, and another for displaying the data of an individual contact. 

A contact has the following fields:

First Name
Last Name
Street
City
State
Zip
Phone
Email

All fields except Zip accept ASCII character strings. Zip requires an integer. For state, when adding, a drop-down selection will be presented. This program assumes a US-based address.

Hitting "Add" or "Edit" will open a dialog window. If "Edit" is selected, the fields will be filled with a the selected contact's info.

First name, last name, and zip must be entered at the very least for the input to be valid.

To delete a contact, select one from the table and hit "Delete". If there is no selection or the table is empty, an error message will pop up.

You can sort by first name, last name, and Zip in the tables in ascending, descending, or none order.

Hitting "Reset" will delete all data after a confirmation dialog if any exists. This is permenant and blanks the XML file after hitting "OK".


BACKGROUND

This application is written in the JavaFX framework in conjunction with Scene Builder. Scene Builder allows fast UI design and auto-generation of FXML files, which provide the view/UI  portion of the application. The FXML files and elements are "hooked" into the controller programmatically to allow the passing and reading of data.

For clarity, the individual components of the application are divided into respective MVC-titled packages.

** Note, due to a quirk of how IntelliJ generates and structures JavaFX projects, the view package has to be under resources **

The entry point of the application is in Main.java. Here, the FXML is loaded and initialized in the appropriate order, and data structures to store data in memory during execution are set up. Main could be considered a "controller" in a way, but is more of a high level entry point that sets things up and lets the controller classes take the wheel. It is not hooked to any view, nor does it allow the model and view to communicate without the use of a controller, and thus does not necessarily violate MVC.


STRUCTURE

The Main() constructor can be used for testing purposes and manually inputting contact data.

There are 3 controller files in the respective package: one for the root/menu view, one for the main two-pane view, and one for the add/edit dialog.

In package model, there is an entity class Contact in the eponymous .java file, which defines the basic data model of a Contact in the book. Here, a wrapper is used to convert Objects to XML, which is used to persist application data at the desired path.

The data stored in the address book, as mentioned above, is stored in XML using JAXB. One might argue that using the JDBC driver and a RDBMS such as SQLite or MySQL might be a better fit, and I would agree, but for the scope of this project, a hierarchical, flat-file approach using XML is appropriate to store the data. There is also the concept of object-relational impedance mismatch to consider.

http://www.agiledata.org/essays/impedanceMismatch.html

However, for a larger-scale project, especially one web-based, a tool like Hibernate might be appropriate for mapping objects to a relational DB.

Any methods tagged @FXML are "injectable", meaning they play a role in updating the view. Because we don't want them being accessed beyond that, they are marked private, while non @FXML methods may be public

ISSUES

There remain some issues to be fixed.

Persistence: If the XML file is deleted or data wiped by "reset", the app will throw a NPE.
As it stores the last opened path in the file registry, I had to manually add tags to get it to launch again.

Styling: The CSS parser seems to throw several errors, one asking to "Report a NPE", but it does not affect
program execution

First and last name: These are the minimum for a contact. If one is missing, it will ask for that before proceeding.
If both are missing, it will only ask for last name.










