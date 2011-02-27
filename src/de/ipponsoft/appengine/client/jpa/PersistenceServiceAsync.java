package de.ipponsoft.appengine.client.jpa;

import java.util.List;
import de.ipponsoft.appengine.shared.jpa.interfaces.DTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The client side stub for the RPC service.
 */
public interface PersistenceServiceAsync {
	void save(DTO obj, AsyncCallback<Void> callback);
	void delete(DTO obj, AsyncCallback<Void> callback);
	void readAll(String className, AsyncCallback<List<DTO>> callback);
}
