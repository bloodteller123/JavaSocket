This is a Java socket program
it receives a string of binary number as input from client side
Binary string will be decoded into B8ZS form in Client side
Server receives encoded string and decodes it ,displying it on the screen

Usage Instruction:
First compile Server and Client program

javac Server.java and javac Client.java

Then first run java Server ,and then run java Client

when Client is running, type string of binary from keyboard

After that, all you need to do is to wait until programs complte their communication


Expected result:

if you type 100000000 from client, it will be encoded as + 000 + -0- +, send it to Server side
From server side, it will decode the code to 100000000 and dsiplays it on the screen


This program works for every single test case.

