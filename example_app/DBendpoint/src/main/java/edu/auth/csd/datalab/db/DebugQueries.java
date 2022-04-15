package edu.auth.csd.datalab.db;

import edu.auth.csd.datalab.db.utils.models.MyIgnite;
import edu.auth.csd.datalab.db.utils.models.MyRedis;

public class DebugQueries {
    public static void main(String[] args){
        MyIgnite ignite = new MyIgnite();
        MyRedis redis = new MyRedis();
        ignite.putData("test","1");
        redis.putData("test", "2");
        System.out.println(ignite.getData("test"));
        System.out.println(redis.getData("test"));
        ignite.close();
        redis.close();
    }

}
