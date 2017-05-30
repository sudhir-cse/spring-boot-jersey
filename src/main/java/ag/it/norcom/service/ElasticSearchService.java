package ag.it.norcom.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import ag.it.norcom.config.PropertiesConfig;

@Service
@PropertySource("app.properties")
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchService implements ISearchService {

	private String host;
	private int port;
	private List<String> indexList;        //multiple indexes to perform search upon
	private List<String> typeList;         //multiple types to perform search upon
	private int from;                      //search result range
	private int to;                        //search result range
	
	private TransportClient client;
	private PreBuiltTransportClient preBuiltTransportClient;

	public ElasticSearchService(PropertiesConfig propertiesConfig) {
		
		this.host = propertiesConfig.getHost();
		this.port = propertiesConfig.getPort();
		this.indexList = propertiesConfig.getIndexList();
		this.typeList = propertiesConfig.getTypeList();
		this.from = propertiesConfig.getFrom();
		this.to= propertiesConfig.getTo();
		
		this.client = this.getTransportClient();
	}

	/* (non-Javadoc)
	 * @see ag.it.norcom.service.ISearchService#search(java.lang.String)
	 */
	@Override
	public SearchResponse search(String queryTerm) {
		
		String[] indexArray = new String[this.indexList.size()];
		indexArray = this.indexList.toArray(indexArray);
		
		String[] typeArray = new String[this.typeList.size()];
		typeArray = this.typeList.toArray(typeArray);
		
		return client.prepareSearch(indexArray).setTypes(typeArray)
				.setQuery(QueryBuilders.simpleQueryStringQuery(queryTerm))
				.setFrom(this.from).setSize(this.to).setExplain(true)
				.get();

	}

	// Create client (if not already) and then returns it.
	private TransportClient getTransportClient() {

		if (client == null)
			try {
				preBuiltTransportClient = new PreBuiltTransportClient(Settings.EMPTY);
				client = preBuiltTransportClient
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(this.host), this.port));

			} catch (UnknownHostException e) {
				System.out.println("\n Exception <" + e.getClass().getSimpleName() + "> " + e.getMessage());
			}

		return client;
	}

	// Close client
	private void closeClient() {

		if (client != null)
			client.close();
	}

}
