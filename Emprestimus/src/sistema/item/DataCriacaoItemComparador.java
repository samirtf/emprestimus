package sistema.item;

import java.util.Date;
import java.util.Comparator;

public class DataCriacaoItemComparador implements Comparator<ItemIF> {

	@Override
	public int compare(ItemIF item1, ItemIF item2) {

		Date dataCriacaoItem1 = item1.getDataCriacao();
		Date dataCriacaoItem2 = item2.getDataCriacao();

		return dataCriacaoItem1.compareTo(dataCriacaoItem2);
	}

}
