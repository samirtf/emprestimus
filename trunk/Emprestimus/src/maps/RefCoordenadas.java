package maps;

import iu.Coordenadas;

public class RefCoordenadas implements Coordenadas {

	private double latitude, longitude;
	
	public RefCoordenadas(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}
}
