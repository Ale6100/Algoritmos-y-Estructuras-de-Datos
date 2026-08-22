package aed;

public class Horario {
    private int horaObj;
    private int minutosObj;

    public Horario(int hora, int minutos) {
        this.horaObj = hora;
        this.minutosObj = minutos;
    }

    public Horario(Horario horario) {
        this.horaObj = horario.horaObj;
        this.minutosObj = horario.minutosObj;
    }

    public int hora() {
        return this.horaObj;
    }

    public int minutos() {
        return this.minutosObj;
    }

    @Override
    public String toString() {
        return this.horaObj + ":" + this.minutosObj;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null) {
            return false;
        }

        if (this.getClass() != otro.getClass()) {
            return false;
        }

        Horario otroCasteado = (Horario) otro;

        return this.horaObj == otroCasteado.horaObj && this.minutosObj == otroCasteado.minutosObj;
    }
}
