package creational.objectpool;

import java.util.ArrayList;
import java.util.List;

public class ObjectPoolDemo {
    public static void main(String[] args) {
        ObjectPool pool = new ObjectPool(2);
        PooledObject o1 = pool.acquire();
        System.out.println("Acquired: " + o1.getId());
        PooledObject o2 = pool.acquire();
        System.out.println("Acquired: " + o2.getId());
        PooledObject o3 = pool.acquire();
        System.out.println("Acquired (extra): " + o3.getId());
        pool.release(o2);
        System.out.println("Released: " + o2.getId());
        PooledObject o4 = pool.acquire();
        System.out.println("Acquired after release: " + o4.getId());
    }
}
