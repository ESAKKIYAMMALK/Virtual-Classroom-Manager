package behavioral.chain;

class AuthHandler implements Handler {
    private Handler next;
    public void setNext(Handler next) { this.next = next; }
    public void handle(Request req) {
        if (req.type.equals("AUTH")) System.out.println("AuthHandler handled: " + req.payload);
        else if (next != null) next.handle(req);
        else System.out.println("Unhandled: " + req.type);
    }
}
