# Track-Driving-History
1. Design

At first, I was thinking use only one class(DriveHistory) include some methods, such as addDriver(), addTrip(), getResult() etc. to get the output. But after carefully thinking, I decide to make a Object Orented Design to make more classes like Driver, Trip, and History instead of using only one class, OOD makes the codes modularity for easier troubleshooting, flexibility through polymorphism, and effective problem solving.


2.Coding

In the trip class, we have two variables which are the total time and total miles, the getter and setter are make the method for encapsulation to make our data safty.

Use java.time.localtime and duration to get the different between two time;

In the history class, initialize a hashmap to store the driver's name and trips, before add the new trip to the map, we have to check and discard the average a speed is less than 5 mph or greater than 100 mph.In the result() function, use a PriorityQueue to sort the data by the most miles driven to least, and output the results by the fomula;

3.Test

Unit test, TDD, Line coverage/branch coverage
Wrote some unit test cases and based on test one thing at a time in isolation, follow the AAA Rule: Arrange, Act, Assert, test the entire spectrum, and improve the line and branch coverage;
