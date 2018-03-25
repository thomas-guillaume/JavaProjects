package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class StudentController {
	
	@FXML
	TextField textname;
	
	@FXML
	ComboBox<String> boxgender;
	
	@FXML
	Button buttonsave;
	
	@FXML
	Button buttonclear;
	
	@FXML
	Label labelsave;
	
	public void saveStudent(ActionEvent e){
		Student student= new Student(textname.getText(), boxgender.getValue().toString());
		student.saveStudent();
		labelsave.setText("Saved");
	}
	
	public void clearData(){
		boxgender.setValue(null);
		textname.setText("");
		labelsave.setText("");
	}
}
