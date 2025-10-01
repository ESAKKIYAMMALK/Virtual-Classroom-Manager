package creational.objectpool;

import java.util.ArrayList;
import java.util.List;

class ObjectPool {
    private final List<PooledObject> pool = new ArrayList<>();

    public ObjectPool(int size) {
        for (int i = 0; i < size; i++) {
            pool.add(new PooledObject(i));
        }
    }

    public synchronized PooledObject acquire() {
        for (PooledObject obj : pool) {
            if (!obj.isInUse()) {
                obj.setInUse(true);
                return obj;
            }
        }
        PooledObject extra = new PooledObject(pool.size());
        extra.setInUse(true);
        pool.add(extra);
        return extra;
    }

    public synchronized void release(PooledObject obj) {
        obj.setInUse(false);
    }
}
