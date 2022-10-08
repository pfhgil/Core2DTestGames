import Core2D.Component.Components.BoxCollider2DComponent;
import Core2D.Component.Components.Rigidbody2DComponent;
import Core2D.Component.Components.TransformComponent;
import org.jbox2d.dynamics.BodyType;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class Bullet extends Entity
{
    public Bullet()
    {
        hp = 0.0f;

        entityObj.getComponent(TransformComponent.class).getTransform().setScale(new Vector2f(0.1f, 0.1f));
        entityObj.setColor(new Vector4f(0.0f, 0.0f, 0.0f, 1.0f));

        Rigidbody2DComponent rb = new Rigidbody2DComponent();
        rb.getRigidbody2D().setType(BodyType.DYNAMIC);
        rb.getRigidbody2D().setFixedRotation(true);

        BoxCollider2DComponent box = new BoxCollider2DComponent();
        box.getBoxCollider2D().setScale(new Vector2f(0.1f, 0.1f));

        entityObj.addComponent(rb);
        entityObj.addComponent(box);
    }
}
