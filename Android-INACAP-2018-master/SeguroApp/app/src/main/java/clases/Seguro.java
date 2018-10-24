package clases;

public class Seguro {
    private int anhoVehiculo;
    private double valorUf;
    private double valorSeguro;
    private boolean asegurable;

    public int getAnhoVehiculo() {
        return anhoVehiculo;
    }

    public void setAnhoVehiculo(int anhoVehiculo) {
        this.anhoVehiculo = anhoVehiculo;
    }

    public double getValorUf() {
        return valorUf;
    }

    public void setValorUf(double valorUf) {
        this.valorUf = valorUf;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public boolean isAsegurable() {
        return asegurable;
    }

    public void setAsegurable(boolean asegurable) {
        this.asegurable = asegurable;
    }
    public double calcularValorSeguro(){
        double valor_seguro = (2018 - this.anhoVehiculo)*this.valorUf/10;
        return valor_seguro;
    }
}
