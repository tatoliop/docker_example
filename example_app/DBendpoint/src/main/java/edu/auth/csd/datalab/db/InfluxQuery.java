package edu.auth.csd.datalab.db;

import com.github.nickrm.jflux.JFluxClient;
import com.github.nickrm.jflux.domain.Point;

import java.util.Collections;
import java.util.Random;

public class InfluxQuery {
    //Write data to influx (Influx/Grafana example)
    public static void main(String[] args) throws Exception {
        Random rand = new Random(0);
        String mydb = "my_db";
        JFluxClient client = new JFluxClient.Builder("http://localhost:8086").build();
        if(!client.databaseExists(mydb)) client.createDatabase(mydb);
        client.useDatabase(mydb);
        do {
            // Write points
            Point point = new Point.Builder()
                    .fields(Collections.singletonMap("some_field_name", rand.nextInt(100)))
                    .build();
            client.writePoint("my_measurement", point);
        }while (true);
    }

}
