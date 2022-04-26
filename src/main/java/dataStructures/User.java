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

    //Method that generate list of languages used in specified user repos
    //It calculates the sum of code bytes in each language
    public void generateLanguagesList() {
        for(Repository repo: repositoriesList){
            for (ProgrammingLanguage singleLang: repo.getUsedLanguages()){
                boolean lanFound = false;
                String lanName = singleLang.getName();
                int lanSize = singleLang.getBytesSize();
                for(int i=0; i<languagesList.size(); i++){
                    if(lanName.equals(languagesList.get(i).getName())){
                        languagesList.get(i).setBytesSize(languagesList.get(i).getBytesSize()+singleLang.getBytesSize());
                        lanFound = true;
                    }
                }
                if(!lanFound){
                    ProgrammingLanguage language = new ProgrammingLanguage(lanName, lanSize);
                    languagesList.add(language);
                }
            }
        }
    }

    //First point from the exercise - repositories list
    public void printUserData(){
        System.out.println("\n");
        if(repositoriesList.size() == 0 || repositoriesList == null){
            System.out.println("This user has no public repositories");
        }
        else {
            System.out.println("Repositories list:");
            int i=1;
            for (Repository repo: repositoriesList){
                System.out.println(i + " [Project Name: " + repo.getName() + "]");
                for(ProgrammingLanguage lang: repo.getUsedLanguages()){
                    System.out.println(lang.getName() + " - " + lang.getBytesSize() + " bytes");
                }
                i++;
            }
        }
    }

    //Second point from the exercise - user data with aggregated information about number of bytres in each language
    public void printUserTotalReposInfo(){
        System.out.println("[Login]: " + login);
        System.out.println("[Name]: " + name);
        System.out.println("[Bio]: " + bio + "\n");

        System.out.println("Total aomunt of code written by user: ");
        int i = 1;
        for(ProgrammingLanguage lan: languagesList){
            System.out.println("[" + i + "] " + lan.getName() + ", " + lan.getBytesSize() + " bytes");
            i++;
        }
    }

}
