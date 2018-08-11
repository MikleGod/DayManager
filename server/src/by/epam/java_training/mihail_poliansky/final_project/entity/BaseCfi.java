package by.epam.java_training.mihail_poliansky.final_project.entity;

public enum  BaseCfi {
    TRANSPORT(new CashFlowItem("транспорт", 1)),
    FOOD(new CashFlowItem("еда", 2)),
    CLOTHES(new CashFlowItem("одежда", 3));

    private final CashFlowItem item;

    BaseCfi(CashFlowItem item) {
        this.item = item;
    }


    public CashFlowItem getItem() {
        return item;
    }
}
