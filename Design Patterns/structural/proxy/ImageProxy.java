package structural.proxy;

class ImageProxy implements Image {
    private String filename;
    private RealImage real;
    ImageProxy(String f) { filename = f; }
    public void display() {
        if (real == null) real = new RealImage(filename);
        real.display();
    }
}
