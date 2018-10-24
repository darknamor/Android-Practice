package operaciones;

public class Seguro {

    private int anoVehiculo;
    private double valorUf;
    private double valorSeguro;
    private boolean asegurable;

    public double calcularValorSeguro(){

        double valor_seguro = (2018 - this.anoVehiculo)*this.valorUf/10;

        return valor_seguro;
    }



    public int getAnoVehiculo() {
        return anoVehiculo;
    }

    public void setAnoVehiculo(int anoVehiculo) {
        this.anoVehiculo = anoVehiculo;
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
}
