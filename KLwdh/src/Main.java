public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller c = new Controller();
        Model m = new Model();

        c.initialize(m, view);

        view.initialize(m, c);
    }
}