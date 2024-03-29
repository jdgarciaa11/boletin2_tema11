package Principal;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GestionClientes {


    static Scanner sc = new Scanner(System.in);
    public boolean append = false;

    /**
     * Metodo que registrará un cliente en el final del fichero CLIENTE.txt
     *
     * @param fichero
     * @param cliente
     */

    public void altaCliente(File fichero, Cliente cliente) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));

            bw.write(cliente.getCif() + ",");
            bw.write(cliente.getNombre() + ",");
            bw.write(cliente.getApellido() + ",");
            bw.write(cliente.getCategoria() + ",");
            bw.write(cliente.getDireccion());
            bw.newLine();

            System.out.println(cliente.toString());

            bw.close();

        } catch (Exception e) {
            System.out.println("Error al crear el fichero" + e.getMessage());
        }

    }

    /**
     *
     * @param fichero
     * @param cliente
     * @param flag
     */

    public void altaClienteOrdenado(File fichero, Cliente cliente, boolean flag) {

        try {
            BufferedWriter bw = null;

            if (flag) {
                bw = new BufferedWriter(new FileWriter(fichero, true));
            } else {
                bw = new BufferedWriter(new FileWriter(fichero));
            }
            bw.write(cliente.getCif() + ",");
            bw.write(cliente.getNombre() + ",");
            bw.write(cliente.getApellido() + ",");
            bw.write(cliente.getCategoria() + ",");
            bw.write(cliente.getDireccion());
            bw.newLine();

            System.out.println(cliente.toString());
            bw.close();

        } catch (Exception e) {
            System.out.println("Error al crear el fichero" + e.getMessage());
        }
    }

    /**
     *
     * @param cliente
     * @param altas
     */

    public void realizarAltas(File cliente, File altas) {

    }

    /**
     * Método por el cual se mostrará el cliente que el usuario elija cuando el mismo
     * pase por teclado el apellido del cliente.
     *
     * @param fichero
     * @param apellidos
     */

    public void consultarElementoPorApellidos(File fichero, String apellidos) {

        try {
            String apellido;
            apellidos = apellidos + ",";
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                sc = new Scanner(linea);
                if (sc.hasNext()) {
                    sc.next();
                }
                if (sc.hasNext()) {
                    apellido = sc.next();
                    if (apellido.equals(apellidos)) {
                        System.out.println(linea);
                    }
                }
                linea = br.readLine();
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("Error!!" + e.getMessage());
        }
    }

    public String buscarElementoPorApellidos(File fichero, String apellidos) {
        return "holi";
    }

    public void consultarElementoPorCif(File fichero, String cif) {

    }

    public String buscarElementoPorCif(File fichero, String cif) {
        return "holi";
    }

    public void insertarModificacionCliente(File fichero, Cliente cliente) {

    }

    public void realizarModificaciones(File clientes, File modificaciones) {

    }

    public void modificarElemento(File fichero, String registroViejo, Cliente registroNuevo) {

    }

    public void insertarBajaClientes(File fichero, Cliente cli) {

    }

    public void realizarBajas(File clientes, File bajas) {

    }

    public void bajaElemento(File fichero, String registro) {

    }


    /**
     * Metodo que transforma un registro cliente que es un String a un objeto cliente
     * usando el metodo .split()
     *
     * @param registro
     * @return
     */
    public Cliente transformarAObjeto(String registro) {

        String[] clienteText = registro.split(",");
        return new Cliente(clienteText[0],clienteText[1],clienteText[2], Integer.parseInt(clienteText[3]), clienteText[4]);
    }

    /**
     * Método que va a ordenar el fichero de texto según el compareTo que está creado en
     * la clase Cliente.
     *
     * @param fichero
     */

    public void ordenarFichero(File fichero) {

        ArrayList<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            String linea = br.readLine();
            while (linea != null) {
                clientes.add(transformarAObjeto(linea));
                linea = br.readLine();
            }
            br.close();
            System.out.println("cierra archivo");

            Collections.sort(clientes, Cliente::compareTo);

            this.append = false;
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println(clientes.get(i).toString());
                if (i > 0) {
                    this.append = true;
                }

                altaClienteOrdenado(fichero, clientes.get(i), this.append);
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
