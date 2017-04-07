package sample;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Marcello395 on 2/12/17.
 */
public class DataGet {
    String name;
    int cost;
    int health;
    int attack;
    double value;
    double bound;

    //Imported Unirest Library to pull all data and parse JSON into objects
    public ArrayList<Card> grabber() throws Exception {
        //mashape url + key grab, this grabs only "minion" type cards
        HttpResponse<JsonNode> response = Unirest.get("/* any unirest API */ ")
                .header("Key")
                .header("Accept", "application/json")
                .asJson();

        //Gets the entire JSON and puts everything in a 'Card' type array list.
        JSONArray cardStats = response.getBody().getArray();
        ArrayList<Card> cardArray = new ArrayList();

        for (int i = 0; i < cardStats.length(); i++) {
            if (response.getBody().getArray().optJSONObject(i).has("cost") &&
                    response.getBody().getArray().getJSONObject(i).has("health") &&
                    response.getBody().getArray().getJSONObject(i).has("attack")
                    ) {

                name = response.getBody().getArray().getJSONObject(i).getString("name");
                cost = response.getBody().getArray().getJSONObject(i).getInt("cost");
                health = response.getBody().getArray().getJSONObject(i).getInt("health");
                attack = response.getBody().getArray().getJSONObject(i).getInt("attack");

                //value will be used for similarity metric, different attributes have different weights
                value = ((cost * .25) + (health * .45) + (attack * .30));

                //Adds all attributes to the card, this will be referenced in for my hashtable
                Card hsValues = new Card(name, cost, health, attack, value);
                cardArray.add(hsValues);
            }
        }
        return cardArray;
    }
}
