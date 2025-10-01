package behavioral.mediator;

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
