package iu.web.server.sistema.item;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class NomeItemComparador implements Comparator<ItemIF>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6624206338674433254L;

	@Override
	public int compare(ItemIF item1, ItemIF item2) {

		Date dataCriacaoItem1 = item1.getDataCriacao();
		Date dataCriacaoItem2 = item2.getDataCriacao();

		return dataCriacaoItem1.compareTo(dataCriacaoItem2);
	}

}
