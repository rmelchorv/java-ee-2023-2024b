package mx.edu.unistmo.ixtepec.li.twi.p2.examples.dbconn.models;

import java.util.Locale;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DBConnManager {
  private final String uri;

  public DBConnManager(String uri, String pwd) {
    this.uri = String.format(Locale.ROOT, uri, pwd);
  }

  public MongoClient getConn() throws MongoException {
    try (MongoClient client = MongoClients.create(this.uri)) {
        return client;
    }
  }

  public void closeConn(MongoClient client) {
    if (client != null) {
      client.close();
    }
  }
}
