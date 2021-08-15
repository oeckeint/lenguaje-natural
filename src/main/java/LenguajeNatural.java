// Author Mario Alfredo Rodríguez Paredes
import java.time.LocalDateTime;
import java.util.*;

public class LenguajeNatural {

    private static String nombreUsuario;
    private static String nombreAplicacion;
    private static Scanner scanner;

    public LenguajeNatural(String nombreUsuario) {
        System.out.println("¡Mucho gusto " + nombreUsuario + "!");
        LenguajeNatural.nombreUsuario = nombreUsuario;
        LenguajeNatural.nombreAplicacion = this.getClass().getName();
        LenguajeNatural.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        System.out.println("¡Hola!, ¿Cuál es su nombre?");
        LenguajeNatural ln = new LenguajeNatural(new Scanner(System.in).nextLine());
        System.out.print("\nEscriba algo para comenzar...");
        do {
            System.out.println("");
            String linea = scanner.nextLine();
            if (linea.equals("bye")) {
                System.out.println("BYE! " + ln.nombreUsuario + ",¡Que todo vaya bien!");
                break;
            }
            switch (ln.tipoOracion(linea.trim().toLowerCase())) {
                case 0:
                    System.out.println("No pude detectar el tipo de oración proporcionada");
                    break;
                case 1:
                    ln.pregunta(linea);
                    break;
                case 2:
                    ln.oracion(linea, true);
                    break;
                case 3:
                    ln.oracion(linea, false);
                    break;
                default:
                    System.out.println("Lo siento " + ln.nombreUsuario + ", no puedo procesar esa solicitud");
                    break;
            }
        } while (true);
    }

    private int tipoOracion(String linea) {
        int tipo = 0;
        if (linea.contains("?")) {
            tipo = 1;
        } else if (linea.contains(" y ") || linea.contains(" o ")) {
            tipo = 2;
        } else {
            tipo = 3;
        }
        return tipo;
    }

    private void oracion(String linea, boolean existeConector) {
        //Divide la oración ingresada en un arreglo de cadenas
        String[] oracion = linea.split(" ");

        //Verifica que la oración cumpla con al menos cuatro palabras
        if (oracion.length < 3) {
            //Si no las tiene indica ña estructura
            System.out.println("La oración no cumple con las minimas palabras, recuerde: ".toUpperCase());
            System.out.println("Articulo + Sustantivo + Verbo");
        } else {
            //Crea el objeto de la oración
            try {
                Articulo omg = new Articulo();
                //Lo envia  al metodo
                omg.ValidaArticulo(oracion[0].toLowerCase(), oracion[1].toLowerCase(), oracion[2].toLowerCase(), oracion, existeConector);
            } catch (Exception e) {
                //Indica esto en caso de que se presente un error no conocido
                System.out.println("Oh no!, algo no ha salido bien :(".toUpperCase());
                e.printStackTrace(System.out);
            }
        }
    }

    private void pregunta(String linea) {
        if (linea.toLowerCase().contains("hora")) {
            linea = "t";
        } else if (linea.toLowerCase().contains("fecha")) {
            linea = "f";
        } else if (linea.toLowerCase().contains("mes")) {
            linea = "m";
        } else if (linea.toLowerCase().contains("tu nombre")) {
            linea = "tn";
        } else if (linea.toLowerCase().contains("mi nombre")) {
            linea = "mn";
        } else if (linea.toLowerCase().contains("ubicacion") || linea.toLowerCase().contains("ubicación")) {
            linea = "u";
        } else if (linea.toLowerCase().contains("preg")) {
            linea = "d";
        }

        LocalDateTime time = LocalDateTime.now();
        ArrayList<String> meses = new ArrayList<>(12);
        meses.add("Enero");
        meses.add("Febrero");
        meses.add("Marzo");
        meses.add("Abril");
        meses.add("Mayo");
        meses.add("Junio");
        meses.add("Julio");
        meses.add("Agosto");
        meses.add("Septiembre");
        meses.add("Octubre");
        meses.add("Noviembre");
        meses.add("Diciembre");

        switch (linea) {
            case "t":
                System.out.println("Hora actual : " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
                break;
            case "f":
                System.out.println("Fecha actual : " + time.getDayOfMonth() + "/" + meses.get(time.getMonthValue() - 1) + "/" + time.getYear());
                break;
            case "m":
                System.out.println("Mes Actual : " + meses.get(time.getMonthValue()));
                break;
            case "tn":
                System.out.println("Me puedes llamar " + LenguajeNatural.nombreAplicacion);
                break;
            case "mn":
                System.out.println("¡Eso es fácil! tu nombre es " + LenguajeNatural.nombreUsuario);
                break;
            case "u":
                System.out.println("Tu ubicación actual es Puebla Pue. de Zaragoza");
                break;
            case "d":
                System.out.println("Las preguntas que me puedes hacer son: \n"
                        + "¿Cuál es la hora?\n"
                        + "¿Cuál es la fecha?\n"
                        + "¿Cuál es tu nombre?\n"
                        + "¿Cuál es mi nombre?\n"
                        + "¿Cuál es mi ubicación?\n"
                        + "NOTA: tambien puedes usar las palabras clave seguido terminando con \"?\"");
                break;
            default:
                System.out.println("Lo siento " + LenguajeNatural.nombreUsuario + ", no puedo comprender esa pregunta");
                System.out.println("puedes consultar las preguntas que me puedes hacer con el comando \"preg?\"");
                break;
        }
    }

}
