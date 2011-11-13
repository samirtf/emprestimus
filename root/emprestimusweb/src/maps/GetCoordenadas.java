package maps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetCoordenadas {

	/**
	 * Faz a requisicao a API do google maps
	 * 
	 * @param endereco
	 * @return
	 */
	public static RefCoordenadas getCoordenadas(String endereco) {
		double latitude = 0;
		double longitude = 0;

		try {
			String url = String.format("%s%s%s",
					"http://maps.google.com/maps/api/geocode/json?address=", endereco,
					"&sensor=true");

			String source = requisicao(url);
			JSONObject object = new JSONObject(source);
			JSONArray a = (JSONArray) object.get("results");
			object = a.getJSONObject(0).getJSONObject("geometry").getJSONObject(
					"location");
			latitude = object.getDouble("lat");
			longitude = object.getDouble("lng");
		} catch (Exception e) {
			// Devolva as coordenadas zeradas
		}
		return new RefCoordenadas(latitude, longitude);
	}

	private static String requisicao(String url) {
		String source = "";
		url = url.replaceAll(" ", "%20");
		try {
			URL req = new URL(url);
			URLConnection connection = req.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));
			String buffer;
			while ((buffer = in.readLine()) != null)
				source = source.concat(buffer + "\n");
			in.close();
		} catch (Exception e) {
			// Devolve até onde foi lido
		}
		return source;
	}

}
