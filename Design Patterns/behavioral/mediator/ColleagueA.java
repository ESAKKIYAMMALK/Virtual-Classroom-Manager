package behavioral.mediator;

class ColleagueA extends Colleague {
    ColleagueA(Mediator m) { super(m); }
    void sendMessage(String to, String msg) { med.send("A", to, msg); }
    void receive(String from, String msg) { System.out.println("A received from " + from + ": " + msg); }
}
