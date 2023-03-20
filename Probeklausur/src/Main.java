public class Main {
    public static void main(String[] args) {

        View view = new View();
        Model model = new Model();
        MyCanvas canvas = new MyCanvas();

        canvas.initialize(model, view);
        view.initialize(model, canvas);
    }
}