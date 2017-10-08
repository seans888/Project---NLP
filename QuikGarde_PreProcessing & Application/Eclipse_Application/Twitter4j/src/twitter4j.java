import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class twitter4j {
    public static void main(String[] args)
  {
      
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey("SDWBbmp4a7LkAqRiDPwHZwx1s")
      .setOAuthConsumerSecret("nU6FZmQlosJndWDyOei464MyBdIJyTZpSlbbMACP1Dln3lEHff")
      .setOAuthAccessToken("4726783074-6yHuw1hlT491qzVlXvn0ilyATEQYIEiTPZwzyxg")
      .setOAuthAccessTokenSecret("zWgZv2dEsEPb4dLf5CLcBlbbjEF6oqafolhRFSO0urCwY");
    
    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
    Query query = new Query("#world");
    int numberOfTweets = 200;
    long lastID = Long.MAX_VALUE;
    ArrayList<Status> tweets = new ArrayList<>();
    while (tweets.size () < numberOfTweets) {
      if (numberOfTweets - tweets.size() > 100)
        query.setCount(100);
      else 
        query.setCount(numberOfTweets - tweets.size());
      try {
        QueryResult result = twitter.search(query);
        tweets.addAll(result.getTweets());
        System.out.println("Gathered " + tweets.size() + " tweets"+"\n");
        for (Status t: tweets) 
          if(t.getId() < lastID) 
              lastID = t.getId();

      }

      catch (TwitterException te) {
        System.out.println("Couldn't connect: " + te);
      }
      query.setMaxId(lastID-1);
    }

    for (int i = 0; i < tweets.size(); i++) {
      Status t = (Status) tweets.get(i);

     // GeoLocation loc = t.getGeoLocation();

      String user = t.getUser().getScreenName();
      String msg = t.getText();
      //String time = "";
      //if (loc!=null) {
        //Double lat = t.getGeoLocation().getLatitude();
        //Double lon = t.getGeoLocation().getLongitude();*/
       System.out. println(i + " USER: " + user + " wrote: " + msg + "\n");
       
    }
  }
}

      //else 
        //System.out.println(i + " USER: " + user + " wrote: " + msg+"\n");