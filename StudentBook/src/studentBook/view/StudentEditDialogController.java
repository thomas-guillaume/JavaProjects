package studentBook.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studentBook.model.Student;

// Dialog to edit details of a person.
public class StudentEditDialogController {

	@FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField groupIdField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField markField;
    @FXML
    private TextField commentField;


    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    // Initializes the controller class. This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize() {
    }

    // Sets the stage of this dialog.
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Sets the student to be edited in the dialog.
    public void setStudent(Student student) {
        this.student = student;

        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        groupIdField.setText(student.getGroupID());
        birthdayField.setText(student.getBirthday());
        genderField.setText(student.getGender());
        markField.setText(Integer.toString(student.getMark()));
        commentField.setText(student.getComment());
    }

    // Returns true if the user clicked OK, false otherwise.
    public boolean isOkClicked() {
        return okClicked;
    }

    // Called when the user clicks ok.
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        		
        	student.setFirstName(firstNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setGroupID(groupIdField.getText());
            student.setBirthday(birthdayField.getText());
            student.setGender(genderField.getText());
            student.setMark(Integer.parseInt(markField.getText()));
            student.setComment(commentField.getText());
            
            // Save the student in the DataBase
            //student.saveStudent();

            okClicked = true;
            dialogStage.close();
        }
    }

    // Called when the user clicks cancel.
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    // Validates the user input in the text fields.
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (groupIdField.getText() == null || groupIdField.getText().length() == 0) {
            errorMessage += "No valid groupID!\n";
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        }
        if (genderField.getText() == null || genderField.getText().length() == 0) {
            errorMessage += "No valid gender!\n";
        }
        if (markField.getText() == null || markField.getText().length() == 0) {
            errorMessage += "No valid mark!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(markField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid mark (must be an integer)!\n";
            }
        }
        if (commentField.getText() == null || commentField.getText().length() == 0) {
            errorMessage += "No valid comment!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}