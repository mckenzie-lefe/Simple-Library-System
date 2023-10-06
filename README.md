# Simple-Library-System
 CPSC 501 - Refactoring Assignment 1

Writen By McKenzie Lefebvre
Program Description:

This program is used to check out and return books at a library used by teachers and students. Books can only be checked out by library members using their library member ID which is unique. To check out a book or return a book the member must enter the book ID and their member id. If the book is checked out by the member the program must request that the member rate the book they are returning with either like or dislike. If the book is not checked out by the member or any other 
member then the program should allow the member to check out the book,
otherwise it should deny the members request to check out the book. The library system keeps track of all library members ids, name, phone number, their current checked out books and previously checked out books. There previously checked out books must be marked as either liked, or disliked as indicated by the member when returning the book. The program also need to keep track of all the books in 
inventory including each books id, title, description, author, if the book is check out or not and if it is by which member. Books can not be checked out my more then one member at time. The program should also keep track of how many times a book has been checked out and its average like rating by members. Student members are only allowed to check out books for 7 days and are charged $3 dollars for everyday overdue. Teacher members are allowed to check out a book for 14 days and are charged $2 dollars for everyday overdue. 