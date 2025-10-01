package creational.objectpool;

import java.util.ArrayList;
import java.util.List;

class PooledObject {
    private final int id;
    private boolean inUse = false;

    public PooledObject(int id) { this.id = id; }
    public int getId() { return id; }
    public boolean isInUse() { return inUse; }
    public void setInUse(boolean inUse) { this.inUse = inUse; }
}
