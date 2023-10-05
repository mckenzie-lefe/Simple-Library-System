import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TestRating {

    private Rating rating;

    @Before
    public void setUp() {
        rating = new Rating();
    }

    @Test
    public void testInitialAverageRating() {
        assertEquals(0.0, rating.getAverageRating(), 0.0001);
    }

    @Test
    public void testUpdateRatingLiked() {
        rating.updateRating(true);
        assertEquals(1.0, rating.getAverageRating(), 0.0001);
    }

    @Test
    public void testUpdateRatingNotLiked() {
        rating.updateRating(false);
        assertEquals(0.0, rating.getAverageRating(), 0.0001);
    }

    @Test
    public void testMultipleRatings() {
        rating.updateRating(true);
        rating.updateRating(false);
        rating.updateRating(true);

        // (1 + 0 + 1) / 3 = 0.6667
        assertEquals(0.6667, rating.getAverageRating(), 0.0001);
    }

    @Test
    public void testMultipleRatingsNotLiked() {
        rating.updateRating(false);
        rating.updateRating(false);

        // (0 + 0) / 2 = 0.0
        assertEquals(0.0, rating.getAverageRating(), 0.0001);
    }
}
