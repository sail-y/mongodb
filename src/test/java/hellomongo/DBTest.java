package hellomongo;

import com.mongodb.*;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DBTest {
    @Test
    public void testConnect() throws UnknownHostException {
        //Connect to MongoDB server.
        Mongo mongo = new Mongo("localhost", 27017);
    }

    @Test
    public void testGetDB() throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        //Get database. If the database doesn’t exist, MongoDB will create it for you.
        DB db = mongo.getDB("fandb");
    }
    @Test
    public void testDisplayDB() throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        //Display all databases.
        List<String> dbs = mongo.getDatabaseNames();
        for (String d : dbs) {
            System.out.println(d);
        }
    }

    @Test
    public void testGetCollcetion() throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("fandb");
        //Get collection / table.
        DBCollection table = db.getCollection("user");

        //Display all collections from selected database.
        Set<String> tables = db.getCollectionNames();

        for(String coll : tables){
            System.out.println(coll);
        }
    }

    @Test
    public void testSave() throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("fandb");
        // Save a document (data) into a collection (table) named “user”.
        DBCollection table = db.getCollection("user");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "yangfan");
        document.put("age", 30);
        document.put("createdDate", new Date());
        table.insert(document);
    }

    @Test
    public void testUpdate()throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("fandb");
        DBCollection table = db.getCollection("user");
        BasicDBObject query = new BasicDBObject();
        query.put("name", "yangfan");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "yangfan-updated");

        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set",newDocument);

        //Update a document where “name=yangfan”.
        table.update(query,updateObj);
    }

    @Test
    public void testFind()throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("fandb");

        //Find document where “name=yangfan”, and display it with DBCursor
        DBCollection table = db.getCollection("user");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name","yangfan");

        DBCursor cursor = table.find(searchQuery);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

    @Test
    public void testDelete()throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("fandb");

        DBCollection table = db.getCollection("user");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name","yangfan");
        table.remove(searchQuery);
    }

}
