package cn.zull.practice.common.zk.test;

import cn.zull.practice.common.basis.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zurun
 * @date 2019/8/15 11:07:49
 */
@Slf4j
public class ZkTest {
    final static String HOST = "192.168.85.25:2181";
    final static String PATH = "/zr/z3/z4";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch connectedSignal = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(HOST, 5_000, event -> {
            log.info("path: " + event.getPath() + " ;state: " + event.getState() + " ; type: " + event.getType());
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                connectedSignal.countDown();
            }
        });
        connectedSignal.await();
        final Stat stat = zooKeeper.exists(PATH, getWatcher(zooKeeper));
        if (stat == null) {
            zooKeeper.create(PATH, "testData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            int version = stat.getVersion();
            System.out.println("version:" + version);
            byte[] data = zooKeeper.getData(PATH, false, stat);
            log.info("【data】:" + new String(data));

            zooKeeper.setData(PATH, "testData12".getBytes(), stat.getVersion());
        }

        Thread.sleep(10_000L);
        zooKeeper.close();


    }

    private static void listener(ZooKeeper zooKeeper, Stat stat) {
        try {
            byte[] data = zooKeeper.getData(PATH, true, stat);
            log.info("【变更后】 data:{}", new String(data));
            Thread.sleep(1000L);
            zooKeeper.setData(PATH, String.valueOf(RandomUtils.randomNumber(100)).getBytes(), stat.getVersion());

        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    private static Watcher getWatcher(ZooKeeper zooKeeper) {
        return event -> {
            log.info("【zk变更】 state:{} type:{}", event.getState(), event.getType());
            if (Watcher.Event.EventType.NodeDataChanged == event.getType()) {
                try {
                    Stat stat = zooKeeper.exists(PATH, getWatcher(zooKeeper));
                    listener(zooKeeper,stat);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
    }
}
