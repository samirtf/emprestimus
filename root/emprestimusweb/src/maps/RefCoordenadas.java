package maps;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 */

public class RefCoordenadas implements Coordenadas {

	private double latitude, longitude;

	/**
	 * Determina as coordenadas do usuario
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public RefCoordenadas(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return longitude do usuario
	 */
	@Override
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * @return latitude do usuario
	 */
	@Override
	public double getLatitude() {
		return this.latitude;
	}
}
