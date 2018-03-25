package studentBook.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import studentBook.controller.MainApp;
import studentBook.model.Student;


public class StudentOverviewController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label groupIdLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label markLabel;
    @FXML
    private Label commentLabel;
    
    // Called when the user clicks the new button. Opens a dialog to edit
    // details for a new student.
    @FXML
    private void handleNewStudent() {
        Student tempStudent = new Student();
        boolean okClicked = mainApp.showStudentEditDialog(tempStudent);
        if (okClicked) {
            mainApp.getStudentData().add(tempStudent);
        }
    }

    // Called when the user clicks the edit button. Opens a dialog to edit
    // details for the selected person.
    @FXML
    private void handleEditStudent() {
    	Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            boolean okClicked = mainApp.showStudentEditDialog(selectedStudent);
            if (okClicked) {
                showStudentDetails(selectedStudent);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
    }
    // Called when the user clicks on the delete button.
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
    }

    // Reference to the main application.
    private MainApp mainApp;

    // The constructor is called before the initialize() method.
    public StudentOverviewController() {
    }

    // Initializes the controller class. This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        // Clear person details.
        showStudentDetails(null);
        
        // Listen for selection changes and show the person details when changed.
        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStudentDetails(newValue));
    }

    // Is called by the main application to give a reference back to itself.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        studentTable.setItems(mainApp.getStudentData());
    }
    
    // Fills all text fields to show details about the student.
    // If the specified student is null, all text fields are cleared.
    private void showStudentDetails(Student student) {
        if (student != null) {
            // Fill the labels with info from the person object.
        	firstNameLabel.setText(student.getFirstName());
            lastNameLabel.setText(student.getLastName());
            groupIdLabel.setText(student.getGroupID());
            birthdayLabel.setText(student.getBirthday());
            genderLabel.setText(student.getGender());
            markLabel.setText(Integer.toString(student.getMark()));
            commentLabel.setText(student.getComment());
        } else {
            // Person is null, remove all the text.
        	firstNameLabel.setText("");
            lastNameLabel.setText("");
            groupIdLabel.setText("");
            birthdayLabel.setText("");
            genderLabel.setText("");
            markLabel.setText("");
            commentLabel.setText("");
        }
    }
}