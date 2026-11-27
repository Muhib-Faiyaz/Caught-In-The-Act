package core;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public class CoreApp extends GameApplication {

private Entity player;

private boolean right,left,up,down;
 public static void main(String[] args) { launch(args); }
 @Override
 protected void initSettings(GameSettings settings) {
 settings.setTitle("Caught In The Act");
 settings.setVersion("0.0");
 settings.setWidth(800);
 settings.setHeight(600);
 }
 @Override
 protected void initGame() {
  player=entityBuilder().at(100, 100)
 .view(new Rectangle(40, 40, Color.DODGERBLUE))
 .buildAndAttach();
 
 }

 @Override
 protected void initInput(){
     
     getInput().addAction(new UserAction("Move right"){
            @Override 
            protected void onActionBegin(){
                right=true;
                
            }
            
            @Override 
            protected void onActionEnd(){
                right=false;
                
            }
        },KeyCode.D);

        getInput().addAction(new UserAction("Move left"){
            @Override 
            protected void onActionBegin(){
                left=true;
                
            }
            
            @Override 
            protected void onActionEnd(){
                left=false;
                
            }
        },KeyCode.A);

        getInput().addAction(new UserAction("Move up"){
            @Override 
            protected void onActionBegin(){
                up=true;
                
            }
            
            @Override 
            protected void onActionEnd(){
                up=false;
                
            }
        },KeyCode.W);

        getInput().addAction(new UserAction("Move Down"){
            @Override 
            protected void onActionBegin(){
                down=true;
                
            }
            
            @Override 
            protected void onActionEnd(){
                down=false;
                
            }
        },KeyCode.S);

        onKeyDown(KeyCode.E,()->player.setPosition(100,100));
    }
    
    @Override 
    protected void onUpdate(double tpf){
        var speed=500*tpf;

        if(right) player.translateX(speed);
        else if(left) player.translateX(-speed);
        else if(up) player.translateY(-speed);
        else if(down) player.translateY(speed);
    } 
 
}