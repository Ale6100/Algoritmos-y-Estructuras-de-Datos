package aed;

public class Fecha {
    private int diaObj;
    private int mesObj;

    public Fecha(int dia, int mes) {
        this.diaObj = dia;
        this.mesObj = mes;
    }

    public Fecha(Fecha fecha) {
        this.diaObj = fecha.diaObj;
        this.mesObj = fecha.mesObj;
    }

    public Integer dia() {
        return this.diaObj;
    }

    public Integer mes() {
        return this.mesObj;
    }

    public String toString() {
        return this.diaObj + "/" + this.mesObj;
    }

    @Override
    public boolean equals(Object otra) {
        if (otra == null) {
            return false;
        }

        if (this.getClass() != otra.getClass()) {
            return false;
        }

        Fecha otraCasteada = (Fecha) otra;

        return this.diaObj == otraCasteada.diaObj && this.mesObj == otraCasteada.mesObj;
    }

    public void incrementarDia() {
        boolean esElUltimoDiaDelMes = this.diasEnMes(this.mesObj) == this.diaObj;
        boolean esElUltimoMes = this.mesObj == 12;

        if (esElUltimoDiaDelMes) {
            this.diaObj = 1;

            if (esElUltimoMes) {
                this.mesObj = 1;
            } else {
                this.mesObj +=1;
            }
        } else {
            this.diaObj += 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }
}
