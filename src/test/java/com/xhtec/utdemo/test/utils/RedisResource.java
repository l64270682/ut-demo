package com.xhtec.utdemo.test.utils;

import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

/**
 * @author hhy@100fen.cn
 */
public class RedisResource {

    private static RedisResource redisResource;

    private RedisServer redisServer;

    private boolean inSuite;

    public static final RedisResource resource() {
        if (redisResource == null) {
            synchronized (RedisResource.class) {
                redisResource = new RedisResource();
            }
        }
        return redisResource;
    }

    public void setup(boolean isInSuite) {
        if (redisServer == null) {
            synchronized (this) {
                if (redisServer == null) {
                    ProcessUtils.killByPort(Protocol.DEFAULT_PORT);
                    RedisServer.builder().reset();
                    redisServer = RedisServer.builder()
                            .port(Protocol.DEFAULT_PORT)
                            .build();
                    redisServer.start();

                    this.inSuite = isInSuite;
                }
            }
        }
    }

    public void cleanup() {
        if (!inSuite && redisServer != null) {
            RedisServer tm = redisServer;
            redisServer = null;
            tm.stop();
        }
    }

}
