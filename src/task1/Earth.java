package task1;

public class Earth extends Spell{
    public Earth() {
        damage = 35;
        costMana = 15;
    }

    @Override
    public String toString() {
        return "Earth{" + super.toString();
    }

    @Override
    public boolean visit(Entity entity) {
        if(!entity.rezistentaEarth){
            //entity.receiveDamage(damage);
            return true;
        }
        return false;
    }
}
