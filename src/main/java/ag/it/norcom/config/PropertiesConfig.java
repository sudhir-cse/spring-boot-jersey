package ag.it.norcom.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("app.properties")
@ConfigurationProperties(prefix = "elasticsearch")
public class PropertiesConfig {

	// Elastic-search
	private String host;
	private int port;
	private List<String> indexList;
	private List<String> typeList;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<String> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<String> indexList) {
		this.indexList = indexList;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
}
