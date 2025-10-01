package structural.bridge;

class Circle extends Shape {
    private int radius;
    public Circle(Renderer renderer, int r) { super(renderer); this.radius = r; }
    public void draw() { renderer.renderCircle(radius); }
}
