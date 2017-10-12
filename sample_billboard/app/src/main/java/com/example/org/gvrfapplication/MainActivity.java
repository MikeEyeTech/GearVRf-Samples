package com.example.org.gvrfapplication;

import android.os.Bundle;

import org.gearvrf.GVRActivity;
import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRBillboard;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.scene_objects.GVRModelSceneObject;
import org.joml.Vector3f;

public class MainActivity extends GVRActivity {

    private GVRSceneObject mNode;
    private GVRModelSceneObject mTrexObj;
    private float mDir = 0.1f;
    //private float mAngle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Set Main Scene
         * It will be displayed when app starts
         */
        setMain(new Main());
    }

    private final class Main extends GVRMain {

        @Override
        public void onInit(GVRContext gvrContext) throws Throwable {

            //Load texture
            GVRTexture texture = gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.__default_splash_screen__));

            //Create a rectangle with the texture we just loaded
            GVRSceneObject quad = new GVRSceneObject(gvrContext, 4, 2, texture);
            quad.getTransform().setPosition(0, 0, -3);

            //Add rectangle to the scene
            gvrContext.getMainScene().addSceneObject(quad);

            mNode = new GVRSceneObject(gvrContext);
            gvrContext.getMainScene().addSceneObject(mNode);

            mTrexObj = gvrContext.getAssetLoader().loadModel("trex_mesh.fbx");
            mTrexObj.getTransform().setPosition(4,-6,-8);
            mTrexObj.attachComponent(new GVRBillboard(gvrContext, new Vector3f(0f,1f,0f)));
            mNode.addChildObject(mTrexObj);
        }

        @Override
        public void onStep() {
//            mAngle += 1;
//            mNode.getTransform().setRotationByAxis(mAngle, 0, 0, 1);

            float oldPosX = mTrexObj.getTransform().getPositionX();
            if (Math.abs(oldPosX) > 10){
                mDir = -mDir;
            }

            mTrexObj.getTransform().setPositionX(oldPosX + mDir);
        }
    }
}
