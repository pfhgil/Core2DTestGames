import Core2D.Component.Components.BoxCollider2DComponent;
import Core2D.Component.Components.Rigidbody2DComponent;
import Core2D.Component.Components.TransformComponent;
import Core2D.Scene2D.SceneManager;
import Core2D.Utils.Tag;
import org.jbox2d.dynamics.BodyType;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class Player extends Entity
{
    public Player()
    {
        super();
        entityObj.getComponent(TransformComponent.class).getTransform().setScale(new Vector2f(0.5f, 1.0f));
        entityObj.setColor(new Vector4f(0.0f, 1.0f, 0.0f, 1.0f));

        Rigidbody2DComponent rb = new Rigidbody2DComponent();
        rb.getRigidbody2D().setType(BodyType.DYNAMIC);

        BoxCollider2DComponent box = new BoxCollider2DComponent();
        box.getBoxCollider2D().setScale(new Vector2f(0.5f, 1.0f));

        entityObj.addComponent(rb);
        entityObj.addComponent(box);

        SceneManager.currentSceneManager.getCurrentScene2D().addTag(new Tag("Player"));
        entityObj.setTag("Player");
    }
}
