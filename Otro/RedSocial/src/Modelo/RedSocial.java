package Modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class RedSocial {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    private Usuario crearUsuario() {
        System.out.println("Introduce el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el apellido del usuario: ");
        String apellido = scanner.nextLine();
        Usuario usuario = new Usuario(this, nombre, apellido);
        return usuario;
    }

    public Usuario comprobarUsuario(Usuario usuario) {
        for (Usuario user : usuarios) {
            if (user.getNombre().equals(usuario.getNombre()) && user.getApellido().equals(usuario.getApellido())) {
                return user;
            }
        }
        return null;
    }

    public void altaUsuario() {
        Usuario usuario = crearUsuario();
        Usuario user = comprobarUsuario(usuario);

        if (user != null) {
            System.out.println("Lo siento no se puede dar de alta, ya existe.");
        } else {
            usuarios.add(usuario);
            System.out.println("El usuario ha sido dado de alta correctamente.");
        }
    }

    public void bajaUsuario() {
        Usuario usuario = crearUsuario();
        Usuario user = comprobarUsuario(usuario);

        if (user == null) {
            System.out.println("Lo siento no se puede dar de baja, no existe.");
        } else {
            usuarios.remove(user);
            System.out.println("El usuario ha sido dado de baja correctamente.");
        }
    }

    public void login() {
        Usuario usuario = crearUsuario();
        Usuario user = comprobarUsuario(usuario);

        if (user == null) {
            System.out.println("Error al acceder");
        } else {
            System.out.println("Acceso concedido:" + user.getNombre() + " " + user.getApellido());
            user.menuUsuario();
        }
    }

}
