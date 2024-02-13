hi!!!


this is a basic spotify 'data scraper' of some sort.

the primary 'goal': utilize spotify's API to grab the top 200 songs on spotify, then allows the users to search through it.

code is written entirely in java with maven.



screenshots of code:

main method

![image](https://github.com/LQ84i-1/spot200/assets/155986030/fd9325bf-bc44-4e0c-a26b-b15ee43ab5ff)


pom.xml

<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  
  <modelVersion>4.0.0</modelVersion>
  
  <groupId>com.shivenanddaniel</groupId>
  <artifactId>spotifytop200</artifactId>
  <version>1.0</version>
  
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
  
  <dependencies>
    <dependency>
      <groupId>se.michaelthelin.spotify</groupId>
      <artifactId>spotify-web-api-java</artifactId>
      <version>6.5.2</version>
    </dependency>
  </dependencies>
  
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
      </plugin>
    </plugins>
  </build>
  
</project>



