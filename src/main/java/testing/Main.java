package testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("""
                1.Hello, this is an application thanks to which you can check any GitHub user data:
                  Login, name, bio and list of repositories
                2.To get it, just write in your browsers:
                  http://localhost:8080/user_name/name
                3.In the field 'name' you should write selected user
                  The results will be written in the console text interface""");

        SpringApplication.run(Main.class, args);
    }
}