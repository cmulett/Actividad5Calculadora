package com.example.calculadoraprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView screen;
private String input="",Answer;
private boolean clearResult;
private Button AC,Power,Back,Div,Multi,Menos,Igual,Suma,Uno,Dos,Tres,Cuatro,Cinco,Seis,Siete,Ocho,Nueve,Cero,Ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen=findViewById(R.id.screen);
        AC=findViewById(R.id.ac);
        Power=findViewById(R.id.power);
        Back=findViewById(R.id.back);
        Div=findViewById(R.id.div);
        Siete=findViewById(R.id.siete);
        Ocho=findViewById(R.id.ocho);
        Nueve=findViewById(R.id.nueve);
        Multi=findViewById(R.id.multi);
        Cuatro=findViewById(R.id.cuatro);
        Cinco=findViewById(R.id.cinco);
        Seis=findViewById(R.id.seis);
        Menos=findViewById(R.id.menos);
        Uno=findViewById(R.id.uno);
        Dos=findViewById(R.id.dos);
        Tres=findViewById(R.id.tres);
        Suma=findViewById(R.id.suma);
        Cero=findViewById(R.id.cero);
        Ans=findViewById(R.id.ans);
        Igual=findViewById(R.id.igual);
    }
    public void ButtonClick(View view){
        Button button=(Button) view;
        String data=button.getText().toString();
        switch (data){
            case "AC":
                input="";
                break;
            case "Ans":
                clearResult=false;
                input+=Answer;
                break;
            case "x":
                clearResult=false;
                Solve();
                input+="*";
                break;
            case "^":
                clearResult=false;
                Solve();
                input+="^";
            case "=":
                clearResult=false;
                Solve();
                Answer=input;
                break;
            case "←":
                if(input.length()>0){
                    clearResult=false;
                    String newText=input.substring(0,input.length()-1);
                    input=newText;
                }
                break;
            default:
                if(input==null){
                    input="";
                }
                if(data.equals("+") || data.equals("-") || data.equals("/")){
                    clearResult=false;
                    Solve();
                }
                else if(clearResult==true){
                    input="";
                    clearResult=false;
                }
                input+=data;
        }
        screen.setText(input);
    }
    public void Solve(){
        if(input.split("\\*").length==2){
            String numbers[]=input.split("\\*");
            try{
                double mul=Double.parseDouble(numbers[0])*Double.parseDouble(numbers[1]);
                input=mul+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("/").length==2){
            String numbers[]=input.split("/");
            try{
                double div=Double.parseDouble(numbers[0])/Double.parseDouble(numbers[1]);
                input=div+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("\\^").length==2){
            String numbers[]=input.split("\\^");
            try{
                double pow=Math.pow(Double.parseDouble(numbers[0]),Double.parseDouble(numbers[1]));
                input=pow+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("\\+").length==2){
            String numbers[]=input.split("\\+");
            try{
                double sum=Double.parseDouble(numbers[0])+Double.parseDouble(numbers[1]);
                input=sum+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        else if(input.split("\\-").length>1){
            String numbers[]=input.split("\\-");
            if(numbers[0]=="" && numbers.length==2){
                numbers[0]=0+"";
            }
            try{
                double sub=0;
                if(numbers.length==2) {
                    sub = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                }
                else if(numbers.length==3){
                    sub = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                input=sub+"";
            }
            catch (Exception e){
                //Display error
            }
        }
        // Para remover el ultimo digito (.0) del resultado del integer como 8 respuesta 8.0 //
        String n[]=input.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
        }
        screen.setText(input);
    }
}