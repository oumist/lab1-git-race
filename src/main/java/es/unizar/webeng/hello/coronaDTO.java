package es.unizar.webeng.hello;

/**
 * DTO coronavirus information
 */
public class coronaDTO {
    private int TotalConfirmed;
    private int TotalDeaths;
    private int TotalRecovered;

    public coronaDTO() {}
    public coronaDTO(int TotalConfirmed, int TotalDeaths, int TotalRecovered){
        this.TotalConfirmed = TotalConfirmed;
        this.TotalDeaths = TotalDeaths;
        this.TotalRecovered = TotalRecovered;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }
    public int getTotalDeaths() {
        return TotalDeaths;
    }
    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalConfirmed(int totalConfirmed) {
       this.TotalConfirmed = totalConfirmed;
    }
    public void setTotalDeaths(int totalDeaths) {
        this.TotalDeaths = totalDeaths;
    }
    public void setTotalRecovered(int totalRecovered) {
        this.TotalRecovered = totalRecovered;
    }
}