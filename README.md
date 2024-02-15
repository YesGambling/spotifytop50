hi!!!


this is a basic spotify 'data scraper' of some sort.

the primary 'goal': utilize spotify's API to grab the top 50 songs on spotify, then allows the users to search through it. 

code is written entirely in java with maven.



screenshots of code:

main file

![image](https://github.com/orangejuiceplz/spot50/assets/155986030/d2e09c43-9f11-41d4-8b64-6318a19f1174)






pom.xml

![image](https://github.com/LQ84i-1/spot200/assets/155986030/39c8bbde-c79e-434e-9708-8eff5bffd8d1)



auth file

![image](https://github.com/orangejuiceplz/spot50/assets/155986030/e387e910-6d9a-4261-bb0b-7c27be1af7f9)



example

![image](https://github.com/orangejuiceplz/spot50/assets/155986030/853e18da-0125-4a90-8977-ab41d02b934a)






>Instructions for input: The program takes user input through the Scanner class in the getTopSongs method. The user is prompted to enter a number between 1 and 50, which represents the position of >a song in the Spotify Top 50 playlist.

>Use of at least one list: The program uses two ArrayList objects, songUrls and songInfo, to store the Spotify URLs and information of the selected songs, respectively.

>At least one student-developed procedure: The getTopSongs method is a student-developed procedure that fetches the top 50 songs from a Spotify playlist and allows the user to access information >about a song by its position in the playlist.

>An algorithm that includes sequencing, selection, and iteration: The getTopSongs method includes a while loop (iteration) that continues to prompt the user for more songs until they decide to >stop. Inside the loop, there’s an if-else statement (selection) that checks if the user’s 
>input is valid. The method also includes a sequence of operations to fetch the songs, get the song information, and add it to the list.

>Calls to your student-developed procedure: The getTopSongs method is called in the main method.

>Instructions for output: The program outputs the information of the selected songs and their Spotify URLs to the console using System.out.println.
