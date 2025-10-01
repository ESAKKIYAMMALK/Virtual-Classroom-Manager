package behavioral.mediator;

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
