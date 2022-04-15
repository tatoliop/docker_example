package edu.auth.csd.datalab.db.utils.models;

import edu.auth.csd.datalab.db.utils.interfaces.MyDatabase;
import redis.clients.jedis.JedisPooled;

public class MyRedis implements MyDatabase {

    private JedisPooled redis;

    public MyRedis(String url, int port){
        redis = new JedisPooled(url, port);
    }

    public MyRedis(){
        redis = new JedisPooled("localhost", 6379);
    }

    @Override
    public String getData(String key) {
        return redis.get(key);
    }

    @Override
    public void putData(String key, String value) {
        redis.set(key, value);
    }

    @Override
    public void close() {
        redis.close();
        redis = null;
    }
}
