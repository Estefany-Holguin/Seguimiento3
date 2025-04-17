package Ejercicio1;

    abstract class Organismo {
        private int nivelDeEnergia;

        public Organismo(int energia) {
            this.nivelDeEnergia = energia;
        }

        public boolean estaVivo() {
            return nivelDeEnergia > 0;
        }

        public void reducirEnergia(int cantidad) {
            nivelDeEnergia -= cantidad;
        }

        public void aumentarEnergia(int cantidad) {
            nivelDeEnergia += cantidad;
        }

        public int getNivelDeEnergia() {
            return nivelDeEnergia;
        }

        public abstract void actuar();
    }
interface Consumidor {
    void consumir(Organismo o);
}
    class Planta extends Organismo {
        public Planta() {
            super(10);
        }

        public void fotosintesis() {
            aumentarEnergia(5);
            System.out.println("Planta realiza fotosíntesis. Energía: " + getNivelDeEnergia());
        }

        @Override
        public void actuar() {
            fotosintesis();
        }
    }

    class Herbivoro extends Organismo implements Consumidor {
        public Herbivoro(int energia) {
            super(energia);
        }

        @Override
        public void consumir(Organismo o) {
            if (o instanceof Planta && o.estaVivo()) {
                aumentarEnergia(5);
                o.reducirEnergia(5);
                System.out.println("Herbívoro come planta. Energía: " + getNivelDeEnergia());
            }
        }

        @Override
        public void actuar() {
            System.out.println("Herbívoro buscando comida...");
        }
    }

    class Carnivoro extends Organismo implements Consumidor {
        public Carnivoro(int energia) {
            super(energia);
        }

        @Override
        public void consumir(Organismo o) {
            if (o instanceof Herbivoro && o.estaVivo()) {
                aumentarEnergia(10);
                o.reducirEnergia(10);
                System.out.println("Carnívoro caza herbívoro. Energía: " + getNivelDeEnergia());
            }
        }

        @Override
        public void actuar() {
            System.out.println("Carnívoro buscando presa...");
        }
    }

    class SimulacionEcosistema {
        public static void main(String[] args) {
            Planta planta = new Planta();
            Herbivoro herbivoro = new Herbivoro(20);
            Carnivoro carnivoro = new Carnivoro(30);

            for (int i = 1; i <= 5; i++) {
                System.out.println("\nCiclo " + i + ":");
                planta.actuar();
                herbivoro.consumir(planta);
                carnivoro.consumir(herbivoro);
            }
        }
    }

