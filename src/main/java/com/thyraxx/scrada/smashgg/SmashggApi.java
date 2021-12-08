package com.thyraxx.scrada.smashgg;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.thyraxx.scrada.smashgg.model.SmashJsonModel;
import com.thyraxx.scrada.smashgg.model.Tournament;
import com.thyraxx.scrada.smashgg.model.TournamentDTO;
import com.thyraxx.scrada.smashgg.model.generated.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SmashggApi {

    private static final long startingDate = System.currentTimeMillis() / 1000L;
    private static final String baseUrl = ("https://api.smash.gg");
    private static final String graphql = "/gql/alpha";
    private static final String expandRegistration = "?expand=registrationOption";
//    private static final String alltournamenstQuery =
//            """
//                query getAllUltimateTournaments($lastDate: Timestamp!) {
//                    tournaments(query: {
//                    page: 1,
//                    perPage: 20,
//                    sortBy: "startAt desc",
//                    filter: {
//                      afterDate: $lastDate,
//                      countryCode: "NL",
//                      regOpen: true,
//                    }
//                  }),{
//                    nodes {
//                      events(limit: 100,
//                        filter: { videogameId: 1386} ) {
//                        id
//                        name
//                        slug
//                        tournament {
//                          registrationClosesAt
//                          id
//                          name
//                          slug
//                          isRegistrationOpen
//                          shortSlug
//                          city
//                          createdAt
//                          startAt
//                          state
//                        }
//                      }
//                    }
//                  }
//                }
//            """;

    // TODO: add teamRoster size of something else to check if event is singles
    //     teamRosterSize {
    //       maxPlayers
    //     }
    private static final String alltournamenstQuery =
            """
                query getAllUltimateTournaments($lastDate: Timestamp!) {
                            tournaments(query: {
                             page: 1,
                             perPage: 15,
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
                               }
                               registrationClosesAt
                               id
                               name
                               slug
                               isRegistrationOpen
                               shortSlug
                               city
                               createdAt
                               startAt
                               endAt
                               state
                             }
                           }
                         }
                    """;

    private static final String singleTournamentQuery =
            """
                query getAllUltimateTournaments($id: ID!) {
                  tournament(id: $id) {
                    id
                    registrationClosesAt
                    state
                    isRegistrationOpen
                    startAt
                  }
                }
            """;

    public static Map<String, Integer> getExtraEventInfo(String slug) {
        HttpGet get = new HttpGet(baseUrl + "/" + slug + expandRegistration);

        get.addHeader("authorization", "Bearer 3fb6e59d9be9b98785686de4e92766d4");

        HttpResponse response = null;
        String responseJsonString = null;
        Map<String, Integer> extraEventInfo = new HashMap<>();
        try {
            response = HttpClients.createDefault().execute(get);
            responseJsonString = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(responseJsonString).getJSONObject("entities");
            int eventFee = jsonObject.getJSONArray("registrationOptionValue").getJSONObject(0).getInt("fee");
            int eventParticipantCap = jsonObject.getJSONArray("registrationOptionValue").getJSONObject(0).getInt("valueLimit");

            extraEventInfo.put("eventFee", eventFee);
            extraEventInfo.put("eventParticipantCap", eventParticipantCap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return extraEventInfo;
    }


    public static List<Tournament> getSmashTournamentEvents(String variables) {
        HttpPost post = new HttpPost(baseUrl + graphql);
        List<NameValuePair> params = new ArrayList<>();

        post.addHeader("authorization", "Bearer 3fb6e59d9be9b98785686de4e92766d4");
        params.add(new BasicNameValuePair("query", alltournamenstQuery));
        params.add(new BasicNameValuePair("variables", variables));

        post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        // TODO: please, clean up/change
        HttpResponse response = null;
        String responseJsonString = null;
        List<Tournament> tournaments = new ArrayList<>();
        try {
            response = HttpClients.createDefault().execute(post);
            responseJsonString = EntityUtils.toString(response.getEntity());

            ObjectMapper om = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Root root = om.readValue(responseJsonString, Root.class);

            ModelMapper modelMapper = new ModelMapper();
            for(Node node : root.getData().getTournaments().getNodes())
            {
                for (Event event : node.getEvents())
                {
                    Map<String, Integer> extraEventInfo = getExtraEventInfo(event.getSlug());

                    event.setFee(extraEventInfo.get("eventFee"));
                    event.setValueLimit(extraEventInfo.get("eventParticipantCap"));
                }

                Tournament tournamentDTO = modelMapper.map(node, Tournament.class);
                tournaments.add(tournamentDTO);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return tournaments;
    }

    public static Tournament updateExistingTournaments(long tournamentId)
    {
        HttpPost post = new HttpPost(baseUrl + graphql);
        List<NameValuePair> params = new ArrayList<>();

        post.addHeader("authorization", "Bearer 3fb6e59d9be9b98785686de4e92766d4");
        params.add(new BasicNameValuePair("query", singleTournamentQuery));
        params.add(new BasicNameValuePair("variables", "{ \"id\":" + tournamentId + "}"));

        post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        // TODO: please, clean up/change
        HttpResponse response = null;
        String responseJsonString = null;
        Tournament tournament = null;
        try {
            response = HttpClients.createDefault().execute(post);
            responseJsonString = EntityUtils.toString(response.getEntity());

            ObjectMapper om = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Tournament mappedTournamentJson = om.readValue(responseJsonString, Tournament.class);

            ModelMapper modelMapper = new ModelMapper();
            tournament = modelMapper.map(mappedTournamentJson, Tournament.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tournament;
    }
}

