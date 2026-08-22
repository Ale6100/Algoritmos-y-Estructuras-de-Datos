package aed;

public class Recordatorio {
    private String mensajeObj;
    private Fecha fechaObj;
    private Horario horarioObj;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensajeObj = mensaje;
        this.fechaObj = new Fecha(fecha);
        this.horarioObj = new Horario(horario);
    }

    public Horario horario() {
        return new Horario(this.horarioObj);
    }

    public Fecha fecha() {
        return new Fecha (this.fechaObj);
    }

    public String mensaje() {
        return this.mensajeObj;
    }

    @Override
    public String toString() {
        return this.mensajeObj + " @ " + this.fechaObj.toString() + " " + this.horarioObj.toString();
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null) {
            return false;
        }

        if (this.getClass() != otro.getClass()) {
            return false;
        }

        Recordatorio otroCasteado = (Recordatorio) otro;

        return this.mensajeObj.equals(otroCasteado.mensajeObj)
                && this.horarioObj.equals(otroCasteado.horarioObj)
                && this.fechaObj.equals(otroCasteado.fechaObj);
    }
}
