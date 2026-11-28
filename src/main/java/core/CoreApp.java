package core;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public class CoreApp extends GameApplication {

private Entity player;
private HealthComponent pHp;
private Entity coin;
private boolean right,left,up,down;

int windowX=800, windowY=600,width=40,height=40;
 public static void main(String[] args) { launch(args); }
 @Override
 protected void initSettings(GameSettings settings) {
 settings.setTitle("Caught In The Act");
 settings.setVersion("0.002");
 settings.setWidth(windowX);
 settings.setHeight(windowY);
 }

 private Entity spawnPlayer(double x, double y){
    Entity player=entityBuilder().at(x, y)
  .type(Kind.PLAYER)
  .collidable()
  .viewWithBBox(new Rectangle(width, height, Color.DODGERBLUE))
  .with(new HealthComponent(100))
  .buildAndAttach();

  return player;

 }
 private Entity spawnCoin(double x, double y, int value){
    Entity coin= entityBuilder()
    .at(x, y)
    .type(Kind.COIN)
    .viewWithBBox(new Circle(10, Color.RED))
    .collidable()
    .buildAndAttach();

    coin.setProperty("Value", value);

    return coin;
 }


 @Override
 protected void initGame() {
 
    player = spawnPlayer(100, 100);
    pHp =player.getComponent(HealthComponent.class);
     coin =spawnCoin(220, 300, 40);
 

 
 }

 private void bind(KeyCode key, String name , java.util.function.Consumer<Boolean> set){

    getInput().addAction(new UserAction(name) {
        @Override 
        protected void onActionBegin(){
            set.accept(true);
        }
        @Override 
        protected void onActionEnd(){
            set.accept(false);
        }
    }, key);
 }

 @Override
 protected void initInput(){
     
    bind(KeyCode.D,"Right",v->right=v);
    bind(KeyCode.A,"Left",v->left=v);
    bind(KeyCode.W,"Up",v->up=v);
    bind(KeyCode.S,"Down",v->down=v);

    onKeyDown(KeyCode.E, ()->System.out.println(pHp.getHp()));
    }
    
    @Override 
    protected void onUpdate(double tpf){
        
        double dx=(right? 1:0) - (left? 1:0);
        double dy= (down? 1:0) - (up? 1:0);

        double length= Math.hypot(dx, dy);
        if (length == 0) return;
        double step = 300 * Math.min(tpf,.05);

        double X = player.getX() + (dx/length)*step;
        double Y = player.getY() + (dy/length)*step;

        player.setPosition(Math.max(0,Math.min(windowX-width,X)) , Math.max(0,Math.min(windowY-height,Y)));

        double ddcx= (player.getX()+20)-220 , ddcy = (player.getY()+20)-300;
         if(Math.hypot(ddcx, ddcy)<7){
            pHp.damage(coin.getInt("Value"));
            coin.removeFromWorld();
         }

         
    } 
 
}