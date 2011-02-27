package de.ipponsoft.appengine.server.jpa;

import java.util.ArrayList;
import java.util.List;

import de.ipponsoft.appengine.client.jpa.PersistenceService;
import de.ipponsoft.appengine.shared.jpa.interfaces.DTO;
import de.ipponsoft.appengine.shared.jpa.interfaces.PO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class PersistenceServiceImpl extends RemoteServiceServlet implements
		PersistenceService {

	public void save(DTO obj) throws IllegalArgumentException {

		PO pObj;
		try {
			pObj = (PO) Class.forName(obj.persistentClassName()).newInstance();
			pObj.fromDTO(obj);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		javax.persistence.EntityManager em = de.ipponsoft.appengine.server.jpa.EMF.getManager();
		try {
			em.getTransaction().begin();
			em.persist(pObj);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new IllegalArgumentException("Oops!?", e);
		} finally {
			em.close();
		}
		
	}

	public void delete(DTO obj) throws IllegalArgumentException {

		javax.persistence.EntityManager em = de.ipponsoft.appengine.server.jpa.EMF.getManager();
		try {
			Object xobj = 
				em.find(
					Class.forName(obj.persistentClassName()), 
					com.google.appengine.api.datastore.KeyFactory.stringToKey(obj.getId()));
			em.getTransaction().begin();
			em.remove(xobj);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new IllegalArgumentException(e);
		} finally {
			em.close();
		}
		
	}

	public List<DTO> readAll(String className) throws IllegalArgumentException {

		List<DTO> ret = new ArrayList<DTO>();
		
		javax.persistence.EntityManager em = de.ipponsoft.appengine.server.jpa.EMF.getManager();
		try {
			@SuppressWarnings("unchecked")
			List<PO>  res = 
				(List<PO>) em.createQuery("SELECT x FROM " + className + " x").getResultList();
			
			for(PO elem : res) {
				ret.add(elem.asDTO());
			}
			return ret;

		} finally {
			em.close();
		}
		
	}

}
