import controllers.BancoController;
import views.MenuView;

public class App {
    public static void main(String[] args) {
        BancoController bancoController = new BancoController();
        MenuView menuView = new MenuView(bancoController);
        menuView.mostrarMenuPrincipal();
    }
}