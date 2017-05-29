package ag.it.norcom.service;

import org.elasticsearch.action.search.SearchResponse;

public interface ISearchService {

	SearchResponse search(String query);

}