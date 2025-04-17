package Ejercicio2;

    // Excepciones personalizadas
    class BateriaAgotadaException extends Exception {
        public BateriaAgotadaException(String mensaje) {
            super(mensaje);
        }
    }

    class SobrecargaException extends Exception {
        public SobrecargaException(String mensaje) {
            super(mensaje);
        }
    }

    // Interfaz para dispositivos recargables
    interface Recargable {
        void recargar() throws SobrecargaException;
    }

    // Clase abstracta base
    abstract class Dispositivo {
        private boolean encendido;
        private String nombre;

        public Dispositivo(String nombre) {
            this.nombre = nombre;
            this.encendido = false;
        }

        public void encender() {
            encendido = true;
            System.out.println(nombre + " encendido.");
        }

        public void apagar() {
            encendido = false;
            System.out.println(nombre + " apagado.");
        }

        public boolean isEncendido() {
            return encendido;
        }

        public abstract void reporteEstado();
    }

    // Laptop
    class Laptop extends Dispositivo implements Recargable {
        private int nivelBateria;

        public Laptop(String nombre, int nivelBateria) {
            super(nombre);
            this.nivelBateria = nivelBateria;
        }

        @Override
        public void reporteEstado() {
            System.out.println("Laptop batería: " + nivelBateria + "%");
        }

        @Override
        public void recargar() throws SobrecargaException {
            if (isEncendido()) {
                throw new SobrecargaException("No se puede recargar mientras está encendida.");
            }
            nivelBateria = 100;
            System.out.println("Laptop recargada al 100%.");
        }
    }
    class TelefonoInteligente extends Dispositivo implements Recargable {
        private int nivelBateria;

        public TelefonoInteligente(String nombre, int nivelBateria) {
            super(nombre);
            this.nivelBateria = nivelBateria;
        }

        @Override
        public void reporteEstado() {
            System.out.println("Teléfono batería: " + nivelBateria + "%");
        }

        @Override
        public void recargar() throws SobrecargaException {
            if (isEncendido()) {
                throw new SobrecargaException("No se puede recargar mientras está encendido.");
            }
            nivelBateria = 100;
            System.out.println("Teléfono recargado al 100%.");
        }
    }
    class BombillaInteligente extends Dispositivo {
        private int intensidad;

        public BombillaInteligente(String nombre, int intensidad) {
            super(nombre);
            this.intensidad = intensidad;
        }

        @Override
        public void reporteEstado() {
            System.out.println("Bombilla intensidad: " + intensidad + "%");
        }
    }

     class SimulacionDispositivos {
        public static void main(String[] args) {
            Laptop laptop = new Laptop("Laptop Lenovo", 25);
            TelefonoInteligente telefono = new TelefonoInteligente("Samsung Galaxy", 10);
            BombillaInteligente bombilla = new BombillaInteligente("Bombilla Philips", 75);

            laptop.encender();
            telefono.encender();
            bombilla.encender();

            laptop.reporteEstado();
            telefono.reporteEstado();
            bombilla.reporteEstado();

            laptop.apagar();
            telefono.apagar();

            try {
                laptop.recargar();
                telefono.recargar();
            } catch (SobrecargaException e) {
                System.out.println("Error: " + e.getMessage());
            }

            laptop.reporteEstado();
            telefono.reporteEstado();
        }
    }


