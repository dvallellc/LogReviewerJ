import java.io.File;

public class Main {
    public static void main(String[] args) {
        String rulesFilePath;
        String emailCredsPath;
        String dbString = "";
        String dbUser = "";
        String dbPass = "";

        if(args.length < 2){
            rulesFilePath = "C:\\Users\\dan_v\\OneDrive\\Desktop\\cfg\\rules.txt";
            emailCredsPath = "C:\\Users\\dan_v\\OneDrive\\Desktop\\cfg\\emailcreds.txt";
        }
        else{
            rulesFilePath = args[0];
            emailCredsPath = args[1];
            dbString = args[2];
            dbUser = args[3];
            dbPass = args[4];
        }
        File rulesFile = new File(rulesFilePath);
        NotificationRules rules = new NotificationRules(rulesFile);
        DBWriter.Init(dbString, dbUser, dbPass);
        LogListener listener = new LogListener(514, 1024, rules,emailCredsPath);
        listener.StartThread();

    }
}