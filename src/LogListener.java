import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class LogListener {
    private boolean _running;
    private int _listeningPort;
    private int _bufferSize;
    private Emailer _emailer;

    private byte[] _buffer;

    private DatagramSocket _udpSocket;
    private NotificationRules _rules;
    /**
     *
     * @param port
     * @param bufferSize
     */
    public LogListener(int port, int bufferSize, NotificationRules rules, String emailCredPath) {
        _listeningPort = port;
        _bufferSize = bufferSize;
        _rules = rules;
        _emailer = new Emailer(emailCredPath);
        try {
            _udpSocket = new DatagramSocket(_listeningPort);
            _buffer = new byte[_bufferSize];
            _running = true;
        } catch (Exception e) {
            System.out.println(e.toString() + " " + e.getStackTrace());
        }
    }
    public void StartThread(){
        new Thread(this::Listen).start();
    }
    private void Listen(){
        System.out.println("Listening on port " + _listeningPort);
        DatagramPacket received = new DatagramPacket(_buffer, _bufferSize);
        while(_running){
            try{
                _udpSocket.receive(received);
                String receivedString = new String(received.getData());
                System.out.println(receivedString);
                String fromAddress = received.getAddress().getHostAddress();
                NotificationRule matchingRule = _rules.IsMatch(fromAddress, receivedString);
                if(matchingRule != null){
                    _emailer.SendMail("dan@danielvalle.net", matchingRule.GetSubject(),receivedString,fromAddress);
                }
            }
            catch(Exception e){
                System.out.println(e.toString() + " " + e.getStackTrace());
            }

        }
        System.out.println("Listener thread exiting.");
    }
}
