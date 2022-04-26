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

public class Repository {
    private ArrayList<ProgrammingLanguage> usedLanguages = new ArrayList<>();
    private String name;

    public void addProgrammingLanguage(ProgrammingLanguage programmingLanguage){
        usedLanguages.add(programmingLanguage);
    }

}
