public class Solicitud {
    private int id;
    private Estudiante estudiante; 
    private String tipo;
    private EstadoSolicitud estado; 
    private String prioridad;
    private String responsable;

    public Solicitud(int id, Estudiante estudiante, String tipo, String prioridad) {
        this.id = id;
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.responsable = "SIN_ASIGNAR";
    }
    
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return id + " - " + estudiante.getDatosCompletos() + " - " + tipo + 
               " - Estado: " + estado + " - Prioridad: " + prioridad + 
               " - Resp: " + responsable;
    }
}