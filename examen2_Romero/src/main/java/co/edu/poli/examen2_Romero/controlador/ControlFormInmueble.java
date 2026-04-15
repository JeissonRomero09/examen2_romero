package co.edu.poli.examen2_Romero.controlador;

import co.edu.poli.examen2_Romero.modelo.*;
import co.edu.poli.examen2_Romero.servicios.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class ControlFormInmueble {

    // ================= FXML =================

    @FXML private TextField txtNumero;
    @FXML private DatePicker dpFecha;

    @FXML private TextField txtPropietarioId;
    @FXML private TextField txtPropietarioNombre;
    @FXML private TextField txtExtra;

    @FXML private TextField txtNumeroConsulta;

    @FXML private RadioButton rbCasa;
    @FXML private RadioButton rbApartamento;
    @FXML private ToggleGroup tipo;

    @FXML private TextArea txtResultado;

    // ================= DAO =================

    private DAOPropietario daoP;
    private DAOInmueble daoI;

    // ================= INIT =================

    @FXML
    private void initialize() {

        daoP = new DAOPropietario();
        daoI = new DAOInmueble();

        rbCasa.setToggleGroup(tipo);
        rbApartamento.setToggleGroup(tipo);

        rbCasa.setSelected(true);
    }

    // ================= CONSULTAR =================

    @FXML
    public void consultar() {

        String numero = txtNumeroConsulta.getText().trim();

        if (numero.isEmpty()) {
            mostrar("Ingrese número de inmueble");
            return;
        }

        try {

            Inmueble i = daoI.readOne(numero);

            if (i == null) {
                txtResultado.setText("❌ No se encontró el inmueble con número: " + numero);
                return;
            }

            String tipoInmueble = (i instanceof Casa) ? "Casa" : "Apartamento";

            String extra = "";

            if (i instanceof Casa) {

                Casa c = (Casa) i;
                extra = c.getCantidadPisos() + " pisos";

            } else if (i instanceof Apartamento) {

                Apartamento a = (Apartamento) i;
                extra = "Piso " + a.getNumeroPiso();
            }

            String info =
                    " INMUEBLE ENCONTRADO\n\n" +
                    "Número: " + i.getNumero() + "\n" +
                    "Fecha compra: " + i.getFechaCompra() + "\n" +
                    "Tipo: " + tipoInmueble + "\n" +
                    "Propietario: " + i.getPropietario().getNombre() +
                    " (" + i.getPropietario().getId() + ")\n" +
                    "Extra: " + extra + "\n" +
                    "Estado: " + (i.isEstado() ? "Activo" : "Inactivo");

            txtResultado.setText(info);

        } catch (Exception e) {
            txtResultado.setText("Error en consulta: " + e.getMessage());
        }
    }

    // ================= GUARDAR =================

    @FXML
    public void guardar(ActionEvent event) {

        String numero = txtNumero.getText().trim();

        String fecha = (dpFecha.getValue() != null)
                ? dpFecha.getValue().toString()
                : "";

        String id = txtPropietarioId.getText().trim();
        String nombre = txtPropietarioNombre.getText().trim();
        String extra = txtExtra.getText().trim();

        if (numero.isEmpty() || fecha.isEmpty() || id.isEmpty() || nombre.isEmpty() || extra.isEmpty()) {
            mostrar("❌ Complete todos los campos");
            return;
        }

        try {

            int valorExtra = Integer.parseInt(extra);

            // ================= PROPIETARIO =================
            Propietario p = new Propietario(id, nombre);
            daoP.create(p);

            // ================= INMUEBLE =================
            Inmueble inmueble;

            if (rbCasa.isSelected()) {
                inmueble = new Casa(numero, fecha, true, p, valorExtra);
            } else {
                inmueble = new Apartamento(numero, fecha, true, p, valorExtra);
            }

            String res = daoI.create(inmueble);

            if (res.equalsIgnoreCase("OK")) {

                mostrar("✔ Guardado exitosamente\nNúmero: " + numero);
                limpiar();

            } else {
                mostrar("❌ Error: " + res);
            }

        } catch (NumberFormatException e) {
            mostrar("❌ El campo extra debe ser numérico");
        } catch (Exception e) {
            mostrar("Error: " + e.getMessage());
        }
    }

    // ================= VENDER =================

    @FXML
    public void venderInmueble() {

        String numero = txtNumeroConsulta.getText().trim();

        if (numero.isEmpty()) {
            mostrar("Ingrese número");
            return;
        }

        Inmueble i = daoI.readOne(numero);

        if (i == null) {
            mostrar("No existe el inmueble");
            return;
        }

        if (!i.isEstado()) {
            mostrar("El inmueble ya está vendido");
            return;
        }

        String msg = i.vender();
        daoI.update(i);

        mostrar(msg);
    }

    // ================= ALQUILAR =================

    @FXML
    public void alquilarInmueble() {

        String numero = txtNumeroConsulta.getText().trim();

        if (numero.isEmpty()) {
            mostrar("Ingrese número");
            return;
        }

        Inmueble i = daoI.readOne(numero);

        if (i == null) {
            mostrar("No existe el inmueble");
            return;
        }

        String msg = i.alquilar();
        daoI.update(i);

        mostrar(msg);
    }

    // ================= ALERTA =================

    private void mostrar(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sistema Inmuebles");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // ================= LIMPIAR =================

    private void limpiar() {

        txtNumero.clear();
        dpFecha.setValue(null);

        txtPropietarioId.clear();
        txtPropietarioNombre.clear();
        txtExtra.clear();

        rbCasa.setSelected(true);
    }
}