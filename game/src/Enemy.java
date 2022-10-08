import Core2D.Component.Components.BoxCollider2DComponent;
import Core2D.Component.Components.Rigidbody2DComponent;
import Core2D.Component.Components.TransformComponent;
import Core2D.Object2D.Object2D;
import Core2D.Object2D.Transform;
import Core2D.Scene2D.SceneManager;
import org.jbox2d.dynamics.BodyType;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class Enemy extends Entity
{
    public Enemy()
    {;
        entityObj.getComponent(TransformComponent.class).getTransform().setScale(new Vector2f(0.5f, 1.0f));
        entityObj.setColor(new Vector4f(1.0f, 0.0f, 0.0f, 1.0f));

        Rigidbody2DComponent rb = new Rigidbody2DComponent();
        rb.getRigidbody2D().setType(BodyType.DYNAMIC);
        rb.getRigidbody2D().setFixedRotation(true);

        BoxCollider2DComponent box = new BoxCollider2DComponent();
        box.getBoxCollider2D().setScale(new Vector2f(0.5f, 1.0f));

        entityObj.addComponent(rb);
        entityObj.addComponent(box);
    }

    @Override
    public void onAction()
    {
        Object2D playerObj = SceneManager.currentSceneManager.getCurrentScene2D().findObject2DByTag("Player");

        if(playerObj != null) {
            Transform playerTransform = playerObj.getComponent(TransformComponent.class).getTransform();
            Transform thisTransform = entityObj.getComponent(TransformComponent.class).getTransform();

            thisTransform.lerpMoveTo(playerTransform.getPosition(), new Vector2f(0.5f));
        }
    }
}
