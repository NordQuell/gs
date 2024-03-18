package de.bo.geminm;

import java.sql.SQLException;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/Gemeindeschluessel")
public class Resource {

	@Inject
	private MemRepo memRepo;

	@Inject
	private DbRepo dbRepo;

	@GET
	@Path("/jdbc/{plz}/{ort}")
	public Response getEintragJdbc(@PathParam("plz") String plz, @PathParam("ort") String ort) throws SQLException {
		long start = System.nanoTime();
		String content = dbRepo.getGemeindeschluessel(plz, ort);
		long end = System.nanoTime();
		System.out.println("JDBC: " + (end - start));
		return Response.ok(content).build();

	}

	@GET
	@Path("/mem/{plz}/{ort}")
	public Response getEintragMem(@PathParam("plz") String plz, @PathParam("ort") String ort) throws SQLException {
		long start = System.nanoTime();
		String content = memRepo.getGemeindeschluessel(plz, ort);
		long end = System.nanoTime();
		System.out.println("Mem: " + (end - start));
		return Response.ok(content).build();
	}

	@GET
	@Path("/mem/currentMem")
	public void getMem() {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Total: " + (runtime.totalMemory() / 1024 / 1024));
		System.out.println("Free: " + (runtime.freeMemory() / 1024 / 1024));
		System.out.println("Max: " + (runtime.maxMemory() / 1024 / 1024));

		System.out.println("Current memory: " + (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024);
	}

}
