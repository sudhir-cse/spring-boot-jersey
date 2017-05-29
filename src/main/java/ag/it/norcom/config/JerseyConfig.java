package ag.it.norcom.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import ag.it.norcom.endpoint.EmailResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(EmailResource.class);
	}
}
