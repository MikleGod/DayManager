package by.epam.java_training.mihail_poliansky.final_project.entity;

import java.util.Objects;

public class CashFlowStatDto {
    private CashFlowItem cfi;
    private double cost;

    public CashFlowStatDto(CashFlowItem cfi, double cost) {
        this.cfi = cfi;
        this.cost = cost;
    }

    public CashFlowItem getCfi() {
        return cfi;
    }

    public void setCfi(CashFlowItem cfi) {
        this.cfi = cfi;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashFlowStatDto that = (CashFlowStatDto) o;
        return Double.compare(that.cost, cost) == 0 &&
                Objects.equals(cfi, that.cfi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cfi, cost);
    }
}
