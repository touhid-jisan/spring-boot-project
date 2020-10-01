package io.touhidjisan.springbootprojectcoronavirustracker.model;

public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int diffPrevDayCases;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getDiffPrevDayCases() {
        return diffPrevDayCases;
    }

    public void setDiffPrevDayCases(int diffPrevDayCases) {
        this.diffPrevDayCases = diffPrevDayCases;
    }

    @Override
    public String toString() {
        return "LocataionStats [state=" + state + ", country=" + country + ", latestCases=" + latestTotalCases
                + "]";
    }

}
