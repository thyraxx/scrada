package com.thyraxx.scrada.smashgg;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.thyraxx.scrada.smashgg.model.EventsModel;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class SmashggApi {

    private static final long startingDate = System.currentTimeMillis() / 1000L;
    private static final String baseUrl = ("https://api.smash.gg");
    private static final String graphql = "/gql/alpha";
    private static final String expandRegistration = "?expand=registrationOption";
    private static final String alltournamenstQuery =
            """
                query getAllUltimateTournaments($lastDate: Timestamp!) {
                    tournaments(query: {
                    page: 1,
                    perPage: 20,
                    sortBy: "startAt desc",
                    filter: {
                      afterDate: $lastDate,
                      countryCode: "NL",
                      regOpen: true,
                    }
                  }),{
                    nodes {
                      events(limit: 100,
                        filter: { videogameId: 1386} ) {
                        id
                        name
                        slug
                        tournament {
                          registrationClosesAt
                          id
                          name
                          slug
                          isRegistrationOpen
                          shortSlug
                          city
                          createdAt
                          startAt
                          state
                        }
                      }
                    }
                  }
                }
            """;

    private static final String singleTournamentQuery =
            """
                query getAllUltimateTournaments($id: ID!) {
                  tournament(id: $id) {
                    registrationClosesAt
                      state
                      id
                      name
                      slug
                      isRegistrationOpen
                      city
                      createdAt
                      startAt
                  }
                }
            """;

    public static List<Object> runAPIQuery(String variables) {
        HttpPost post = new HttpPost(baseUrl + graphql);
        List<NameValuePair> params = new ArrayList<>();

        post.addHeader("authorization", "Bearer 3fb6e59d9be9b98785686de4e92766d4");
        params.add(new BasicNameValuePair("query", alltournamenstQuery));
        params.add(new BasicNameValuePair("variables", variables));

        post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        HttpResponse response = null;
        String test = null;
        List<Object> ja = null;
        try {
            response = HttpClients.createDefault().execute(post);
            test = EntityUtils.toString(response.getEntity());
            ja = new JSONObject(test).getJSONObject("data").getJSONObject("tournaments").getJSONArray("nodes").toList();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ja;
    }
}

//{
//              "id": 461915,
//              "name": "Super Smash Bros. Ultimate Doubles",
//              "slug": "tournament/the-ultimate-performance-3-5/event/super-smash-bros-ultimate-doubles",
//              "tournament": {
//                "registrationClosesAt": 1638745140,
//                "id": 199975,
//                "name": "The Ultimate Performance 3.5",
//                "slug": "tournament/the-ultimate-performance-3-5",
//                "isRegistrationOpen": true,
//                "shortSlug": "TUP3-5",
//                "city": "Rotterdam",
//                "createdAt": 1582213853,
//                "startAt": 1639904400,
//                "state": 1
//              }
//            }