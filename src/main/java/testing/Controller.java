package testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataStructures.ProgrammingLanguage;
import dataStructures.Repository;
import dataStructures.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.net.http.HttpResponse.BodyHandlers;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
    HttpClient client = HttpClient.newHttpClient();


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

        ArrayList<Repository> repositoriesList = new ArrayList<>();
        ArrayList<ProgrammingLanguage> languagesList = new ArrayList<>();

        //setting login, bio and name from http response
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

        User user = new User();
        user.setLogin(login);
        user.setBio(bio);
        user.setName(name);

        System.out.println("Login: " + login);
        System.out.println("Bio: " + bio);
        System.out.println("Name: " + name);


        ArrayList<String> links = new ArrayList<>();
        var request2 = HttpRequest.newBuilder().uri(URI.create(baseUrl + username + "/repos"))
                .setHeader("Authorization", authorization)
                .GET()
                .build();

        var response2 = HttpClient.newHttpClient().send(request2, BodyHandlers.ofString());

        String stringResponse2 = response2.body();


        try
        {
            JSONArray jsonArray = new JSONArray(stringResponse2);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //System.out.println(jsonObject.toString());
                String path2 = jsonObject.getString("languages_url");
                String name2 = jsonObject.getString("name");

                var request3 = HttpRequest.newBuilder().uri(URI.create(path2))
                        .setHeader("Authorization", authorization)
                        .GET()
                        .build();

                var response3 = HttpClient.newHttpClient().send(request3, BodyHandlers.ofString());

                String stringResponse3 = response3.body();
                JSONObject jj3 = new JSONObject(stringResponse3);
                Iterator<String> keys = jj3.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    int lanSize = jj3.getInt(key);
                    System.out.println(lanSize);
                    System.out.println(key);
                }


                System.out.println(name2);
                System.out.println(path2);

            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        //ArrayList<ProgrammingLanguage> programmingLanguages = new ArrayList<>();
        //Repository repository = new Repository();
        //repositoriesList.add(repository);
        //user.setLanguagesList();

        return stringResponse;

        //return objectMapper.writeValueAsString(" Dane użytkownika '" + username + "' ");
        //tutaj api githuba
    }




}
















