package org.rupesh.tester;

import org.apache.zookeeper.ZooKeeper;
import org.rupesh.NoLockQueue;
import org.rupesh.interfaces.Queue;
import org.rupesh.utils.Constants;

public class Producer {
    public static void main(String[] args) throws Exception {
        // Connect to the ZooKeeper instance running on localhost:2181
        ZooKeeper zk = new ZooKeeper(Constants.zookeeperUrl, 20000, null);

        // Create a distributed queue and enqueue items
        Queue queue = new NoLockQueue(zk, Constants.queuePath);
        int i = 0;
        while (i < Integer.MAX_VALUE){
            System.out.println("going to produce item"+i);
            queue.enqueue("item"+i);
            Thread.sleep(1000);
            i++;
        }

        // Close the ZooKeeper connection
        zk.close();
    }
}
