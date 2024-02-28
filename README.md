wsg gang

this is a basic spotify 'data scraper' of some sort that we cooked up in the basement yo.

the primary 'goal': utilize spotify's API to grab the top 50 songs on spotify, then allows the users to search through it. it gives the details of the songs when you're done.

code is written entirely in java with maven as an additon for dependencies building.



screenshot of example:


// update example

Java 20 is needed, however, in the future, we will plan on adding other Java version support.

instructions for proper download:

1. click on releases
2. download the .JAR file
<!> IMPORTANT: YOU NEED JDK 20 TO RUN THIS PROGRAM. YOU ALSO NEED A JRE, IF YOU DON'T KNOW WHERE TO GET ONE, LOOK IT UP <!>
3. execute the .jar file
4. enjoy


Collegeboard Create Performance Task Guidelines:

>instructions for input: the program uses instructions for input by taking the input from the user through a button click even in the gui, and it prompts the user to enter a number.

>use of at least one list: the program uses two ArrayList objects, songUrls and songInfo, to store the Spotify URLs and information of the selected songs, respectively.

>at least one student-developed procedure: the getTopSongs method is the student-developed procedure that we made that gets the top 50 songs from a Spotify playlist and allows the user to access information about a song by its position in the playlist at the current time.

>An algorithm that includes sequencing, selection, and iteration:  the getTopSongs method includes an algorithm that uses sequencing (the order of operations), selection (the try block), and iteration (the for loop) to complete its job.

>calls to your student-developed procedure: The getTopSongs method is called when the button is clicked.

>instructions for output: the program outputs the information of the selected songs and their Spotify URLs to their screen by printing it out using System.out.println()


ACKNOWLEDGEMENTS:


we used a couple outside resources in our program and We would like to give proper credit to these individuals:

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

