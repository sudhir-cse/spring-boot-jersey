package ag.it.norcom.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("app.properties")
@ConfigurationProperties(prefix = "elasticsearch")
public class PropertiesConfig {

	//Elastic-search
	private String host;
	private int port;
	private List<String> indexList;
	private List<String> typeList;
	private int from;
	private int to;

	public int getFrom() {
		return from;
	}

	public String getHost() {
		return host;
	}

	public List<String> getIndexList() {
		return indexList;
	}

	public int getPort() {
		return port;
	}

	public int getTo() {
		return to;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setIndexList(List<String> indexList) {
		this.indexList = indexList;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
}
