public class Main {
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller();

        controller.initialize(model, view);
        view.initialize(controller, model);
    }
}