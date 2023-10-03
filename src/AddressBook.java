import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

//Martha Rdz
public class AddressBook {
    public static void main(String[] args) {

        HashMap<String, String> mapCon = new HashMap<String, String>();
        Scanner teclado = new Scanner(System.in);

        String op;
        int  ban = 0;
        String telefono, nombre;
        System.out.println("**Agenda Telefonica**");

        do {
            try{
                System.out.println("Selecciona la opción que deseas realizar");
                System.out.println("1.Cargar Contactos");
                System.out.println("2.Guardar Contactos");
                System.out.println("3.Mostrar Contactos");
                System.out.println("4.Agrgar Contacto");
                System.out.println("5.Borrar Contacto");
                System.out.println("6.Salir");
                op = teclado.nextLine();

                switch (op) {
                    case "1":
                        load(mapCon);
                        break;
                    case "2":
                        save(mapCon);

                        break;
                    case "3":
                        list(mapCon);

                        break;
                    case "4":
                        System.out.println("\n*** Nuevo contacto a la agenda");
                        System.out.println("\nIntroduzca el teléfono:");
                        telefono = teclado.nextLine();
                        System.out.println("\nIntroduzca el nombre:");
                        nombre = teclado.nextLine();

                        create(mapCon, telefono, nombre);

                        break;
                    case "5":

                        System.out.println("\n*** Eliminar contacto a la agenda");
                        System.out.println("\nIntroduzca el teléfono:");
                        telefono = teclado.nextLine();
                        delete(mapCon, telefono);

                        break;
                    case "6":
                        System.out.println("Gracias por usar nuestra agenda");
                        System.exit(0);
                        break;
                }
            } catch (Exception e){
                System.out.println("Error");
                break;
            }

        }while (ban==0);

    }

    public static void list(HashMap<String, String> mapCon){
        Iterator<String> iterator = mapCon.keySet().iterator();

        System.out.println("Contactos: \n");
        System.out.println("Teléfono\t|\tNombre");
        System.out.println("---------------------");

        while (iterator.hasNext()){
            String llave = iterator.next();
            System.out.println("   "+llave+"\t|\t"+mapCon.get(llave));
        }
    }

    public static void create (HashMap<String,String> mapaCon, String tel, String nom){

            if (mapaCon.containsKey(tel)){
                System.out.println("\nError! Telefono registrado anteriormente");
            }
            else {
                mapaCon.put(tel, nom);
                System.out.println("Contacto agregado");
            }

    }

    public static void delete(HashMap<String, String> mapaCon, String tel){
        if (mapaCon.containsKey(tel)){
            System.out.println("\nContacto eliminado: "+mapaCon.get(tel)+"\n");
            mapaCon.remove(tel);
        } else {
            System.out.println("\nEl telefono no existe");
        }
    }
    public static void load(HashMap<String, String> m){
        String inputFilename = "C:\\Users\\USER\\IdeaProjects\\AddressBook\\src\\Contactos\\input.csv";
        String a [];

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader (new FileReader(inputFilename));

            String line;
            while ((line=bufferedReader.readLine())!= null){
                a = line.split(",");
                m.put(a[0],a[1]);

            }
        } catch (IOException e){
            System.out.println("IOExpection catched while reading: "+e.getMessage());
        } finally {
            try{
                if (bufferedReader != null);
                bufferedReader.close();
                System.out.println("Contactos cargados");
            } catch (IOException e){
                System.out.println("IOException catched while closing"+e.getMessage());
            }
        }
    }
    public static void save(HashMap<String,String>m){

        Iterator<String> iterator = m.keySet().iterator();


        String imputFilename = "C:\\Users\\USER\\IdeaProjects\\AddressBook\\src\\Contactos\\input.csv";

        BufferedWriter bufferedWriter = null;

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(imputFilename));

            while (iterator.hasNext()){
                String llave = iterator.next();
                bufferedWriter.write(llave+","+m.get(llave)+"\n");
            }

            bufferedWriter.close();

        }catch (IOException e){
            System.out.println("IOException catched while writing: "+e.getMessage());
        }
    }
}