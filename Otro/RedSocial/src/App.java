import java.util.Scanner;

import Modelo.RedSocial;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static RedSocial red = new RedSocial();

    public static void main(String[] args) throws Exception {
        Boolean salir = false;
        while (!salir) {
            salir = menu();
        }
    }

    private static Boolean menu() {
        Boolean salir = false;
        System.out.println("#############################");
        System.out.println("########### Menu Principal ##");
        System.out.println("#############################");
        System.out.println("1 Alta Usuario");
        System.out.println("2 Baja Usuario");
        System.out.println("3 Login Usuario");
        System.out.println("9 Salir");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                red.altaUsuario();

                break;
            case "2":
                red.bajaUsuario();
                break;
            case "3":
                red.login();
                break;
            case "9":
                System.out.println("Saliendo...");
                salir = true;
                break;

            default:
                System.out.println("opcion no válida");
                break;
        }
        return salir;

    }
}
