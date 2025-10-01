package creational.abstractfactory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory;
        factory = new WindowsFactory();
        factory.createButton().paint();
        factory.createCheckbox().paint();
        factory = new MacFactory();
        factory.createButton().paint();
        factory.createCheckbox().paint();
    }
}
