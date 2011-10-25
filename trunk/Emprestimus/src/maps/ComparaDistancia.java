package maps;

import java.util.Comparator;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 */

public class ComparaDistancia implements Comparator<Coordenadas> {

	private Coordenadas referencia;
	final static double raioTerra = 6371.0;

	/**
	 * Construtor da classe Compara distancia
	 * 
	 * @param referencia
	 */
	public ComparaDistancia(Coordenadas referencia) {
		this.referencia = referencia;
	}

	/**
	 * Calcula a distancia entre as coordenadas x e y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private double distancia(Coordenadas x, Coordenadas y) {
		double dLat = Math.toRadians(y.getLatitude() - x.getLatitude());
		double dLon = Math.toRadians(y.getLongitude() - x.getLongitude());
		double xLat = Math.toRadians(x.getLatitude());
		double yLat = Math.toRadians(y.getLatitude());
		double haversineA = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
				* Math.sin(dLon / 2) * Math.cos(xLat) * Math.cos(yLat);
		double haversineB = 2 * Math.atan2(Math.sqrt(haversineA), Math
				.sqrt(1 - haversineA));
		return raioTerra * haversineB;
	}

	/**
	 * Compara as coordenadas o1 e 02
	 */
	@Override
	public int compare(Coordenadas o1, Coordenadas o2) {
		double diferenca = distancia(o1, referencia) - distancia(o2, referencia);

		int retorno = 0;
		if (diferenca < 0)
			retorno = -1;
		if (diferenca > 0)
			retorno = 1;
		return retorno;
	}
}
