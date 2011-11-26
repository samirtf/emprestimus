package iu.web.server.sistema.item;

import java.util.Comparator;
import java.util.Date;

public class NomeItemComparador implements Comparator<ItemIF> {

	@Override
	public int compare(ItemIF item1, ItemIF item2) {

		Date dataCriacaoItem1 = item1.getDataCriacao();
		Date dataCriacaoItem2 = item2.getDataCriacao();

		return dataCriacaoItem1.compareTo(dataCriacaoItem2);
	}

}
