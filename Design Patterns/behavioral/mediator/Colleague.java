package behavioral.mediator;

abstract class Colleague {
    protected Mediator med;
    Colleague(Mediator m) { med = m; }
}
