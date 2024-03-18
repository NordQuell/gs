package de.bo.geminm;

import java.sql.SQLException;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class Init {

	@Inject	 
	private MemRepo memRepo;
	
	@Inject
	private DbRepo dbRepo;
	
	void onStart(@Observes StartupEvent evt) throws SQLException {
		memRepo.init();
		dbRepo.initTable();
	}
	
}
