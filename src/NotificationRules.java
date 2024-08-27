import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotificationRules {
    private ArrayList<Pattern> _patterns;

    public NotificationRules(File rulesFile){
        _patterns = new ArrayList<Pattern>();
        if(rulesFile.exists()){
            try{
                Scanner rulesScanner = new Scanner(rulesFile);
                String ruleToAdd;
                while(rulesScanner.hasNextLine()){
                    ruleToAdd = rulesScanner.nextLine();
                    Pattern toAdd = Pattern.compile(ruleToAdd, Pattern.CASE_INSENSITIVE);
                    _patterns.add(toAdd);
                }
            }
            catch(Exception e){

            }

        }
    }
    public boolean IsMatch(String text){
        Matcher matcher;
        for(int i = 0; i < _patterns.size(); i++){
            matcher = _patterns.get(i).matcher(text);
            boolean matchFound = matcher.find();
            if(matchFound){
                return true;
            }
        }
        return false;
    }

}
