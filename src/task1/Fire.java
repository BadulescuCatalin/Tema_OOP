package task1;

public class Fire extends Spell{
    public Fire() {
        damage = 45;
        costMana = 20;
    }

    @Override
    public String toString() {
        return "Fire{" +super.toString();
    }
    @Override
    public boolean visit(Entity entity) {
        if(!entity.rezistentaFire){
            //entity.receiveDamage(damage);
            return true;
        }
        return false;
    }
}
