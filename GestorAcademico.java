import java.util.ArrayList;
import java.util.List;

public class GestorAcademico {
    // SMELL: Primitive Obsession (Guardamos todo como un String largo concatenado)
    private List<String> bdSolicitudes = new ArrayList<>();
    private int contadorId = 1;

    // SMELL: God Class (hace TODO) y Long Method
    // SMELL: Data Clumps (n, a, dni viajan siempre sueltos en vez de usar un objeto Estudiante)
    public void procesarSolicitud(String n, String a, String dni, String tipo, boolean urg) {
        
        // SMELL: Duplicated Code (mala validación)
        if (n == null || n.equals("")) {
            System.out.println("Falta nombre");
        }
        if (a == null || a.equals("")) {
            System.out.println("Falta apellido");
        }

        // SMELL: Magic Strings (textos sueltos fáciles de tipear mal)
        String estado = "PENDIENTE";
        String prioridad = "NORMAL";

        // SMELL: Deeply Nested Control Flow (Código en flecha) + redundancia booleana
        if (urg == true) {
            if (tipo.equals("CERTIFICADO")) {
                prioridad = "ALTA";
            } else {
                if (tipo.equals("EXAMEN")) {
                    prioridad = "MEDIA";
                } else {
                    prioridad = "BAJA";
                }
            }
        }

        // Atado con alambre
        String s = contadorId + "-" + n + "-" + a + "-" + tipo + "-" + estado + "-" + prioridad + "-SIN_ASIGNAR";
        bdSolicitudes.add(s);
        System.out.println("Se guardó: " + s);
        contadorId++;
    }

    // Operación 2: Cambiar estado
    public void cambiar(int id, String e) { // SMELL: Poor naming ('e' en vez de 'nuevoEstado')
        for (int i = 0; i < bdSolicitudes.size(); i++) {
            String temp = bdSolicitudes.get(i);
            if (temp.startsWith(id + "-")) {
                String[] partes = temp.split("-");
                partes[4] = e; // Modifica el estado rompiendo el string y volviéndolo a armar
                String nueva = partes[0]+"-"+partes[1]+"-"+partes[2]+"-"+partes[3]+"-"+partes[4]+"-"+partes[5]+"-"+partes[6];
                bdSolicitudes.set(i, nueva);
                System.out.println("Actualizado: " + nueva);
            }
        }
    }

    // Operación 3: Asignar responsable
    public void asignarResp(int id, String r) {
        for (int i = 0; i < bdSolicitudes.size(); i++) {
            String temp = bdSolicitudes.get(i);
            if (temp.startsWith(id + "-")) {
                String[] partes = temp.split("-");
                partes[6] = r;
                String nueva = partes[0]+"-"+partes[1]+"-"+partes[2]+"-"+partes[3]+"-"+partes[4]+"-"+partes[5]+"-"+partes[6];
                bdSolicitudes.set(i, nueva);
            }
        }
    }

    // Operación 4: Listar reporte básico
    public void verTodas() {
        System.out.println("--- LISTA DE SOLICITUDES ---");
        for (String x : bdSolicitudes) {
            System.out.println(x);
        }
    }

    // Main para probar que funciona externamente
    public static void main(String[] args) {
        GestorAcademico gestor = new GestorAcademico();
        
        gestor.procesarSolicitud("Juan", "Perez", "12345678", "CERTIFICADO", true);
        gestor.procesarSolicitud("Maria", "Gomez", "87654321", "EXAMEN", false);

        gestor.cambiar(1, "EN_PROCESO");
        gestor.asignarResp(1, "Secretaria_Alumnos");

        gestor.verTodas();
    }
}