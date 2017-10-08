import twitter4j.conf.ConfigurationBuilder;


public class Twiiter4j {
	public static void main (String []args) throws TwitterException{
		ConfigurationBuilder cf = new ConfigurationBuilder ();
		
		cf.setDebugEnabled(true)
		.setOAuthConsumerKey("Smsh2R3KGgXTfj6iG1JT2ILsd")
		.setOAuthConsumerSecret("mLtnURdMQ2zNsBSEaFZ4JEvm4kHybbCekVZx6nllETQDN9bElr")
		.setOAuthAccessToken("4836254654-t5DebTDwEnlTszpBppcklnL4Rv9KfvhuNL83cMP")
		.setOAuthAccessTokenSecret("6o3EZ8ZizlcCfxcZqWKbOEBYQPsCjNhKVdYeAyJXN03JC");
		
	TwitterFactor tf = new TwitterFactor (cf.build());
	twitter4j.Twitter twitter = tf.getInstance();
	
	List<Status> status = twitter.GetTimeline();
	for(Status st: status){
		System.out.println(st.getUser().getName()+"------"+ st.getText());
	}
		
		
	}
	


}
