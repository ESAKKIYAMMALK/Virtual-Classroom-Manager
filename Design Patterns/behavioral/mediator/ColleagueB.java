package behavioral.mediator;

class ColleagueB extends Colleague {
    ColleagueB(Mediator m) { super(m); }
    void sendMessage(String to, String msg) { med.send("B", to, msg); }
    void receive(String from, String msg) { System.out.println("B received from " + from + ": " + msg); }
}
