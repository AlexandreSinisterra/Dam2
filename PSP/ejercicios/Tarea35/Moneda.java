package ejercicios.Tarea35;

public class Moneda {
    private String name;
    private String symbol;
    private Double price_usd;
    private int rank;
    private Double pc24;

    public Moneda(String name, String symbol, Double price_usd, int rank, Double pc24) {
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.rank = rank;
        this.pc24 = pc24;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(Double price_usd) {
        this.price_usd = price_usd;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Double getPc24() {
        return pc24;
    }

    public void setPc24(Double pc24) {
        this.pc24 = pc24;
    }
}
