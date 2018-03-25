package studentBook.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studentBook.model.Student;
import studentBook.view.StudentEditDialogController;
import studentBook.view.StudentOverviewController;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane studentOverview;
    // The data as an observable list of Students.
    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    // Constructor
    public MainApp() {
        // Add some sample data
        studentData.add(new Student("Thomas", "Guillaume"));
        studentData.add(new Student("Clément", "Lefèvre"));
        studentData.add(new Student("Mélissa", "Lee"));
        studentData.add(new Student("Elisa", "Pavaroti"));
    }

    // Returns the data as an observable list of Students.
    public ObservableList<Student> getStudentData() {
        return studentData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("StudentBookApp");

        initStudentOverview();
    }

    // Initializes the student overview
    public void initStudentOverview() {
        try {
            // Load student overview from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/StudentOverview.fxml"));
            studentOverview = (AnchorPane) loader.load();
            
            // Show the scene containing the student overview.
            Scene scene = new Scene(studentOverview);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Give the controller access to the main app.
            StudentOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns the main stage.
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    // Opens a dialog to edit details for the specified student. If the user
    // clicks OK, the changes are saved into the provided student object and true
    // is returned.
    public boolean showStudentEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/StudentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            StudentEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}