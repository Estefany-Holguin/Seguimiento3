package Ejercicio3;

interface ProcesadorPago {
    void procesarPago(double monto);
    double calcularCosto(double monto);
}


abstract class MetodoPagoBase implements ProcesadorPago {
    private int intentos = 0;

    public void registrarTransaccion(double monto, boolean exito) {
        System.out.println("Transacción: " + monto + " | Estado: " + (exito ? "Éxito" : "Fallo"));
    }

    public void reintentarPago(double monto) {
        while (intentos < 3) {
            boolean exito = procesarPagoInterno(monto);
            registrarTransaccion(monto, exito);
            intentos++;
            if (exito) break;
            System.out.println("Reintento " + intentos + " fallido");
        }
    }

    private boolean procesarPagoInterno(double monto) {
        try {
            procesarPago(monto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

class TarjetaCredito extends MetodoPagoBase {
    @Override
    public void procesarPago(double monto) {
        if (monto > 1000) {
            System.out.println("Límite de crédito excedido");
            throw new RuntimeException();
        }
        System.out.println("Pago con tarjeta procesado. Total: " + calcularCosto(monto));
    }

    @Override
    public double calcularCosto(double monto) {
        return monto * 1.02; // 2% de comisión
    }
}

class TransferenciaBancaria extends MetodoPagoBase {
    @Override
    public void procesarPago(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido para transferencia");
            throw new RuntimeException();
        }
        System.out.println("Pago por transferencia procesado. Total: " + calcularCosto(monto));
    }

    @Override
    public double calcularCosto(double monto) {
        return monto + 5; // comisión fija de 5
    }
}

class Criptomoneda extends MetodoPagoBase {
    @Override
    public void procesarPago(double monto) {
        if (Math.random() < 0.3) {
            System.out.println("Red blockchain no disponible");
            throw new RuntimeException();
        }
        System.out.println("Pago con criptomoneda procesado. Total: " + calcularCosto(monto));
    }

    @Override
    public double calcularCosto(double monto) {
        return monto * 1.01; // 1% de comisión
    }
}
    class SimulacionPagos {
    public static void main(String[] args) {
        ProcesadorPago tarjeta = new TarjetaCredito();
        ProcesadorPago transferencia = new TransferenciaBancaria();
        ProcesadorPago cripto = new Criptomoneda();

        System.out.println("\n--- Transacción con tarjeta ---");
        ((MetodoPagoBase)tarjeta).reintentarPago(800);

        System.out.println("\n--- Transacción con transferencia ---");
        ((MetodoPagoBase)transferencia).reintentarPago(500);

        System.out.println("\n--- Transacción con criptomoneda (puede fallar) ---");
        ((MetodoPagoBase)cripto).reintentarPago(300);
    }
}

