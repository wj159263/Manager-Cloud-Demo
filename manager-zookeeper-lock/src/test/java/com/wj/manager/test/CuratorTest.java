package com.wj.manager.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CuratorTest {
    // String ip = "127.0.0.1:2181,192.168.38.138:2181";
    static String ip = "127.0.0.1:2181";
    static int seesionTimeout = 5000; //seesion多久不用后超时断开
    public static void main(String[] args) {
        //重试策略：连接不上时，1秒内重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ip)
                .sessionTimeoutMs(seesionTimeout)
                .retryPolicy(retryPolicy)
                .build();
        try {
            curatorFramework.start();
            Stat stat1 = curatorFramework.checkExists().forPath("/test1");
            Stat stat = curatorFramework.checkExists().forPath("/test11111");
            //创建目录/master/lock
            //curatorFramework.create().forPath("/lock","aa".getBytes()); //父目录不存在，不能创建
            String string = new String(curatorFramework.getData().forPath("/master/test"));
            //父目录不存在，能创建.默认持久
           // curatorFramework.create().creatingParentsIfNeeded().forPath("/master/test","init".getBytes());
            curatorFramework.setData().forPath("/master/test","cccccccc13123".getBytes());
            String string1 = new String(curatorFramework.getData().forPath("/master/test"));
            //PERSISTENT:持久， EPHEMERAL:临时
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL).forPath("/afaaada/ab","init".getBytes());
            //curatorFramework.delete().forPath("/aa");
            //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/aaa");

            ExecutorService service = new ThreadPoolExecutor(3,4,5, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100));
            //异步
           /* curatorFramework.create().inBackground((curatorFramework1,curatorEvent)->{
                CuratorEventType type = curatorEvent.getType();
                String path = curatorEvent.getPath();
                System.out.println("path="+path);
                byte[] data = curatorEvent.getData();
                System.out.println("s1="+data);


            },service).forPath("/test11111","daaaa".getBytes());*/

           /* curatorFramework.delete().inBackground((v1,v2)->{
                CuratorEventType type = v2.getType();
                byte[] data = v2.getData();
                String path = v2.getPath();
                System.out.println("delete_path="+path);
            }).forPath("/test11111");*/
            System.out.println("eeeend======");

            Stat stat3 = curatorFramework.checkExists().forPath("/node7");
            System.out.println("1_node3=="+stat3 +"=="+(stat3==null));
            //监听
            PathChildrenCache cache = new  PathChildrenCache(curatorFramework,"/node7",true);
            Stat stat2 = curatorFramework.checkExists().forPath("/node7");
            System.out.println("2_node3=="+stat2 +"=="+(stat2==null));


            cache.getListenable().addListener(new PathChildrenCacheListener(){

                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                    System.out.println("监听。。。。。。。。。。。。。");
                    switch (event.getType()){
                        case CHILD_ADDED:
                                System.out.println("新增子节点:" + event.getData().getPath());
                                break;
                        case CHILD_UPDATED:
                                System.out.println("子节点数据变化:" + event.getData().getPath());
                                break;
                        case CHILD_REMOVED:
                                System.out.println("删除子节点:" + event.getData().getPath());
                                break;
                        default:
                                break;
                    }
                }
            });
            //POST_INITIALIZED_EVENT之后触发
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            Stat stat4 = curatorFramework.checkExists().forPath("/node7");
            System.out.println("3_node3=="+stat4 +"=="+(stat4==null));
            //curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/node7","aaaaa123".getBytes());
            //不要上面得生成/node7，withMode会联通父目录一起生成
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/node7/node7_1","aa3".getBytes());
            Stat stat5 = curatorFramework.checkExists().forPath("/node7");
            System.out.println("4_node3=="+stat5 +"=="+(stat5==null));
            Thread.sleep(30000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            curatorFramework.close();
        }

    }
}
