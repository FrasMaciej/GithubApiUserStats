package testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.net.http.HttpResponse.BodyHandlers;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

/*
@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }

    @GetMapping("/hello2")
    @ResponseBody
    public String hello2(@RequestParam(name="name", required = false) String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello3/{name}")
    @ResponseBody
    public String hello3(@PathVariable("name") String name) {
        return "Hello " + name;
    }
}
*/

@RestController
public class Controller {
    private static final String authorization = "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";
    private static final String baseUrl = "https://api.github.com/users/";

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/user_name/{username}")
    public String getUser(@PathVariable("username") String username) throws IOException, InterruptedException {

        var request = HttpRequest.newBuilder().uri(URI.create(baseUrl + username))
                .setHeader("Authorization", authorization)
                .GET()
                .build();

        var response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

        String stringResponse = response.body();
        JSONObject jj = new JSONObject(stringResponse);
        String login = jj.getString("login");
        String bio;
        String name;

        if(jj.isNull("bio")){
            bio="";
        }
        else {
            bio = jj.getString("bio");
        }

        if(jj.isNull("name")){
            name="";
        }
        else {
            name = jj.getString("name");
        }


        System.out.println("Login: " + login);
        System.out.println("Bio: " + bio);
        System.out.println("Name: " + name);



        return stringResponse;



        //return objectMapper.writeValueAsString(" Dane użytkownika '" + username + "' ");
        //tutaj api githuba
    }




}
















