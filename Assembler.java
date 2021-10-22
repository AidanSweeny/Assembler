//Katie Andre, Aidan Sweeny
import java.util.ArrayList;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Assembler{
    private LLStack stk;
    private Postfix postfix;
    private int temp_num;
    private String file_name;
    public FileWriter fw;

    public Assembler(FileWriter fw){
        this.postfix = new Postfix();
        this.stk = new LLStack();
        this.temp_num = 1;
        this.fw = fw;

    }
    public String assemble(String infix){
        String[] split_infix = infix.split("(?<=;)");
        
        String solution = "";
        for (int j=0; j < split_infix.length; j++){
            try{    
                this.fw.write(split_infix[j] + "\n----------------------------------\n");
            }
            catch(Exception e){System.out.println(e);}   
            this.temp_num = 1;
            String pf = this.postfix.inToPost(split_infix[j]);
            try{    
                this.fw.write(pf + "\n----------------------------------\n");
            }
            catch(Exception e){System.out.println(e);}    
            String[] characters = pf.split(" ");
            String operators = "+-*/";
            String right;
            String left;
            String oper;
            for (int i=0; i < characters.length; i++){
                if(operators.contains(characters[i])){
                    right = (String)stk.pop();
                    left = (String)stk.pop();
                    oper = this.evaluate(left, right, characters[i]);
                    stk.push(oper);
                }
                else{
                    stk.push(characters[i]);
                }
            }
            solution += (String)stk.pop() + "\n----------------------------------\n";
            try{    
                this.fw.write("\n----------------------------------\n");
            }
            catch(Exception e){System.out.println(e);}    
        }
        return solution;
    }

    private String evaluate(String left, String right, String operator){
        String temp = "TEMP" + this.temp_num;
        String evaluated = "";
        this.temp_num++;
        if (operator.equals("+")){
             try{    
                evaluated += "LD " + left + "\n";
                evaluated += "AD " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
        }
        else if (operator.equals("-")){
            try{    
                evaluated += "LD " + left + "\n";
                evaluated += "SB " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
        }
        else if (operator.equals("*")){
            try{    
                evaluated += "LD " + left + "\n";
                evaluated += "ML " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
        }
        else if (operator.equals("/")){
            try{    
                evaluated += "LD " + left + "\n";
                evaluated += "DV " + right + "\n";
                evaluated += "ST " + temp + "\n";
                this.fw.write(evaluated);     
            }
            catch(Exception e){System.out.println(e);}    
        }
        else{
            System.out.print("Not a Valid Operation");
            return null;
        }
        return temp; 
    }

    public static void main(String args[])throws IOException{
        //  Assembler as = new Assembler();
        //  String pf = "( ( AX + ( BY * C ) ) / ( D4 - E ) ) ;";
        //  String sol = as.assemble(pf);
        //  System.out.println(sol);
        try{
            FileWriter fw = new FileWriter("output.txt");
            if (args.length > 0){
                Assembler as = new Assembler(fw);
                Path fileName = Path.of((String)args[0]);
                String pf = Files.readString(fileName);
                as.assemble(pf);
                System.out.println("Output is in file: " + fileName);
                fw.close();
            }
            else{
                System.out.println("Must provide the filename with the given infix expression");
        }
    }
        catch(Exception e){System.out.println(e);}
        
    }
}