package is_project3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class ListAllFollowers extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		@SuppressWarnings("unchecked")
		LinkedList<Map<String, Object>> recordSet = ((LinkedList<Map<String, Object>>) message.getPayload());
		
		String res = "";
		for(Iterator<Map<String, Object>> j = recordSet.iterator(); j.hasNext();)
		{
			Map<String, Object> row = j.next();
			
			res += "Email: " + (String)row.get("email") + ", Brand: " + (String)row.get("brand") + ", Confirmed: " + Integer.toString((int)row.get("confirmed")) + "\n";
			
		}
		
		if(res.equals("")) {
			return "No Followers on the database !!!";
		}
		else {
			return res;
		}
	}

}
