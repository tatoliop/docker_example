package edu.auth.csd.datalab.db.utils.models;

import edu.auth.csd.datalab.db.utils.interfaces.MyDatabase;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

public class MyIgnite implements MyDatabase {

    private IgniteClient ignite;
    private ClientCache<String,String> cache;

    public MyIgnite(String url, int port) {
        ClientConfiguration cfg = new ClientConfiguration().setAddresses(url + ":" + port);
        ignite = Ignition.startClient(cfg);
        cache = ignite.cache("MyData");
    }

    public MyIgnite() {
        ClientConfiguration cfg = new ClientConfiguration().setAddresses("localhost:10800");
        ignite = Ignition.startClient(cfg);
        cache = ignite.cache("MyData");
    }

    public String getData(String key){
        return cache.get(key);
    }

    public void putData(String key, String value){
        cache.put(key, value);
    }

    public void close(){
        cache = null;
        ignite.close();
        ignite = null;
    }
}
