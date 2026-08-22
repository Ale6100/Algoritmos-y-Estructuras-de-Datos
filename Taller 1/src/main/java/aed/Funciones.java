package aed;

class Funciones {

/***  Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        return x*x;
    }

    double distancia(double x, double y) {
        return Math.sqrt(x*x + y*y);
    }

    // Función auxiliar
    boolean divideA(double d, double n) { // ¿d divide a n?
        boolean res;

        if (d == 0) {
            res = false;
        } else {
            double resto = n % d;
            res = resto == 0;
        }

        return res;
    }

    boolean esPar(int n) {
        return divideA(2, n);
    }

    boolean esBisiesto(int n) {
        return (divideA(4, n) && !divideA(100, n)) || divideA(400, n);
    }

    int factorialIterativo(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        if (n == 0) {
            return 1;
        }

        return n*factorialIterativo(n-1);
    }

    boolean esPrimo(int n) {
        int cantDivisores = 0;

        for (int i = 1; i <= n; i++) {
            if (divideA(i, n)) {
                cantDivisores++;
            }
        }

        return cantDivisores == 2;
    }

    int sumatoria(int[] numeros) {
        int suma = 0;
        for (int i = 0; i < numeros.length; i++) {
            suma += numeros[i];
        }
        return suma;
    }

    int busqueda(int[] numeros, int buscado) {
        boolean encontrado = false;

        int i = 0;
        while (!encontrado) {
            if (numeros[i] == buscado) {
                encontrado = true;
            } else {
                i++;
            }
        }
        return i;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false;
        for (int i = 0; i < numeros.length; i++) {
            if (esPrimo(numeros[i])) {
                res = true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res = true;

        for (int i = 0; i < numeros.length; i++) {
            if (!esPar(numeros[i])) {
                res = false;
            }
        }

        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean esPrefijo = true;

        int longS1 = s1.length();

        if (longS1 <= s2.length()) {
            for (int i = 0; i < longS1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    esPrefijo = false;
                }
            }
        } else {
            esPrefijo = false;
        }

        return esPrefijo;
    }

    // Función auxiliar
    String invertir(String word) {
        String res = "";
        if (word.length() > 0) {
            for (int i = word.length() - 1; i >= 0; i--) {
                res += word.charAt(i);
            }
        }
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        boolean esSufijo;

        if (s1.length() <= s2.length()) {
            esSufijo = esPrefijo(invertir(s1), invertir(s2));
        } else {
            esSufijo = false;
        }

        return esSufijo;
    }

/***  Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b);
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;

        if (xs.length == ys.length) {
            for (int i = 0; i < xs.length; i++) {
                if (xs[i] != ys[i]) {
                    res = false;
                }
            }
        } else {
            res = false;
        }

        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = true;
        for (int x: xs) {
            if (x <= 0) {
                res = false;
            }
        }

        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0];

        for (int i = 1; i <= xs.length - 1; i++) {
            if (xs[i] > res) res = xs[i];
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        for (int i = 0; i < xs.length - 1; i++) {
            if (xs[i] > xs[i+1]) {
                res = false;
            }
        }
        return res;
    }
}
