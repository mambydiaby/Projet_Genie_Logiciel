package com.flight_sharing.dao;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
/**
 * 
 * Implements Actions with elastic search methods.
 * Associate each Dao object with a type(flight,airplane,pilot...)
 *
 */
public class ActionDao extends BasicDao {
	/** entity's type */
	private String mainType;
	private final TransportClient client;

	public ActionDao(String mainType, TransportClient client) {
		this.mainType = mainType;
		this.client = client;

	}

	public List<String> getAll() throws Exception {
		SearchResponse response = client.prepareSearch(BasicDao.index).setTypes(mainType)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(matchAllQuery()).get();
		List<String> results = new ArrayList<String>();
		for (SearchHit searchHit : response.getHits().getHits()) {
			results.add(searchHit.getSourceAsString());
		}
		return results;
	}

	public String getById(String id) throws Exception {
		GetResponse response = client.prepareGet(BasicDao.index, mainType, id).get();
		return response.getSourceAsString();
	}

	public String delete(String id) throws Exception {
		DeleteResponse response = client.prepareDelete(BasicDao.index, mainType, id).get();
		return response.status().toString();
	}

	public String add(byte[] json, String id) throws Exception {
		IndexResponse response = client.prepareIndex(BasicDao.index, mainType, id).setSource(json).get();
		return response.status().toString();
	}

	public void update(String id, String attribute, String value) throws Exception {
		UpdateRequest updateRequest = new UpdateRequest(BasicDao.index, mainType, id)
				.doc(jsonBuilder().startObject().field(attribute, value).endObject());
		client.update(updateRequest).get();
	}

	public List<String> search(QueryBuilder queryBuilder) throws Exception {
		List<String> results = new ArrayList<String>();
		try {
			SearchResponse response = client.prepareSearch(BasicDao.index).setTypes(mainType)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(queryBuilder).setExplain(true).setSize(100)
					.setFrom(0).get();
			for (SearchHit searchHit : response.getHits().getHits()) {
				results.add(searchHit.getSourceAsString());
			}
		} catch (Exception e) {
			registerException(e);
		}
		return results;
	}

	/**
	 * Extra param from and size for elasticsearch configuration
	 */
	public List<String> search(QueryBuilder queryBuilder, int from, int size) throws Exception {
		List<String> results = new ArrayList<String>();
		try {
			SearchResponse response = client.prepareSearch(BasicDao.index).setTypes(mainType)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(queryBuilder).setExplain(true)
					.setSize(size).setFrom(from).get();
			for (SearchHit searchHit : response.getHits().getHits()) {
				results.add(searchHit.getSourceAsString());
			}
		} catch (Exception e) {
			registerException(e);
		}
		return results;
	}

	private static void registerException(Exception e) {
		Logger.getLogger(ActionDao.class.getName()).log(Level.SEVERE, null, e);
	}

}
