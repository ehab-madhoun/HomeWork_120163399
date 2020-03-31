package fourthapplogin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FourthAppLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label welcome = new Label("Welcome");
        welcome.setId("welcome");

        Label username = new Label("User Name ");
        TextField name_field = new TextField();
        HBox username_box = new HBox(10, username, name_field);
        username_box.setAlignment(Pos.CENTER);

        Label Passowrd = new Label("Passowrd ");
        TextField Passowrd_field = new TextField();
        HBox Passowrd_box = new HBox(10, Passowrd, Passowrd_field);
        Passowrd_box.setAlignment(Pos.CENTER);

        Button signin = new Button("Sign In");
        Button exit = new Button("Exit");
        HBox signin_exit_box = new HBox(10, signin, exit);
        signin.setId("button");
        exit.setId("button");
        signin_exit_box.setAlignment(Pos.CENTER);

        GridPane gridPane_login = new GridPane();
        gridPane_login.add(welcome, 1, 0);
        gridPane_login.add(username_box, 1, 1);
        gridPane_login.add(Passowrd_box, 1, 2);
        gridPane_login.add(signin_exit_box, 1, 3);
        gridPane_login.setAlignment(Pos.CENTER);
        gridPane_login.setVgap(20);
        Scene Login = new Scene(gridPane_login, 800, 400);
        Login.getStylesheets().add("style.css");
        primaryStage.setTitle("Login Page");

        signin.setOnAction(sign -> {
            File validationFile;
            Scanner input;
            try {
                validationFile = new File("validationFile.txt");
                if (!validationFile.exists()) {
                    validationFile.createNewFile();
                }
                input = new Scanner(validationFile);
                boolean Found = false;

                while (input.hasNextLine()) {
                    String[] validate = input.nextLine().split(" ");
                    if (name_field.getText().equals(validate[0]) && Passowrd_field.getText().equals(validate[1])) {
                        Found = true;
                        System.out.println("isValidate");
                        Button add = new Button("Add Student");
                        Button view = new Button("View Student");
                        add.setId("button");
                        view.setId("button");
                        VBox addAndView = new VBox(6, add, view);
                        addAndView.setAlignment(Pos.CENTER);
                        Scene IntirnalLogin = new Scene(addAndView, 800, 400);
                        IntirnalLogin.getStylesheets().add("style.css");
                        primaryStage.setTitle("Options Page");
                        primaryStage.setScene(IntirnalLogin);

                        add.setOnAction(addStd -> {
                            primaryStage.setTitle("Student Entry Page");

                            Label stdData = new Label("Student Data");
                            HBox box_stdData = new HBox(stdData);

                            stdData.setAlignment(Pos.CENTER);
                            box_stdData.setAlignment(Pos.CENTER);
                            stdData.setId("wlcom");

                            Label id = new Label("Id:");
                            TextField textField_Id = new TextField();
                            HBox box_id = new HBox(10, id, textField_Id);
                            box_id.setAlignment(Pos.CENTER);

                            Label name = new Label("Name:");
                            TextField textField_Name = new TextField();
                            HBox box_name = new HBox(10, name, textField_Name);
                            box_name.setAlignment(Pos.CENTER);

                            Label major = new Label("Major");
                            TextField textField_Major = new TextField();
                            HBox box_major = new HBox(10, major, textField_Major);
                            box_major.setAlignment(Pos.CENTER);

                            Label grade = new Label("Grade:");
                            TextField textField_Grade = new TextField();
                            HBox box_grade = new HBox(10, grade, textField_Grade);
                            box_grade.setAlignment(Pos.CENTER);

                            Button btn_Add = new Button("Add");
                            btn_Add.setId("button");
                            Button btn_Reset = new Button("Reset");
                            btn_Reset.setId("button");
                            Button btn_Exit = new Button("Exit");
                            btn_Exit.setId("button");

                            HBox box_Add_Reset_Exit = new HBox(10, btn_Add, btn_Reset, btn_Exit);
                            box_Add_Reset_Exit.setAlignment(Pos.CENTER);

                            ListView listView_Std = new ListView();
                            listView_Std.setPrefHeight(300);
                            listView_Std.setPrefWidth(350);

                            GridPane std_Pane = new GridPane();

                            VBox box_allOfControl = new VBox(10, box_id, box_name, box_major, box_grade, box_Add_Reset_Exit);

                            std_Pane.add(stdData, 1, 0);
                            std_Pane.add(box_allOfControl, 1, 1);
                            std_Pane.add(listView_Std, 2, 1);

                            std_Pane.setHgap(20);
                            std_Pane.setVgap(10);
                            std_Pane.setAlignment(Pos.CENTER);

                            Scene std_Entry = new Scene(std_Pane, 800, 400);
                            std_Entry.getStylesheets().add("style.css");
                            primaryStage.setScene(std_Entry);

                            btn_Add.setOnAction(ad -> {
                                Student std = new Student();
                                try {
                                    std.setId(Integer.parseInt(textField_Id.getText()));
                                    std.setName(textField_Name.getText());
                                    std.setMajor(textField_Major.getText());
                                    std.setGrade(Double.parseDouble(textField_Grade.getText()));

                                } catch (Exception e) {
                                    System.out.println("This Is Exception");
                                }
                                listView_Std.getItems().addAll(std);

                                Collections.sort(listView_Std.getItems(), new Comparator<Student>() {
                                    @Override
                                    public int compare(Student t, Student t1) {
                                        if (t.getGrade() == t1.getGrade()) {
                                            return 0;
                                        } else if (t.getGrade() > t1.getGrade()) {
                                            return -1;
                                        } else if (t.getGrade() < t1.getGrade()) {
                                            return 1;
                                        }
                                        return 0;
                                    }
                                });

                                btn_Reset.setOnAction(reset -> {
                                    textField_Id.setText(null);
                                    textField_Name.setText(null);
                                    textField_Major.setText(null);
                                    textField_Grade.setText(null);
                                });

                                btn_Exit.setOnAction(value -> {
                                    primaryStage.close();
                                });
                            });
                        }
                        );
                    }
                }
                if (!Found) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Your Acount not Saved in validationFile", ButtonType.OK);
                    alert.show();

                }
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Created .. Plz creat File .. ");
            } catch (IOException ex) {
                System.out.println("Error in I/O ");
            }

        });

        exit.setOnAction(ex -> {
            primaryStage.close();
        });

        primaryStage.setScene(Login);
        primaryStage.show();
    }

}
