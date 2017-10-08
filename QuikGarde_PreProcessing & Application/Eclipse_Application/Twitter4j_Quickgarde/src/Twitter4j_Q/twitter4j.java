/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Twitter4j_Q;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 *
 * @author Eva Samilliano
 */
public class twitter4j {
    public static void main(String[] args) throws TwitterException {
      
    ConfigurationBuilder cf = new ConfigurationBuilder();
    cf.setDebugEnabled(true)
      .setOAuthConsumerKey("0U8e6dnlEUlY3TsCxICuK2ikJ")
      .setOAuthConsumerSecret("fBGyKNNv6T1YccXk7CtkhKFlOitqS7UDmf1Rqv5eHZ4pHgPdt0")
      .setOAuthAccessToken("4726783074-imWTYb0FMOXHkXhgNrF66Dm59GqP2T45vfh76mb")
      .setOAuthAccessTokenSecret("5gDy85ehJHxCeD9dL2Y4maexPsKtOL0XvoVz0zkEnDQOS");
    
    TwitterFactory tf = new TwitterFactory(cf.build());
    Twitter twitter = tf.getInstance();
    
    List <Status> status = twitter.getHomeTimeline();
    for(Status st : status)
        {
            System.out.println(st.getUser().getName()+"---"+st.getText());
        }
    
  }
}