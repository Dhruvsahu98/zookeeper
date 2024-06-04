package org.rupesh.utils;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.rupesh.NoLockQueue;
import org.rupesh.LockQueue;
import org.rupesh.interfaces.Queue;

public class QueueFactory {
    private ZooKeeper zk;
    private String queuePath;

    public QueueFactory(ZooKeeper zk, String queuePath) {
        this.zk = zk;
        this.queuePath = queuePath;
    }

    public Queue GetQueue(LockType lockType) throws InterruptedException, KeeperException {
        if(LockType.Lock == lockType){
            return new LockQueue(this.zk, this.queuePath);
        }
        return new NoLockQueue(this.zk, this.queuePath);
    }
}
