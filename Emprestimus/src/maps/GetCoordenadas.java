package maps;

import java.io.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class GetCoordenadas {

	public RefCoordenadas getCoordenadas(String endereco) {
		double latitude = 0;
		double longitude = 0;
		
		try {
			String url = String.format("%s%s%s",
					"http://maps.google.com/maps/api/geocode/json?address=",
					endereco, "&sensor=true");
			
			String source = requisicao(url);
			JSONObject object = new JSONObject(source);
			object = object.getJSONObject("results");
			object = object.getJSONObject("geometry");
			object = object.getJSONObject("location");
			latitude = object.getDouble("lat");
			longitude = object.getDouble("lng");
		} catch (Exception e) {

		}
		return new RefCoordenadas(latitude, longitude);
	}

	private String requisicao(String url) {
		String source = "";
		try {
			URL req = new URL(url);
			URLConnection connection = req.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String buffer;
			while ((buffer = in.readLine()) != null)
				source = source.concat(buffer + "\n");
			in.close();
		} catch (Exception e) {

		}
		return source;
	}

}
