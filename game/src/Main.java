import Core2D.Camera2D.Camera2D;
import Core2D.Camera2D.CamerasManager;
import Core2D.Component.Components.TransformComponent;
import Core2D.Controllers.PC.Keyboard;
import Core2D.Core2D.Core2D;
import Core2D.Core2D.Core2DUserCallback;
import Core2D.Object2D.Transform;
import Core2D.Scene2D.Scene2D;
import Core2D.Scene2D.SceneManager;
import Core2D.Utils.Utils;
import org.jbox2d.common.Vec2;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static Core2DUserCallback core2DUserCallback;

    private static Scene2D mainScene2D;

    private static Camera2D mainCamera2D;

    public static Player player;

    private static int maxEnemies = 10;
    private static List<Enemy> enemies = new ArrayList<>();

    private static Vector2f playerSpeed = new Vector2f(200.0f, 200.0f);

    public static void main(String[] args)
    {
        core2DUserCallback = new Core2DUserCallback() {
            @Override
            public void onInit() {
                mainScene2D = new Scene2D();
                SceneManager.currentSceneManager.setCurrentScene2D(mainScene2D);

                mainCamera2D = new Camera2D();
                CamerasManager.setMainCamera2D(mainCamera2D);

                mainScene2D.getPhysicsWorld().setGravity(new Vec2(0.0f, 0.0f));

                player = new Player();
                player.getEntityObj().setColor(new Vector4f(0.0f, 1.0f, 0.0f, 1.0f));
            }

            @Override
            public void onExit() {

            }

            @Override
            public void onDrawFrame() {
                Core2D.getMainRenderer().render(player.getEntityObj());
                for(int i = 0; i < enemies.size(); i++) {
                    Core2D.getMainRenderer().render(enemies.get(i).getEntityObj());
                    enemies.get(i).onAction();
                }

                if(Utils.getRandom(0, 10000) == 50) {
                    if(enemies.size() < maxEnemies) {
                        Enemy enemy = new Enemy();
                        Vector2f minPos = new Vector2f(200.0f, -200.0f);
                        Vector2f maxPos = new Vector2f(200.0f, 200.0f);
                        Vector2f currentPos = new Vector2f(
                                (float) Utils.getRandom(minPos.x, maxPos.x),
                                (float) Utils.getRandom(minPos.y, maxPos.y)
                                );
                        enemy.getEntityObj().getComponent(TransformComponent.class).getTransform().setPosition(currentPos);
                        enemies.add(enemy);
                    }
                }
            }

            @Override
            public void onDeltaUpdate(float v) {
                Transform playerTransform = player.getEntityObj().getComponent(TransformComponent.class).getTransform();
                if(Keyboard.keyDown(GLFW.GLFW_KEY_D)) {
                    playerTransform.translate(new Vector2f(playerSpeed.x * v, 0.0f));
                }
                if(Keyboard.keyDown(GLFW.GLFW_KEY_A)) {
                    playerTransform.translate(new Vector2f(-playerSpeed.x * v, 0.0f));
                }
                if(Keyboard.keyDown(GLFW.GLFW_KEY_W)) {
                    playerTransform.translate(new Vector2f(0.0f, playerSpeed.y * v));
                }
                if(Keyboard.keyDown(GLFW.GLFW_KEY_S)) {
                    playerTransform.translate(new Vector2f(0.0f, -playerSpeed.y * v));
                }
            }
        };

        Core2D.core2DUserCallback = core2DUserCallback;
        Core2D.start("хуйня", new Vector2i(1040, 720));
    }
}