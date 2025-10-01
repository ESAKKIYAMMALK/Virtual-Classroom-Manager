package behavioral.chain;

interface Handler {
    void setNext(Handler next);
    void handle(Request req);
}

class Request {
    String type;
    String payload;
    Request(String t, String p) { type = t; payload = p; }
}

class AuthHandler implements Handler {
    private Handler next;
    public void setNext(Handler next) { this.next = next; }
    public void handle(Request req) {
        if (req.type.equals("AUTH")) System.out.println("AuthHandler handled: " + req.payload);
        else if (next != null) next.handle(req);
        else System.out.println("Unhandled: " + req.type);
    }
}

class LoggingHandler implements Handler {
    private Handler next;
    public void setNext(Handler next) { this.next = next; }
    public void handle(Request req) {
        if (req.type.equals("LOG")) System.out.println("LoggingHandler handled: " + req.payload);
        else if (next != null) next.handle(req);
        else System.out.println("Unhandled: " + req.type);
    }
}

class DefaultHandler implements Handler {
    private Handler next;
    public void setNext(Handler next) { this.next = next; }
    public void handle(Request req) { System.out.println("Default handling: " + req.payload); }
}

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
