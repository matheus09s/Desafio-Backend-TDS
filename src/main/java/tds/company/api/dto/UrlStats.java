package tds.company.api.dto;

public class UrlStats {
    private Long totalAccesses;
    private Double averageAccessesPerDay;

    public UrlStats(Long totalAccesses, Double averageAccessesPerDay) {
        this.totalAccesses = totalAccesses;
        this.averageAccessesPerDay = averageAccessesPerDay;
    }

    public long getTotalAccesses() {
        return totalAccesses;
    }

    public void setTotalAccesses(Long totalAccesses) {
        this.totalAccesses = totalAccesses;
    }

    public double getAverageAccessesPerDay() {
        return averageAccessesPerDay;
    }

    public void setAverageAccessesPerDay(Double averageAccessesPerDay) {
        this.averageAccessesPerDay = averageAccessesPerDay;
    }
}

