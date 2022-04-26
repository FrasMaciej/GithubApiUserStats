package testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataStructures.ProgrammingLanguage;
import dataStructures.Repository;
import dataStructures.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse.BodyHandlers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Iterator;

@RestController
public class Controller {
    HttpClient client = HttpClient.newHttpClient();

    private static final String authorization = "Bearer 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";
    private static final String baseUrl = "https://api.github.com/users/";

    ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/user_name/{username}")
    public String getUser(@PathVariable("username") String username, User user) throws IOException, InterruptedException {

        var basicRequest = HttpRequest.newBuilder().uri(URI.create(baseUrl + username))
                .setHeader("Authorization", authorization)
                .GET()
                .build();

        var basicResponse = HttpClient.newHttpClient().send(basicRequest, BodyHandlers.ofString());

        String stringBasicResponse = basicResponse.body();
        JSONObject jjBasicResponse = new JSONObject(stringBasicResponse);

        //setting login, bio and name from http response
        if(user == null) {
            user = new User();
        }
        String login = jjBasicResponse.getString("login");
        user.setLogin(login);

        String bio;
        String name;

        if(jjBasicResponse.isNull("bio")){
            bio="";
        }
        else {
            bio = jjBasicResponse.getString("bio");
        }
        user.setBio(bio);

        if(jjBasicResponse.isNull("name")){
            name="";
        }
        else {
            name = jjBasicResponse.getString("name");
        }
        user.setName(name);

        //Getting an access to user repositories
        var userReposRequest = HttpRequest.newBuilder().uri(URI.create(baseUrl + username + "/repos"))
                .setHeader("Authorization", authorization)
                .GET()
                .build();

        var userReposResponse = HttpClient.newHttpClient().send(userReposRequest, BodyHandlers.ofString());
        String stringUserReposResponse = userReposResponse.body();

        //Setting repositories user data
        Controller.getUserRepos(user, stringUserReposResponse);
        user.generateLanguagesList();
        user.printUserTotalReposInfo();
        user.printUserData();

        return stringBasicResponse;
    }

    public static void getUserRepos(User user, String stringUserReposResponse) throws IOException, InterruptedException {
        try {
            JSONArray jsonArray = new JSONArray(stringUserReposResponse);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String path = jsonObject.getString("languages_url");
                String name = jsonObject.getString("name");

                var request = HttpRequest.newBuilder().uri(URI.create(path))
                        .setHeader("Authorization", authorization)
                        .GET()
                        .build();

                var response3 = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

                Repository repository = new Repository();
                repository.setName(name);

                String stringResponse3 = response3.body();
                JSONObject jj = new JSONObject(stringResponse3);
                Iterator<String> keys = jj.keys();

                while(keys.hasNext()) {
                    String key = keys.next();
                    int lanSize = jj.getInt(key);
                    ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(key, lanSize);
                    repository.addProgrammingLanguage(programmingLanguage);
                }

                user.addRepository(repository);
            }
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
















