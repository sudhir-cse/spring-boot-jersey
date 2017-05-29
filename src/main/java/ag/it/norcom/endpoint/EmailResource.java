package ag.it.norcom.endpoint;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import ag.it.norcom.service.ISearchService;

@Component
@Path("/emails")
public class EmailResource {

	private ISearchService searchService;
	
	public EmailResource(ISearchService searchService){
		this.searchService = searchService;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String findEmails(@QueryParam("search") @NotNull String searchTerm){
		return this.searchService.search(searchTerm).toString();
	}
}
