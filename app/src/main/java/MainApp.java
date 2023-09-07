

public class MainApp {

    public String saluda() {
        return "Hola Nuevo Mundo!";
    }

    public static void main(String[] args) {

        MainApp yo = new MainApp();
        System.out.println(yo.saluda());
    }
}
