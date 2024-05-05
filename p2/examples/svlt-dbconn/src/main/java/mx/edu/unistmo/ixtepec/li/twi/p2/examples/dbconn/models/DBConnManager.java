package mx.edu.unistmo.ixtepec.li.twi.p2.examples.dbconn.models;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnManager {
  private final String uri;
  private final String dbName;
  
  public DBConnManager(String uri, String dbName) {
    this.uri = uri;
    this.dbName = dbName;
  }

  public MongoDatabase getConn() throws MongoException {
    ServerApi api = ServerApi.builder()
        .version(ServerApiVersion.V1)
        .build();
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(this.uri))
        .serverApi(api)
        .build();

    try (MongoClient client = MongoClients.create(settings)) {
        return client.getDatabase(this.dbName);
    }
  }

  public void closeConn(MongoClient client) {
    if (client != null) {
      client.close();
    }
  }
}
