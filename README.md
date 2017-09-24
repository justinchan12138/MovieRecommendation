# MovieRecommendation

This is a fully functional app that recommends the 20 most popular and highly-rated movies to the user. The app gets the recommendations from The Movie Database API. You need to obtain your own personal API key for this app to work. You can register for an account on https://www.themoviedb.org/ and once you have your API key, you can insert it in the java Network.class, updating the MOVIE_URL static variable, which is:

    private static final String MOVIE_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=<<Insert your API Key Here>>";

Then run the project in android studio and it should be working. Your phone needs to be connected to the internet to get the data from the API, but the app won't crush even if your phone is on airplane mode.  
