import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javax.script.*;

public class Main extends Application
{
	public ScriptEngineManager mg = new ScriptEngineManager();
	public ScriptEngine engine = mg.getEngineByName("js");
	public static void main(String[] args)
	{
		launch();
	}
	public void start(Stage s)
	{
		Scene scene = new Scene(new Group(),200,240);
		scene.getStylesheets().add("style.css");
		VBox main = new VBox();
		TextField field = new TextField(); field.setAlignment(Pos.BASELINE_RIGHT); field.setDisable(true);
		field.setFont(Font.loadFont(Main.class.getResource("DS-DIGIT.TTF").toExternalForm(), 120));
		HBox line1 = new HBox();
		HBox line2 = new HBox();
		HBox line3 = new HBox();
		HBox line4 = new HBox();
		HBox line5 = new HBox();
		Button btn1 = new Button("1"); btn1.setOnAction(new btnHandler(field,btn1.getText()));
		Button btn2 = new Button("2"); btn2.setOnAction(new btnHandler(field,btn2.getText()));
		Button btn3 = new Button("3"); btn3.setOnAction(new btnHandler(field,btn3.getText()));
		Button btn4 = new Button("4"); btn4.setOnAction(new btnHandler(field,btn4.getText()));
		Button btn5 = new Button("5"); btn5.setOnAction(new btnHandler(field,btn5.getText()));
		Button btn6 = new Button("6"); btn6.setOnAction(new btnHandler(field,btn6.getText()));
		Button btn7 = new Button("7"); btn7.setOnAction(new btnHandler(field,btn7.getText()));
		Button btn8 = new Button("8"); btn8.setOnAction(new btnHandler(field,btn8.getText()));
		Button btn9 = new Button("9"); btn9.setOnAction(new btnHandler(field,btn9.getText()));
		Button btnPls = new Button("+"); btnPls.setOnAction(new btnHandler(field,btnPls.getText())); btnPls.setId("pl_sign");
		Button btnMis = new Button("-"); btnMis.setOnAction(new btnHandler(field,btnMis.getText())); btnMis.setId("ms_sign");
		Button btnMtp = new Button("x"); btnMtp.setOnAction(new btnHandler(field,btnMtp.getText())); btnMtp.setId("mtp_sign");
		Button btnDiv = new Button("/"); btnDiv.setOnAction(new btnHandler(field,btnDiv.getText())); btnDiv.setId("div_sign");
		Button btn0 = new Button("0"); btn0.setOnAction(new btnHandler(field,btn0.getText()));
		Button btnDt = new Button("."); btnDt.setOnAction(new btnHandler(field,btnDt.getText()));
		Button btnCl = new Button("C"); btnCl.setId("clearBtn"); btnCl.setOnAction(new btnHandler(field,btnCl.getText()));
		Button btnEq = new Button("="); btnEq.setId("eqBtn"); btnEq.setOnAction(new btnHandler(field,btnEq.getText()));
		
		line1.getChildren().addAll(btn7,btn8,btn9,btnPls);
		line2.getChildren().addAll(btn4,btn5,btn6,btnMis);
		line3.getChildren().addAll(btn1,btn2,btn3,btnMtp);
		line4.getChildren().addAll(btn0,btnCl,btnDt,btnDiv);
		line5.getChildren().addAll(btnEq);
		
		
		main.getChildren().add(field);
		main.getChildren().addAll(line1,line2,line3,line4,line5);
		s.setScene(scene);
		s.setResizable(false);
		s.setTitle("Calc");
		s.show();
		((Group)scene.getRoot()).getChildren().add(new Rectangle(0,0,500,500));
		((Group)scene.getRoot()).getChildren().add(main);
	}
	class btnHandler implements EventHandler<ActionEvent>
	{
		public TextField field;
		public String number;
		public btnHandler(TextField field, String number)
		{
			this.field = field;
			this.number = number;
		}
		public void handle(ActionEvent e)
		{
			if(number.equals("C"))
			{
				if(field.getText().length()>0)
				{
					field.setText(field.getText().substring(0,field.getText().length()-1));
				}
			}
			else if(number.equals("="))
			{
				try
				{
					Object out = engine.eval(field.getText());
					try
					{
						field.setText(Integer.toString((int)out));
					}catch(Exception exc){}
					try
					{
						field.setText(Double.toString(Math.floor((double)out*1000000000)/1000000000));
					}catch(Exception exc){}
				}
				catch(ScriptException ex){ex.printStackTrace();}
			}
			else
			{
				if(number.equals("x"))
				{
					number = "*";
				}
				field.setText(field.getText()+number);
			}
		}
	}
}