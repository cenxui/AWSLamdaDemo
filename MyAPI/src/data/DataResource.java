package data;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

/**
 * 
 * @author cenxui
 * 2016/11/12
 */
public final class DataResource {
	private static final String TABLE_NAME = "Album";
	
	private final DynamoDB dynamoDB;
	private final Table table;
	
	static class Resource {
		static DataResource instance = new DataResource();
	}
	
	private DataResource() {
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();

		// Modify the client so that it accesses Tokyo region.
		client.withRegion(Regions.AP_NORTHEAST_1);
		this.dynamoDB = new DynamoDB(client);
		
		this.table = this.dynamoDB.getTable(TABLE_NAME);
		
	}
	
	public static DataResource getInstance() {
		return Resource.instance;
	}
	
	public String putItem(String primaryKey, String date) {
		Item item = new Item();
		item.withPrimaryKey("mail",primaryKey);
		item.withString("date", date);
		
		return table.putItem(item).toString();
		
	}
	
	public String getItem(String mail) {
		GetItemSpec spec = new GetItemSpec()
				.withPrimaryKey("mail", mail)
				.withConsistentRead(true);
		
		Item item = this.table.getItem(spec);
		return item.toJSON();
	}
}
