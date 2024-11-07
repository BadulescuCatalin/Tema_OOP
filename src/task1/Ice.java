package task1;

public class Ice extends Spell{
    public Ice() {
        damage = 20;
        costMana = 10;
    }

    @Override
    public String toString() {
        return "Ice{" + super.toString();
    }

    @Override
    public boolean visit(Entity entity) {
        if(!entity.rezistentaIce){
           // entity.receiveDamage(damage);
            return true;
        }
        return false;
    }
}
