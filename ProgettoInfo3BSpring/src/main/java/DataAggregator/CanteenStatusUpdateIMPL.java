package DataAggregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import JSONParser.JSONParser;
import dataItemClasses.Apertura;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;
import dbConnection.dbConnectionSetter;

public class CanteenStatusUpdateIMPL implements CanteenStatusUpdateIF {

	// Connessione con mongoDB
	MongoCollection<Document> collection = dbConnectionSetter.connectToMongoCollection();

	@Override
	public boolean updateAvailableCapacity(int actualCapacity, Mensa mensa) {
		
		MongoCollection<Document> collection = dbConnectionSetter.connectToMongoCollection();
		
		//cerco l'oggetto su cui fare update (in questo caso mensa)
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("nome", mensa.getNome());
		
		//creo il filtro
		BasicDBObject putQuery = new BasicDBObject();
		putQuery.put("capacita", actualCapacity);
		
		//imposto la query
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.put("$set", putQuery);
		
		//faccio update
		Document result = collection.findOneAndUpdate(searchQuery, setQuery);
		
		return result != null;
	}

	@Override
	public boolean updateAvailablePortions(Dish dish, Menu dailyMenu, Mensa mensa, DettaglioApertura dettaglioApertura,
			Apertura apertura) {
		
		/*
		// seleziono la mensa che mi interessa
		Bson filterQuery = Filters.eq("nome", mensa.getNome());
		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// dichiaro gli indici che mi serviranno per costruire la stringa di query
		int indexArrayDA;
		int indexArrayA;
		int indexArrayMenu = 0;

		// mi assicuro di ricevere 1 solo risultato
		if (JSONParser.countQueryResults(queryRes) != 1)
			throw new RuntimeException();
		else {
			// oggetto mensa restituito dalla prima query su nomeMensa
			JSONObject objMensa = new JSONObject(queryRes.first().toJson());
			// prendo il JSONArray del JSONObject ritornato
			JSONArray arrayDettagli = objMensa.getJSONArray("dettaglioApertura");
			// imposto i filtri per la ricerca del dettaglioApertura di nostro interesse
			ArrayList<String> filterList = new ArrayList<String>(Arrays.asList("giornoSettimana",
					dettaglioApertura.getGiornoSettimana(), "tipoPasto", dettaglioApertura.getTipoPasto()));
			// restituisce map (indice arrayDettaglioApertura, JSONObject del
			// dettaglioApertura selezionato)
			Map<Integer, JSONObject> mapDA = JSONParser.filterIntoAndIndex(arrayDettagli, filterList);
			// setto il primo indice necessario per la costruzione della stringa di query
			// finale
			indexArrayDA = mapDA.keySet().iterator().next();
			// scendo di un ulteriore livello per prendere l'apertura corretta ->
			// -> prendo il JSONObject risultante dal filtering precedente
			JSONObject objDettagli = mapDA.get(indexArrayDA);
			// prendo il JSONArray del JSONObject ritornato
			JSONArray arrayAperture = objDettagli.getJSONArray("apertura");
			// imposto i filtri per la ricerca dell'apertura di nostro interesse
			filterList = new ArrayList<String>(Arrays.asList("data", apertura.getData().toString()));
			// restituisce map (indice arrayApertura, JSONObject dell'apertura selezionata)
			Map<Integer, JSONObject> mapA = JSONParser.filterIntoAndIndex(arrayAperture, filterList);
			// setto il secondo indice necessario per la costruzione della stringa di query
			// finale
			indexArrayA = mapA.keySet().iterator().next();

		}

		// preparazione searchQuery
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("nome", mensa.getNome());

		// preparazione campi JSON da inserire/aggiornare
		Document updatedPiatto = new Document();
		updatedPiatto.append("idPiatto", dish.getIdPiatto()).append("nomePiatto", dish.getNomePiatto())
				.append("tipoPiatto", dish.getTipoPiatto()).append("prezzo", dish.getPrezzo())
				.append("initialAvailability", dish.getInitialAvailability())
				.append("currentAvailability", dish.getCurrentAvailability());

		// preparazione e esecuzione setQuery
		String queryString = "dettaglioApertura.".concat(String.valueOf(indexArrayDA)).concat(".apertura.")
				.concat(String.valueOf(indexArrayA)).concat(".menu.").concat(String.valueOf(indexArrayMenu))
				.concat(".Piatti");
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.put("$set", new BasicDBObject(queryString, updatedPiatto));
		
		Document result = collection.findOneAndUpdate(searchQuery, setQuery);
		
		return result != null;
		*/
		return true;

	}

}
