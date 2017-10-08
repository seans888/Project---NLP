import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
 
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
 
public class Solution {
 
    public static void main(String args[])
    {
        try
        {
            // The factory instance is re-useable and thread safe.
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer("Pk3FnsRFL2DZqMfvMdk8Nw", "EVaQJYrmj76mDBvJYT4XFXxHWRHf6VGrr734vG00NA");
 
            RequestToken requestToken = twitter.getOAuthRequestToken();
            AccessToken accessToken = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (null == accessToken)
            {
                System.out.println("Open the following URL and grant access to your account:");
                System.out.println(requestToken.getAuthorizationURL());
                System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
                String pin = null;
                try
                {
                    pin = br.readLine();
                } catch (IOException ex)
                {
                    Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
                }
                try
                {
                    if (pin.length() > 0)
                    {
                        accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                    } else
                    {
                        accessToken = twitter.getOAuthAccessToken();
                    }
                } catch (TwitterException te)
                {
                    if (401 == te.getStatusCode())
                    {
                        System.out.println("Unable to get the access token.");
                    } else
                    {
                        te.printStackTrace();
                    }
                }
            }
            //persist to the accessToken for future reference.
            storeAccessToken(twitter.verifyCredentials(), accessToken);
            Status status = twitter.updateStatus(args[0]);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            System.exit(0);
        } catch (TwitterException ex)
        {
            Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    private static void storeAccessToken(User user, AccessToken accessToken)
    {
        System.out.println(user.getScreenName());
    }
}
