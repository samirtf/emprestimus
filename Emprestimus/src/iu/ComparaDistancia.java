package iu;

import java.util.Comparator;

public class ComparaDistancia implements Comparator<Coordenadas> {

	private Coordenadas referencia;
	final static double raioTerra = 6371.0; 
	
	public ComparaDistancia(Coordenadas referencia) {
		this.referencia = referencia;
	}
	
	 private double distancia(Coordenadas a, Coordenadas b) {
         double dLat = Math.toRadians(b.getLatitude()-a.getLatitude());
         double dLon = Math.toRadians(b.getLongitude()-a.getLongitude());
         double aLat = Math.toRadians(a.getLatitude());
         double bLat = Math.toRadians(b.getLatitude());
         double haversineA = Math.sin(dLat/2) * Math.sin(dLat/2) +
                                 Math.sin(dLon/2) * Math.sin(dLon/2) *
                                 Math.cos(aLat) * Math.cos(bLat);
         double haversineB = 2 * Math.atan2(Math.sqrt(haversineA), Math.sqrt(1-haversineA));
         return raioTerra * haversineB;
 }
 
	
	@Override
	public int compare(Coordenadas o1, Coordenadas o2) {
		double diferenca = distancia(o1, referencia) - distancia(o2, referencia);
		
		int retorno = 0;
		if (diferenca < 0) retorno = -1;
		if (diferenca > 0) retorno = 1;
		return retorno;
	}
	
	

}
