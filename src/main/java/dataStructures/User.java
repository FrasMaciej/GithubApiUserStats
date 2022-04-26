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
    private ArrayList<Repository> repositoriesList = new ArrayList<>();

    //Summary User data
    private String login;
    private String name;
    private String bio;
    private ArrayList<ProgrammingLanguage> languagesList = new ArrayList<>();

    public void addRepository(Repository repository){
        repositoriesList.add(repository);
    }

    public void generateLanguagesList() {

    }

    public void printUserData(){
        System.out.println("\n");
        System.out.println("[Login]: " + login);
        System.out.println("[Name]: " + name);
        System.out.println("[Bio]: " + bio + "\n");

        if(repositoriesList.size() == 0 || repositoriesList == null){
            System.out.println("This user has no public repositories");
        }
        else {
            System.out.println("Repos" +
                    "itories list:");
            for (Repository repo: repositoriesList){
                System.out.println("[Project Name: " + repo.getName() + "]");
                for(ProgrammingLanguage lang: repo.getUsedLanguages()){
                    System.out.println(lang.getName() + " - " + lang.getBytesSize() + " bytes");
                }
            }
        }
    }

}
