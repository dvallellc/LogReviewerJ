import java.util.Objects;
import java.util.regex.Pattern;

public class NotificationRule {
    private String _from;
    private String _subject;

    private Pattern _pattern;

    public String GetSubject(){
        return _subject;
    }
    public NotificationRule(String lineToProcess){
        String[] splitLine = lineToProcess.split("~_~");
        if(splitLine.length == 3){
            _from = splitLine[0];
            _pattern = Pattern.compile(splitLine[1], Pattern.CASE_INSENSITIVE);
            _subject = splitLine[2];
        }
    }

    public boolean IsMatch(String source, String text){
        if((Objects.equals(source, _from)) || (Objects.equals(_from, "*"))){
            if(_pattern.matcher(text).find()){
                System.out.println("Match: " + text);
                return true;
            }
        }
        return false;
    }
}
