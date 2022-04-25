package dataStructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //User repositories
    private ArrayList<Repository> repositoriesList;

    //Summary User data
    private String login;
    private String name;
    private String bio;
    private ArrayList<ProgrammingLanguage> languagesList;

}
