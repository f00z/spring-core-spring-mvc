package guru.springframework.services;

import guru.springframework.domain.DomainObject;

import java.util.*;

/**
 * Created by jt on 11/14/15.
 */
public abstract class AbstractMapService  {
    protected Map<Integer, DomainObject> domainMap;

    public AbstractMapService() {
        domainMap = new HashMap<>();
        loadDomainObjects();
    }

    public List<DomainObject> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public DomainObject getById(Integer id) {
        return domainMap.get(id);
    }

    public DomainObject saveOrUpdate(DomainObject domainObject) {
        if (domainObject != null){

            if (domainObject.getID() == null){
                domainObject.setID(getNextKey());
            }
            domainMap.put(domainObject.getID(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(Integer id) {
        domainMap.remove(id);
    }

    private Integer getNextKey(){
        return Collections.max(domainMap.keySet()) + 1;
    }

    protected abstract void loadDomainObjects();

}
