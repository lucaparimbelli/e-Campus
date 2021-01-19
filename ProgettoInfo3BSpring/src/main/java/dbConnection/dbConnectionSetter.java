package dbConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public final class dbConnectionSetter {

	public static MongoCollection<Document> connectToMongoCollection() {

		// lettura del file di config
		String mongoConnectionString = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src" + File.separator + "main" + File.separator
					+ "java" + File.separator + "mainProgram" + File.separator + "config.txt"));
			mongoConnectionString = reader.readLine();
			reader.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		// creazione uri per connessione
		MongoClientURI uri = new MongoClientURI(mongoConnectionString);

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");

		return collection;
	}

}
