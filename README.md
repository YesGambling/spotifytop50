hi!!!


this is a basic spotify 'data scraper' of some sort.

the primary 'goal': utilize spotify's API to grab the top 50 songs on spotify, then allows the users to search through it. 

code is written entirely in java with maven.



screenshots of code:

main file

![image](https://github.com/LQ84i-1/spot200/assets/155986030/06e8a88f-f63e-4fbe-9096-b31d3837fba1)





pom.xml

![image](https://github.com/LQ84i-1/spot200/assets/155986030/39c8bbde-c79e-434e-9708-8eff5bffd8d1)



auth file

![image](https://github.com/LQ84i-1/spot200/assets/155986030/757b862d-f3cc-49ad-b495-393ecb073c45)


example

![image-mh](https://github.com/LQ84i-1/spot200/assets/155986030/237db0dc-ee5d-414a-86da-02c4e3fdc249)





Instructions for input: The program takes user input through the Scanner class in the getTopSongs method. The user is prompted to enter a number between 1 and 50, which represents the position of a song in the Spotify Top 50 playlist.

Use of at least one list: The program uses two ArrayList objects, songUrls and songInfo, to store the Spotify URLs and information of the selected songs, respectively.

At least one student-developed procedure: The getTopSongs method is a student-developed procedure that fetches the top 50 songs from a Spotify playlist and allows the user to access information about a song by its position in the playlist.

An algorithm that includes sequencing, selection, and iteration: The getTopSongs method includes a while loop (iteration) that continues to prompt the user for more songs until they decide to stop. Inside the loop, there’s an if-else statement (selection) that checks if the user’s 
input is valid. The method also includes a sequence of operations to fetch the songs, get the song information, and add it to the list.

Calls to your student-developed procedure: The getTopSongs method is called in the main method.

Instructions for output: The program outputs the information of the selected songs and their Spotify URLs to the console using System.out.println.
