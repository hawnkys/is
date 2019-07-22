package is_project3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class GetUsers extends AbstractMessageTransformer {
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		@SuppressWarnings("unchecked")
		LinkedList<Map<String, Object>> recordSet = ((LinkedList<Map<String, Object>>) message.getPayload());
		
		ArrayList<String> userMails = new ArrayList<>();
		for(Iterator<Map<String, Object>> j = recordSet.iterator(); j.hasNext();)
		{
			Map<String, Object> row = j.next();
			userMails.add((String)row.get("email"));
			
		}
		
		return userMails;
	}

}
