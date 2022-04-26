My email in recruitment process: maciejfrs@gmail.com

To perform the task i used JAVA with jdk 17 and Maven build automation tool, along with the spring boot framework and the lombok library.
Api was made in the REST architecture, along with the use of github api.

To run the application you need to have installed on your machine: Java and Maven.
After that needed steps are:
1. From the command prompt/terminal going to the catalogue in which the file pom.xml from this project is
2. Type command: mvn clean install
3. If everything is okay, type command: mvn spring-boot:run
4. After the run you should see welcome information:

	.Hello, this is an application thanks to which you can check any GitHub user data:
          Login, name, bio and list of repositories
        .To get it, just write in your browsers:
          http://localhost:8080/user_name/name
        .In the field 'name' you should write selected user
          The results will be written in the console text interface and in your browser