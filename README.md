# CSX42: Assignment 3

## Name: Madhan Thangavel

## B NO: B00814916

---

Following are the commands and the instructions to run ANT on your project.

Note: build.xml is present in [studentskills/src](./studentskills/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```

The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dout1="output1.txt" -Dout2="output2.txt" -Dout3="output3.txt" -Derror="error.txt" -Ddebug="3"
```

Note: 
1. Arguments accept the absolute path of the files.
2. input.txt,modify.txt and error.txt should be in [studentskills/](./studentskills/) folder.
3. The last argument is the Logger value as per Mylogger. (1.FileProcessor 2. Constructor 3.Debug 4.Error)

## Description:

* To build a Replica System for Student Records using Observer pattern.
* In observer pattern, when one object changes, all the dependent objects gets notified and that they get updated.
* The object that changes the value and need to notify other objects is known as Subject and the objects those get        
  notified are known as observer.
* Subject and observer holds a one-many relationship.
* In this assignment original node is subject and two backup nodes are observer.So when original node changes the           respective changes are made into backup nodes.

###### Implementation of Observer pattern:

Whenever a new node is created, two clones of the node are created as backup nodes and reference to the are stored in a data structure with the original node. The notification of backup nodes takes place when there are changes in the original node. The changes take place in the following scenarios:

Insertion
Whenever a new value is added to the original node, the corresponding backup nodes are also updated.

Modify
Whenever a new value is replaced to the original node, the corresponding backup nodes are also updated.

The code perfroms the following tasks:

1. Reads the command line arguments.
2. Builds trees, based on input.txt and the observer pattern.
3. Applies updates according to modify.txt.
4. Calls a method in Results, via the method in FileDisplayInterface, to write the data stored in Results to output1.txt,    output2.txt, and output3.txt, for the three trees.
5. When read an input file, it tries to insert the values into the main tree corresponding to the node that has the          BNumber. 
6. During modify if a node with the BNumber exists, then update that node in the main tree, notifyAll(...) to the two        corresponding listener nodes.

###### Justification for Data structure used :

The data structure used for storing the student information is Array list since the it supports the following operations insert, search and modify. Each object consists of bNumber (student id) and list of skills. The bNumber is used as identifier for the list. Since a student can have multiple skills, those are stored using a list.There are two backup lists, which are copies of the original lists and are maintained using the Observer pattern using unique id. The tree implements the SubjectI interface and ObserverI interface.

ArrayList - Used for Storing the fileData line by line and also for mainting the list of observers attached to particular node. ArrayList doestn't have fixed size, hence we can modify it more frequently when needed.
Worst Case Time Complexity - O(1) for adding a element in a list. O(n) for removal of data from array list since we go through each element under normal circumstances.
Getting an element from Array List take O(1).

HashSet - used for maintaing the skills. Avoids duplicates Data
Time Complexity - The underlying data structure for HashSet is hashtable.
So, (average or usual case) time complexity for add, remove and look-up operation of HashSet takes O(1) time.

###### References and Citations:

Iterating over arraylist to check if skill is already present: https://stackoverflow.com/questions/8284936/java-see-if-arraylist-contains-string?rq=1

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Note: I would like to use one slack day for this assignment.

Date: [JULY 10 2020]