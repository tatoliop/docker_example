package edu.auth.csd.datalab.db;


import edu.auth.csd.datalab.db.utils.models.MyIgnite;
import edu.auth.csd.datalab.db.utils.models.MyRedis;

import java.util.Random;

public class ContinuousQueries {

    final static String string_iter = "ITER";
    final static String string_item = "ITEMS";
    final static String string_ignite = "IGNITE_HOST";
    final static String string_redis = "REDIS_HOST";

    public static void main(String[] args) {
        //Read env variables
        int iterations = (System.getenv(string_iter) != null && Integer.parseInt(System.getenv(string_iter)) > 0) ? Integer.parseInt(System.getenv(string_iter)) : 100;
        int items = (System.getenv(string_item) != null && Integer.parseInt(System.getenv(string_item)) > 0) ? Integer.parseInt(System.getenv(string_item)) : 100;
        String igniteHost = (System.getenv(string_ignite) != null) ? System.getenv(string_ignite) : "HelloIgnite";
        String redisHost = (System.getenv(string_redis) != null) ? System.getenv(string_redis) : "HelloRedis";
        //Set random with specific seed
        Random rand = new Random(0);
        //Create stat vars
        long redis_time = 0;
        long ignite_time = 0;
        //Create db instances
        MyIgnite ignite = new MyIgnite(igniteHost, 10800);
        MyRedis redis = new MyRedis(redisHost, 6379);
        int counter = 0;
        do {
            for (int i = 0; i <= items; i++) {
                String key = "key_" + i;
                double valIgnite = rand.nextInt(100) + rand.nextDouble();
                double valRedis = rand.nextInt(100) + rand.nextDouble();
                long time1 = System.currentTimeMillis();
                ignite.putData(key, Double.toString(valIgnite));
                long time2 = System.currentTimeMillis();
                redis.putData(key, Double.toString(valRedis));
                long time3 = System.currentTimeMillis();
                ignite_time += (time2 - time1);
                redis_time += (time3 - time2);
            }
            System.out.println("Iteration: " + counter);

            counter += 1;
        } while (counter <= iterations);
        System.out.println("Ignite avg ingestion time: " + (ignite_time / iterations) + " ms");
        System.out.println("Redis avg ingestion time: " + (redis_time / iterations) + " ms");
        String key = "key_0";
        long time1 = System.currentTimeMillis();
        System.out.println("Ignite key: " + key + " - latest value: " + ignite.getData(key));
        long time2 = System.currentTimeMillis();
        System.out.println("Redis key: " + key + " - latest value: " + redis.getData(key));
        long time3 = System.currentTimeMillis();
        System.out.println("Ignite extraction time: " + (time2 - time1) + " ms");
        System.out.println("Redis extraction time: " + (time3 - time2) + " ms");
        //Close db instances
        ignite.close();
        redis.close();
    }

}
