package christmasPastryShop.repositories.interfaces;

public interface DelicacyRepository<T> extends Repository<T> {

    Object getByName(String name);

}
