package structural.bridge;

public class BridgeDemo {
    public static void main(String[] args) {
        Shape circle1 = new Circle(new VectorRenderer(), 5);
        circle1.draw();
        Shape circle2 = new Circle(new RasterRenderer(), 7);
        circle2.draw();
    }
}
