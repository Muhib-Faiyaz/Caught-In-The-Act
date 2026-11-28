package core;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.BoundingShape;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class gameEntityFactory implements EntityFactory {

    @Spawns ("player")
    public Entity spawnPlayer(SpawnData data){
    return entityBuilder(data)
        .type(Kind.PLAYER)
        .collidable()
        .view(new Rectangle(20, 60, Color.DODGERBLUE))
        .bbox(new HitBox("playerBody", new Point2D(2,6),BoundingShape.box(18,54)))
        .with(new HealthComponent(100))
        .build();

 

 }

 @Spawns("coin")
 public Entity spawnCoin(SpawnData data){

    int value = data.get("Value");
    
    return entityBuilder(data)
    .type(Kind.COIN)
    .viewWithBBox(new Circle(10, Color.RED))
    .collidable()
    .with("Value",value)
    .build();

    
 }
    
}
