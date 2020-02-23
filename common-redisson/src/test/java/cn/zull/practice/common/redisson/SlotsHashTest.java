package cn.zull.practice.common.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RedissonApplication.class)
public class SlotsHashTest {
    @Autowired
    RedissonUtils redissonUtils;
    /**
     * 虚拟节点的数目，一个真实结点对应n个虚拟节点，设置虚拟节点使各节点数据分布更均匀
     */
    private static final int VIRTUAL_NODES = 2000;
    private final static String PREFIX = "##";
    private final TreeMap<Long, String> virtualNodes = new TreeMap<>();

    @Test
    public void t() {
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> counter = new HashMap<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");
        list.add("s6");
        SlotsHashTest test = new SlotsHashTest();
        list.forEach(test::addSlot);

        for (int i = 0; i < 10000; i++) {
            String slot = test.findSlot("key:" + i);
            if (counter.get(slot) == null) {
                counter.put(slot, 0);
            }
//            Integer count = counter.putIfAbsent(slot, 0);
            counter.put(slot, counter.get(slot) + 1);
        }
        System.out.println(counter);

    }


    public void addSlot(String slotName) {
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            String virtualNode = slotName + PREFIX + i;
            long hash = hash(virtualNode);
            if (virtualNodes.containsKey(hash)) {
                throw new RuntimeException("pls check slot repetitive");
            }
            virtualNodes.put(hash, virtualNode);
        }
    }

    public String findSlot(String key) {
        long hash = hash(key);
        String slotName = virtualNodes.get(hash);
        // 如果hash值巧合已有，直接使用此key,否则使用小于此hash值的最近一个key
        if (StringUtils.isEmpty(slotName)) {
            Map.Entry<Long, String> longStringEntry = virtualNodes.lowerEntry(hash);
            if (longStringEntry == null) {
                // 碰巧是最小的hash值，取第一个
                longStringEntry = virtualNodes.firstEntry();
            }
            slotName = longStringEntry.getValue();
        }
        return slotName.substring(0, slotName.indexOf(PREFIX));
    }

    /**
     * murmur hash算法实现
     */
    private long hash(byte[] key) {

        ByteBuffer buf = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    private long hash(String key) {
        return hash(key.getBytes());
    }


}
