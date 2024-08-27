import java.io.File;

public class Main {
    public static void main(String[] args) {
        String rulesFilePath;

        if(args.length < 1){
            rulesFilePath = "C:\\Users\\dan_v\\OneDrive\\Desktop\\cfg\\rules.txt";
        }
        else{
            rulesFilePath = args[0];
        }
        File rulesFile = new File(rulesFilePath);
        NotificationRules rules = new NotificationRules(rulesFile);

        LogListener listener = new LogListener(514, 1024, rules);
        listener.StartThread();

    }
}