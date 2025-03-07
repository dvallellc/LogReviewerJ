import java.io.File;

public class Main {
    public static void main(String[] args) {
        String rulesFilePath;
        String emailCredsPath;
        String dbCredsPath;

        if(args.length < 2){
            rulesFilePath = "C:\\Users\\dan_v\\OneDrive\\Desktop\\cfg\\rules.txt";
            emailCredsPath = "C:\\Users\\dan_v\\OneDrive\\Desktop\\cfg\\emailcreds.txt";
            dbCredsPath = "C:\\Users\\dan_v\\OneDrive\\Desktop\\cfg\\dbcreds.txt";
        }
        else{
            rulesFilePath = args[0];
            emailCredsPath = args[1];
            dbCredsPath = args[2];
        }
        File rulesFile = new File(rulesFilePath);
        NotificationRules rules = new NotificationRules(rulesFile);
        DBWriter.Init(dbCredsPath);
        LogListener listener = new LogListener(514, 1024, rules,emailCredsPath);
        listener.StartThread();

    }
}