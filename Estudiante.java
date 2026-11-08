public class Estudiante {
    private String nombre;
    private String apellido;
    private String dni;

    public Estudiante(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public boolean esValido() {
        return nombre != null && !nombre.isEmpty() && 
               apellido != null && !apellido.isEmpty();
    }

    public String getDatosCompletos() {
        return nombre + " " + apellido + " (DNI: " + dni + ")";
    }
}