package behavioral.chain;

public class ChainDemo {
    public static void main(String[] args) {
        Handler auth = new AuthHandler();
        Handler log = new LoggingHandler();
        Handler def = new DefaultHandler();
        auth.setNext(log);
        log.setNext(def);
        auth.handle(new Request("AUTH", "user login"));
        auth.handle(new Request("LOG", "user clicked"));
        auth.handle(new Request("OTHER", "something else"));
    }
}
