package com.thyraxx.scrada.smashgg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thyraxx.scrada.customproperties.CustomProperties;
import com.thyraxx.scrada.smashgg.model.Tournament;
import com.thyraxx.scrada.smashgg.model.generated.Event;
import com.thyraxx.scrada.smashgg.model.generated.Node;
import com.thyraxx.scrada.smashgg.model.generated.Root;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SmashggApi {

    private static final long startingDate = System.currentTimeMillis() / 1000L;
    private static final String baseUrl = ("https://api.start.gg");
    private static final String graphql = "/gql/alpha";
    private static final String expandRegistration = "?expand=registrationOption";
    private static final Logger logger = LoggerFactory.getLogger(SmashggApi.class);


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

    // TODO: integrate/retrieve from graphql once the API finally added retrieving max number of participants per event
    public static Map<String, Double> getExtraEventInfo(String slug) {
        HttpGet get = new HttpGet(baseUrl + "/" + slug + expandRegistration);

        get.addHeader("authorization", "Bearer " + CustomProperties.getSmashggApiKey());

        logger.debug("Http get url: {}", baseUrl + "/" + slug + expandRegistration);

        CloseableHttpResponse response = null;
        String responseJsonString = null;
        Map<String, Double> extraEventInfo = new HashMap<>();
        try {
            responseJsonString = getHttpGetResponse(get);

            JSONObject jsonObject = new JSONObject(responseJsonString).getJSONObject("entities");

            JSONObject costEntryEvent = jsonObject.getJSONArray("registrationOptionValue").getJSONObject(0);
            double eventFee = !costEntryEvent.isNull("fee") ? costEntryEvent.getDouble("fee") : 0;

            JSONObject participantEntryCap = jsonObject.getJSONArray("registrationOptionValue").getJSONObject(0);
            double eventParticipantCap = !participantEntryCap.isNull("valueLimit") ? participantEntryCap.getDouble("valueLimit") : 0;

            extraEventInfo.put("eventFee", eventFee);
            extraEventInfo.put("eventParticipantCap", eventParticipantCap);

        } catch (IOException | JSONException e) {
            logger.error("Retrieving extra event info went wrong.");
            e.printStackTrace();
        }

        return extraEventInfo;
    }

    public static List<Tournament> getSmashTournamentEvents(String variables) {

        HttpPost post = new HttpPost(baseUrl + graphql);

        List<NameValuePair> params = new ArrayList<>();

        post.addHeader("authorization", "Bearer " + CustomProperties.getSmashggApiKey());
        params.add(new BasicNameValuePair("query", alltournamenstQuery));
        params.add(new BasicNameValuePair("variables", variables));

        post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        logger.debug("Http post url: {} with variables: {}", baseUrl + graphql, variables);

        // TODO: please, clean up/change
        String responseJsonString = null;
        List<Tournament> tournaments = new ArrayList<>();

        try {
            responseJsonString = getHttpPostResponse(post);
        } catch (IOException e) {
            logger.error("Could not post to: {}", baseUrl + graphql);
            e.printStackTrace();
        }


        ObjectMapper om = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Root root = null;
        try {
            root = om.readValue(responseJsonString, Root.class);

            ModelMapper modelMapper = new ModelMapper();
            for (Node node : root.getData().getTournaments().getNodes()) {

                // For some reason, some events are null instead of an empty array
                if(node.getEvents() == null)
                    continue;

                for (Event event : node.getEvents()) {
                    Map<String, Double> extraEventInfo = getExtraEventInfo(event.getSlug());

                    event.setFee(extraEventInfo.get("eventFee"));
                    event.setValueLimit(extraEventInfo.get("eventParticipantCap").intValue());
                }

                Tournament tournamentDTO = modelMapper.map(node, Tournament.class);
                tournaments.add(tournamentDTO);
            }
        } catch (JsonProcessingException e) {
            logger.error("Couldn't process json");
            e.printStackTrace();
        }

        return tournaments;
    }

    private static String getHttpPostResponse(HttpPost post) throws IOException {
        try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            return closeableHttpClient.execute(post, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
        }
    }

    private static String getHttpGetResponse(HttpGet get) throws IOException {
        try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            return closeableHttpClient.execute(get, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
        }
    }

    // TODO: unused currently
//    public static Tournament updateExistingTournaments(long tournamentId)
//    {
//        HttpPost post = new HttpPost(baseUrl + graphql);
//        List<NameValuePair> params = new ArrayList<>();
//
//        post.addHeader("authorization", "Bearer " + CustomProperties.getSmashggApiKey());
//        params.add(new BasicNameValuePair("query", singleTournamentQuery));
//        params.add(new BasicNameValuePair("variables", "{ \"id\":" + tournamentId + "}"));
//
//        post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
//
//        // TODO: please, clean up/change
//        HttpResponse response = null;
//        String responseJsonString = null;
//        Tournament tournament = null;
//        try {
//            response = HttpClients.createDefault().execute(post);
//            responseJsonString = EntityUtils.toString(response.getEntity());
//
//            ObjectMapper om = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//            Tournament mappedTournamentJson = om.readValue(responseJsonString, Tournament.class);
//
//            ModelMapper modelMapper = new ModelMapper();
//            tournament = modelMapper.map(mappedTournamentJson, Tournament.class);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return tournament;
//    }
}

