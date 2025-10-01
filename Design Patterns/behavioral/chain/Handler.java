package behavioral.chain;

interface Handler {
    void setNext(Handler next);
    void handle(Request req);
}
