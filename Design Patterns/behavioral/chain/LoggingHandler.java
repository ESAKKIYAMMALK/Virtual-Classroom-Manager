package behavioral.chain;

class LoggingHandler implements Handler {
    private Handler next;
    public void setNext(Handler next) { this.next = next; }
    public void handle(Request req) {
        if (req.type.equals("LOG")) System.out.println("LoggingHandler handled: " + req.payload);
        else if (next != null) next.handle(req);
        else System.out.println("Unhandled: " + req.type);
    }
}
