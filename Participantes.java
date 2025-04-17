package Ejercicio4;

abstract class Participante {
    private String id;
    private String nombre;

    public Participante(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void evaluarDesempeno();
}
class Estudiante extends Participante {
    private double[] calificaciones;

    public Estudiante(String id, String nombre, double[] calificaciones) {
        super(id, nombre);
        this.calificaciones = calificaciones;
    }

    private double calcularPromedio() {
        double suma = 0;
        for (double nota : calificaciones) {
            if (nota < 0) nota = 0; // Validación de nota no negativa
            suma += nota;
        }
        return suma / calificaciones.length;
    }

    @Override
    public void evaluarDesempeno() {
        System.out.println("Estudiante " + getNombre() + " - Promedio: " + calcularPromedio());
    }
}

class Profesor extends Participante {
    private double salario;
    private String[] cursos;

    public Profesor(String id, String nombre, double salario, String[] cursos) {
        super(id, nombre);
        this.salario = salario;
        this.cursos = cursos;
    }

    @Override
    public void evaluarDesempeno() {
        System.out.println("Profesor " + getNombre() + " - Cursos asignados: " + cursos.length);
    }
}

class Administrativo extends Participante {
    private double salario;
    private String departamento;

    public Administrativo(String id, String nombre, double salario, String departamento) {
        super(id, nombre);
        this.salario = salario;
        this.departamento = departamento;
    }

    @Override
    public void evaluarDesempeno() {
        System.out.println("Administrativo " + getNombre() + " - Departamento: " + departamento);
    }
}

    class SimulacionAcademica {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante("E001", "Ana Pérez", new double[]{4.5, 3.8, 4.2});
        Profesor profesor = new Profesor("P001", "Carlos Ruiz", 3200.0, new String[]{"Matemáticas", "Física"});
        Administrativo admin = new Administrativo("A001", "Laura Gómez", 2500.0, "Recursos Humanos");

        Participante[] participantes = {estudiante, profesor, admin};

        for (Participante p : participantes) {
            p.evaluarDesempeno();
        }
    }
}
