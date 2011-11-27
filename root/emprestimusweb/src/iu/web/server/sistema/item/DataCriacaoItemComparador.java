package iu.web.server.sistema.item;

import java.io.Serializable;
import java.util.Date;
import java.util.Comparator;

public class DataCriacaoItemComparador implements Comparator<ItemIF>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2292400569127188109L;

	@Override
	public int compare(ItemIF item1, ItemIF item2) {

		Date dataCriacaoItem1 = item1.getDataCriacao();
		Date dataCriacaoItem2 = item2.getDataCriacao();

		return dataCriacaoItem1.compareTo(dataCriacaoItem2);
	}

}
