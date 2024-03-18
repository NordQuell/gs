package de.bo.geminm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MemRepo {

	private Map<GemKey, String> contentMap = new HashMap<>();

	public void init() throws SQLException {
		for (int i = 0; i < 10000000; i++) {
			GemKey key = new GemKey(String.valueOf(i), "Ort " + i);
			String content = "{vkz: " + i + ", gemeindeschluessel: " + i + "}";
			contentMap.put(key, content);
		}
	}

	public String getGemeindeschluessel(String plz, String ort) throws SQLException {
		GemKey key = new GemKey(plz, ort);
		return contentMap.get(key);
	}

}
