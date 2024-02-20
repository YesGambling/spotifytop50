hi!!!


this is a basic spotify 'data scraper' of some sort.

the primary 'goal': utilize spotify's API to grab the top 50 songs on spotify, then allows the users to search through it. 

code is written entirely in java with maven as an additon for dependencies building.



screenshot of example:


![image](https://github.com/orangejuiceplz/spot50/assets/155986030/853e18da-0125-4a90-8977-ab41d02b934a)


instructions for proper download:

1. click on releases
2. download the .JAR file
<!> IMPORTANT: YOU NEED JDK 20 TO RUN THIS PROGRAM. YOU ALSO NEED A JRE, IF YOU DON'T KNOW WHERE TO GET ONE, LOOK IT UP <!>
3. execute the .jar file
4. enjoy


Collegeboard Create Performance Task Guidelines:

>instructions for input: the program takes user input through the scanner class in the getTopSongs method. the user is told to enter a number between 1 and 50, which represents the position of a song in the Spotify Top 50 playlist.

>Use of at least one list: The program uses two ArrayList objects, songUrls and songInfo, to store the Spotify URLs and information of the selected songs, respectively.

>At least one student-developed procedure: The getTopSongs method is a student-developed procedure that fetches the top 50 songs from a Spotify playlist and allows the user to access information about a song by its position in the playlist.

>An algorithm that includes sequencing, selection, and iteration: The getTopSongs method includes a while loop (iteration) that continues to prompt the user for more songs until they decide to stop. inside the loop, there’s an if-else statement (selection) that checks if the user’s 
>input is valid. the method also includes a sequence of operations to fetch the songs, get the song information, and add it to the list.

>calls to your student-developed procedure: The getTopSongs method is called in the main method.

>instructions for output: the program outputs the information of the selected songs and their Spotify URLs to the console using java's System.out.println(); function


ACKNOWLEDGEMENTS:


We used a couple outside resources in our program and We would like to give proper credit to these individuals:

Thelinmicheal - his Java wrapper for the spotify API.

>View his GitHub profile here: https://github.com/thelinmichael

This GitHub user basically powers this entire project, as we use the API's he wrote in Java for this main project.


Various users on stack overflow:

>IOException - https://stackoverflow.com/questions/5819121/understanding-java-ioexception

>try, catch - https://stackoverflow.com/questions/2535723/try-catch-on-stack-overflows-in-java

This came as a major help when attempting to debug, so this was also very big. 

Oracle:
>Java Docs in general - https://docs.oracle.com/en/java/

>InputMistmatchException - https://docs.oracle.com/javase/8/docs/api/java/util/InputMismatchException.html

>JButtons - https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html

Apache:

>ParseException - https://commons.apache.org/proper/commons-cli/apidocs/org/apache/commons/cli/ParseException.html

And of course:

Spotify Developer Docs 

>https://developer.spotify.com/documentation/web-api

@NoGambling1/@YesGambling for assistance w/ compiling & writing some of spotifytop50 and SpotifyAuthenticator 

>view their pages here: https://github.com/NoGambling1 and https://github.com/YesGambling respectively.

@S-lices for motivational and entertainment support

>View their page here: https://github.com/S-lices

AP teacher - will not be named for privacy protection

