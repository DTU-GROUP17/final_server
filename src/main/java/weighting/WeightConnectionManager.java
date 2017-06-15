package weighting;

import app.App;
import models.db.Weight;
import org.hibernate.Session;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WeightConnectionManager implements Runnable {

	private HashMap<String, WeightConnection> connections;

	public WeightConnectionManager() {
		this.connections = new HashMap<>();
	}

	@Override
	public void run() {

	}

	public void refresh(){
		List<Weight> weights;
		try (Session session = App.factory.openSession()) {
			weights = session.createQuery("FROM Weight").list();
		}
		for (Weight weight : weights){
			if (
				!this.connections.containsKey(weight.getUri())
				|| !this.connections.get(weight.getUri()).isRunning()
			){
				WeightConnection connection = new WeightConnection(weight.getUri(), weight);
				this.connections.put(weight.getUri(), connection);
				connection.start();
			}
		}
		List<String> uris = weights.stream().map(Weight::getUri).collect(Collectors.toList());
		for (String key : this.connections.keySet()){
			if (!uris.contains(key)){
				this.connections.get(key).stopRunning();
				this.connections.remove(key);
			}
		}
	}
}
