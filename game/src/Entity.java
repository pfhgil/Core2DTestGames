import Core2D.Object2D.Object2D;

public abstract class Entity implements EntityAction
{
    public float hp = 100.0f;
    public String name = "";
    protected Object2D entityObj;

    public Entity()
    {
        entityObj = Object2D.instantiate();
    }

    @Override
    public void onAction() {

    }

    public Object2D getEntityObj() { return entityObj; }
}
