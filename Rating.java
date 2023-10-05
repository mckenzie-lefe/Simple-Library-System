public class Rating {
    private int ratingsCount;
    private double averageRating;

    public Rating() {
        this.ratingsCount = 0;
        this.averageRating = 0.0;
    }

    public void updateRating(boolean liked) {
        this.ratingsCount++;
        if (liked) {
            this.averageRating = (this.averageRating * (this.ratingsCount - 1) + 1) / this.ratingsCount;
        } else {
            this.averageRating = (this.averageRating * (this.ratingsCount - 1)) / this.ratingsCount;
        }
    }

    public double getAverageRating() {
        return this.averageRating;
    }
}