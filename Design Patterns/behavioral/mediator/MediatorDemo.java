package behavioral.mediator;

interface Mediator {
    void send(String from, String to, String msg);
}

class ConcreteMediator implements Mediator {
    private ColleagueA a;
    private ColleagueB b;
    public void registerA(ColleagueA a) { this.a = a; }
    public void registerB(ColleagueB b) { this.b = b; }
    public void send(String from, String to, String msg) {
        if (to.equals("A") && a != null) a.receive(from, msg);
        if (to.equals("B") && b != null) b.receive(from, msg);
    }
}

abstract class Colleague {
    protected Mediator med;
    Colleague(Mediator m) { med = m; }
}

class ColleagueA extends Colleague {
    ColleagueA(Mediator m) { super(m); }
    void sendMessage(String to, String msg) { med.send("A", to, msg); }
    void receive(String from, String msg) { System.out.println("A received from " + from + ": " + msg); }
}

class ColleagueB extends Colleague {
    ColleagueB(Mediator m) { super(m); }
    void sendMessage(String to, String msg) { med.send("B", to, msg); }
    void receive(String from, String msg) { System.out.println("B received from " + from + ": " + msg); }
}

public class MediatorDemo {
    public static void main(String[] args) {
        ConcreteMediator med = new ConcreteMediator();
        ColleagueA a = new ColleagueA(med);
        ColleagueB b = new ColleagueB(med);
        med.registerA(a);
        med.registerB(b);
        a.sendMessage("B", "Hello from A");
        b.sendMessage("A", "Hi A, this is B");
    }
}
