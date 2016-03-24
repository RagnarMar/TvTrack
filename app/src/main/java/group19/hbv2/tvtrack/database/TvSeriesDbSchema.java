package group19.hbv2.tvtrack.database;

/**
 * Created by Jóhannes Þorkell Tómasson on 23.3.2016, jtht2@hi.is.
 */
public class TvSeriesDbSchema {

    public class TvSeriesTable {
        public static final String NAME = "tvseries";

        public class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String RATING = "rating";
            public static final String FIRST_AIR_DATE = "firstAirDate";
            public static final String POSTER_PATH = "posterPath";
        }
    }
}
