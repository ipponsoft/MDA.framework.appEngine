package de.ipponsoft.appengine.client.jpa;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.ipponsoft.appengine.shared.jpa.interfaces.DTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("persistence")
public interface PersistenceService extends RemoteService {
	void save(DTO obj) throws IllegalArgumentException;
	void delete(DTO obj) throws IllegalArgumentException;
	List<DTO> readAll(String className) throws IllegalArgumentException;
}
