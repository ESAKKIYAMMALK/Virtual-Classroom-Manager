package structural.proxy;

class RealImage implements Image {
    private String filename;
    RealImage(String f) { filename = f; loadFromDisk(); }
    private void loadFromDisk() { System.out.println("Loading " + filename); }
    public void display() { System.out.println("Displaying " + filename); }
}
