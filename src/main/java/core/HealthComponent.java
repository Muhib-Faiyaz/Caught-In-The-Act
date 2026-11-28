package core;

import com.almasb.fxgl.entity.component.Component;

public class HealthComponent extends Component {
    
    private int hp;
    private final int maxhp;

    public HealthComponent(int maxhp){
        if(maxhp<=0) throw new IllegalArgumentException();
        this.maxhp=maxhp;
        hp=maxhp;
    }

    public int getHp(){
        return hp;
    }

    public boolean isDead(){ return hp==0 ;}

    public void heal(int amount){
        if(amount>0){
            hp=Math.min(maxhp,hp+amount);
        }
    }

    public void damage(int amount){
        if(amount>0){
            hp=Math.max(0,hp-amount);
        }
    }

}
