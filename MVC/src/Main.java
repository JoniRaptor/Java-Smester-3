public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();

        controller.initialize(view,model);
        view.initialize(model,controller);



    }
}