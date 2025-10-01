package behavioral.chain;

class DefaultHandler implements Handler {
    private Handler next;
    public void setNext(Handler next) { this.next = next; }
    public void handle(Request req) { System.out.println("Default handling: " + req.payload); }
}
