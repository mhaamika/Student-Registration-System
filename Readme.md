# Volleyball Registration System

A Java-based registration and student management system for the Youth Volleyball Club.

## Project Overview
I originally started this project to apply the Java and object-oriented programming concepts I learned in class to a 
more extensive application and gain hands-on experience building something beyond small-scale programming exercises.
Although the project began with basic Java syntax and concepts, I continued developing and improving it as I learned 
new skills both in school and independently through online resources.


##  Features

* **Member Registration**

    * Register new club members with personal and contact information
    * Automatically assign unique membership numbers
    * Support up to 20 registered members

* **Input Validation**

    * Validate names, birthdays, gender, addresses, postal codes, phone numbers, and menu selections
    * Prevent invalid information from being entered into the system

* **Member Management**

    * View all registered members
    * Search for members using their membership number
    * Display individual member information
    * Calculate and display club statistics

* **Sorting**

    * Alphabetically sort members using a manually implemented bubble sort algorithm

* **Tournament Registration**

    * Register members for available tournament periods
    * Prevent duplicate tournament registrations
    * Display registered tournament periods

* **File Persistence**

    * Save member information to a text file
    * Load previously registered members when the program starts
    * Preserve membership numbers and tournament registrations between program sessions

* **Documentation**

    * Javadoc documentation for classes and important methods
    * Clear comments throughout the code to explain program logic

## Concepts Applied


* Object-Oriented Programming (OOP)
* Classes and Objects
* Constructors
* Encapsulation
* Methods
* ArrayLists
* Loops and Conditional Statements
* String Manipulation
* Input Validation
* Exception Handling
* Searching
* Sorting Algorithms
* File I/O
* Data Persistence



### Main Classes

**`Main.java`**
Handles user interaction, menus, input, and program flow.

**`Member.java`**
Represents a club member and stores their personal information and tournament registrations.

**`Family.java`**
Stores parent/guardian and contact information associated with a member.

**`RegistrationSystem.java`**
Manages the collection of members, searching, sorting, statistics, and file saving/loading.

##  Data Storage
The system uses basic Java file I/O to save registration information to `members.txt`.

When the program starts, previously saved members are loaded into the system. When changes are made, the updated information can be saved back to the file.

This allows registration data to remain available even after the program is closed.

## What I Learned

This project helped me strengthen my understanding of Java by requiring me to apply concepts together rather than learning them individually.

Through the development process, I practiced:

* Designing a program using multiple classes
* Breaking large pieces of code into reusable methods
* Working with collections of objects
* Validating and handling user input
* Implementing a sorting algorithm
* Searching through stored objects
* Reading from and writing to files
* Handling invalid input with exceptions
* Organizing and documenting a larger Java project

Features such as file I/O and persistent data storage were added as I independently learned new concepts outside of the original assignment.

## Future Improvements

Potential future versions of the project could include:

* A graphical user interface
* A database for storing member information
* More advanced search and filtering
* Additional tournament management features
* Unit testing
* A web-based version of the application

