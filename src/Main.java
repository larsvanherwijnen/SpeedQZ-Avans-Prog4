import controller.MainController;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws Exception {
        new MainController().run(args);
    }
}
