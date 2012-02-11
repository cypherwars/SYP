package inputdata;
import org.json.simple.*;
public class testing {

	public static String abc11()
{
	JSONObject obj=new JSONObject();
    obj.put("name","foo");
    obj.put("num",new Integer(100));
    obj.put("balance",new Double(1000.21));
    obj.put("is_vip",new Boolean(true));
    obj.put("nickname",null);
    //out.print(obj);
   // out.flush();
    return "ksh";
}
}
