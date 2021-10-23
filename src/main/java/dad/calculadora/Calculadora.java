package dad.calculadora;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


public class Calculadora extends Application {

    private TextField realABText;
    private TextField imaginarioABText;
    private TextField realCDText;
    private TextField imaginarioCDText;
    private TextField resultadoRealText;
    private TextField resultadoImaginarioText;

    private String suma = "+";
    private String resta = "-";
    private String multiplicacion = "*";
    private String division = "/";

    private String[] operaciones = {suma, resta, multiplicacion, division};
    private ComboBox <String> operacionesCombo;

    private NumComplejo primerNum;
    private NumComplejo segundoNum;
    private NumComplejo resultado;

    private Separator separator;

    //private Label operacionLabel1;
    //private Label operacionLabel2;
    //private Label operacionLabel3;

    @Override
    public void start(Stage stage) throws Exception {
        realABText = new TextField("0");
        realABText.setMaxWidth(50);
        realABText.setAlignment(Pos.CENTER);

        imaginarioABText = new TextField("0");
        imaginarioABText.setMaxWidth(50);
        imaginarioABText.setAlignment(Pos.CENTER);

        realCDText = new TextField("0");
        realCDText.setMaxWidth(50);
        realCDText.setAlignment(Pos.CENTER);

        imaginarioCDText = new TextField("0");
        imaginarioCDText.setMaxWidth(50);
        imaginarioCDText.setAlignment(Pos.CENTER);

        resultadoRealText = new TextField("0");
        resultadoRealText.setMaxWidth(50);
        resultadoRealText.setAlignment(Pos.CENTER);
        resultadoRealText.setDisable(true);

        resultadoImaginarioText = new TextField("0");
        resultadoImaginarioText.setMaxWidth(50);
        resultadoImaginarioText.setAlignment(Pos.CENTER);
        resultadoImaginarioText.setDisable(true);

        operacionesCombo = new ComboBox<>();
        operacionesCombo.getItems().addAll(operaciones);
        operacionesCombo.getSelectionModel().select(suma);

        separator = new Separator();
        separator.setMaxWidth(130);

        primerNum = new NumComplejo();
        segundoNum = new NumComplejo();
        resultado = new NumComplejo();

        //si quisiera bindear el operador del combobox a un label
        /*operacionLabel1 = new Label(" ");
        operacionLabel1.textProperty().bind(operacionesCombo.getSelectionModel().selectedItemProperty());
        operacionLabel2 = new Label(" ");
        operacionLabel2.textProperty().bind(operacionesCombo.getSelectionModel().selectedItemProperty());
        operacionLabel3 = new Label(" ");
        operacionLabel3.textProperty().bind(operacionesCombo.getSelectionModel().selectedItemProperty());*/


        VBox operacionesVBox = new VBox(5, operacionesCombo);
        operacionesVBox.setAlignment(Pos.CENTER);

        HBox operacionRealHbox = new HBox(5, realABText, new Label(" + "), imaginarioABText, new Label(" i"));
        operacionRealHbox.setAlignment(Pos.CENTER);

        HBox operacionImaginariaHbox = new HBox(5, realCDText, new Label(" + "), imaginarioCDText, new Label(" i"));
        operacionImaginariaHbox.setAlignment(Pos.CENTER);

        HBox resultadoHbox = new HBox(5, resultadoRealText, new Label(" + "), resultadoImaginarioText, new Label(" i"));
        resultadoHbox.setAlignment(Pos.CENTER);

        VBox calculadoraVbox = new VBox(5, operacionRealHbox, operacionImaginariaHbox, separator, resultadoHbox);
        calculadoraVbox.setAlignment(Pos.CENTER);

        HBox root = new HBox(5, operacionesVBox, calculadoraVbox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 320, 200);

        stage.setTitle("CalculadoraView.fxml");
        stage.setScene(scene);
        stage.show();

        realABText.textProperty().bindBidirectional(primerNum.realProperty(), new NumberStringConverter());
        imaginarioABText.textProperty().bindBidirectional(primerNum.imaginariaProperty(), new NumberStringConverter());
        realCDText.textProperty().bindBidirectional(segundoNum.realProperty(), new NumberStringConverter());
        imaginarioCDText.textProperty().bindBidirectional(segundoNum.imaginariaProperty(), new NumberStringConverter());

        resultadoRealText.textProperty().bindBidirectional(resultado.realProperty(), new NumberStringConverter());
        resultadoImaginarioText.textProperty().bindBidirectional(resultado.imaginariaProperty(), new NumberStringConverter());

        operacionesCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> operaciones());

    }

    private void operaciones() {
        if(operacionesCombo.getSelectionModel().getSelectedItem().equals("+")){

            resultado.realProperty().bind(primerNum.realProperty().add(segundoNum.realProperty()));
            resultado.imaginariaProperty().bind(primerNum.imaginariaProperty().add(segundoNum.imaginariaProperty()));

        }else if(operacionesCombo.getSelectionModel().getSelectedItem().equals("-")){

            resultado.realProperty().bind(primerNum.realProperty().subtract(segundoNum.realProperty()));
            resultado.imaginariaProperty().bind(primerNum.imaginariaProperty().subtract(segundoNum.imaginariaProperty()));

        }else if(operacionesCombo.getSelectionModel().getSelectedItem().equals("*")){

            resultado.realProperty().bind((primerNum.realProperty().multiply(segundoNum.realProperty()).
                    subtract(primerNum.imaginariaProperty().multiply(segundoNum.imaginariaProperty()))));

            resultado.imaginariaProperty().bind((primerNum.realProperty().multiply(segundoNum.imaginariaProperty()).
                    add(primerNum.imaginariaProperty().multiply(segundoNum.realProperty()))));

        }else if(operacionesCombo.getSelectionModel().getSelectedItem().equals("/")){

            resultado.realProperty().bind(((primerNum.realProperty().multiply(segundoNum.realProperty())).
                    add(primerNum.imaginariaProperty().multiply(segundoNum.imaginariaProperty()))).divide(
                            (segundoNum.realProperty().multiply(segundoNum.realProperty())).add(segundoNum.imaginariaProperty().
                                    multiply(segundoNum.imaginariaProperty()))));

            resultado.imaginariaProperty().bind(((primerNum.imaginariaProperty().multiply(segundoNum.realProperty())).
                    subtract(primerNum.realProperty().multiply(segundoNum.imaginariaProperty()))).divide(
                    (segundoNum.realProperty().multiply(segundoNum.realProperty())).add(segundoNum.imaginariaProperty().
                            multiply(segundoNum.imaginariaProperty()))));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
