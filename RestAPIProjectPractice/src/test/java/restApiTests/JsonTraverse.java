package restApiTests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class JsonTraverse {
	
	@Test
	public void jsonTraverse()
	{
		String response = "{\r\n\"dashboard\": {\r\n\"purchaseAmount\": 910,\r\n\"website\": \"rahulshettyacademy.com\"\r\n},\r\n\"courses\": [\r\n{\r\n\"title\": \"Selenium Python\",\r\n\"price\": 50,\r\n\"copies\": 6\r\n},\r\n{\r\n\"title\": \"Cypress\",\r\n\"price\": 40,\r\n\"copies\": 4\r\n},\r\n{\r\n\"title\": \"RPA\",\r\n\"price\": 45,\r\n\"copies\": 10\r\n}\r\n]\r\n}";
		
		JsonPath js = new JsonPath(response);
		
		int size = js.getInt("size()");
		
		for(int i=0;i<size;i++)
		{
			if(i==0)
			{
				System.out.println("Purchase amount = "+js.get("dashboard.purchaseAmount"));
				System.out.println("website = "+js.get("dashboard.website"));
			}else if(i==1)
			{
				int count = js.get("courses.size()");
				for(int j=0;j<count;j++)
				{
					System.out.println("title = "+js.get("courses["+j+"].title"));
					System.out.println("price = "+js.get("courses["+j+"].price"));
					System.out.println("copies = "+js.get("courses["+j+"].copies"));
				}
			}
		}
		
	}

}
