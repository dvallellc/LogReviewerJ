import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotificationRules {
    private ArrayList<NotificationRule> _rules;

    public NotificationRules(File rulesFile){
        _rules = new ArrayList<NotificationRule>();
        if(rulesFile.exists()){
            try{
                Scanner rulesScanner = new Scanner(rulesFile);
                while(rulesScanner.hasNextLine()){
                    _rules.add(new NotificationRule(rulesScanner.nextLine()));
                }
            }
            catch(Exception e){

            }
        }
    }
    public NotificationRule IsMatch(String source, String text){
        for(int i = 0; i < _rules.size(); i++){
            NotificationRule toCheck = _rules.get(i);
            if(toCheck.IsMatch(source, text)){
                return toCheck;
            }
            else{
                //System.out.println("NOT A MATCH");
            }
        }
        return null;
    }

}
