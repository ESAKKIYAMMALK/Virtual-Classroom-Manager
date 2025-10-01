package structural.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Image img = new ImageProxy("photo.jpg");
        img.display();
        img.display();
    }
}
