package utils.comparator;

import beans.GenericCatalog;

import java.util.Comparator;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public class GenericCatalogComparator implements Comparator<GenericCatalog> {
    @Override
    public int compare(GenericCatalog o1, GenericCatalog o2) {
        if (o1.getOrder() == null || o2.getOrder() == null) return 0;
        return o1.getOrder().compareTo(o2.getOrder());
    }
}
