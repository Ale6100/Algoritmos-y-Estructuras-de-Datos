package aed;

public class Agenda {
    private Fecha fechaActual;
    private Recordatorio[] recordatorios;

    public Agenda(Fecha fechaActual) {
        this.fechaActual = new Fecha(fechaActual);
        this.recordatorios = new Recordatorio[]{};
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        Recordatorio[] res = new Recordatorio[this.recordatorios.length + 1];

        for (int j = 0; j < this.recordatorios.length; j++) {
            res[j] = this.recordatorios[j];
        }

        res[this.recordatorios.length] = recordatorio;

        this.recordatorios = res;
    }

    @Override
    public String toString() {
        String concatenacion = this.fechaActual().toString() + "\n" + "=====" + "\n";

        for (int i = 0; i < this.recordatorios.length; i++) {
            if (this.recordatorios[i].fecha().toString().equals(this.fechaActual().toString())) {
                concatenacion += this.recordatorios[i].toString() + "\n";
            }
        }

        return concatenacion;
    }

    public void incrementarDia() {
        this.fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(fechaActual);
    }

}
