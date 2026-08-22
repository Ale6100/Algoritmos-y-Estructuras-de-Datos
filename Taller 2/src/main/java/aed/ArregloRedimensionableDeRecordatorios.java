package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        this.recordatorios = new Recordatorio[]{};
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.recordatorios = new Recordatorio[]{};

        for (int i = 0; i < vector.longitud(); i++) {
            this.agregarAtras(vector.obtener(i));
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);
    }

    public int longitud() {
        if (this.recordatorios == null) {
            return 0;
        }
        return this.recordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] res = new Recordatorio[this.longitud() + 1];

        for (int j = 0; j < this.longitud(); j++) {
            res[j] = this.obtener(j);
        }

        res[this.longitud()] = i;

        this.recordatorios = res;
    }

    public Recordatorio obtener(int i) {
        if (this.recordatorios == null) {
            return null;
        }
        return this.recordatorios[i];
    }

    public void quitarAtras() {
        Recordatorio[] res = new Recordatorio[this.longitud() - 1];

        for (int j = 0; j < this.longitud() -1; j++) {
            res[j] = this.obtener(j);
        }

        this.recordatorios = res;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        if (this.recordatorios == null) {
            return;
        }
        this.recordatorios[indice] = valor;
    }
}
