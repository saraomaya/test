import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		Document doc;
		try {
			Connection connect = Jsoup
					.connect("http://www.lotterypost.com/game/327");
			doc = connect.userAgent("Mozilla").get();
			Elements elementsByAttributeValue2 = doc
					.getElementsByAttributeValue("class", "resultsDrawDate");
			Element elementsByAttributeValuecontaonet = doc
					.getElementsByAttributeValue("class", "resultsRow").get(0);
			Elements elementsByAttributeValue3 = elementsByAttributeValuecontaonet
					.getElementsByAttributeValue("class", "sprite-results");
			String numberStr = "";
			for (Element element : elementsByAttributeValue3) {
				numberStr += " " + element.text();
			}

			DateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy",
					Locale.ENGLISH);
			Date date = null;
			try {
				date = format.parse(elementsByAttributeValue2.text());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DateFormat format2 = new SimpleDateFormat("yyyy-MM-d",
					Locale.ENGLISH);
			;
			ArrayList<String> arrayLIst = new ArrayList<String>();
			arrayLIst.add(format2.format(date));
			arrayLIst.add(numberStr);
			// String string = "abcde";
			Gson gson = new Gson();
			// JsonObject json = new JsonObject();
			String json = new Gson().toJson(arrayLIst);
			System.out.print(json);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
