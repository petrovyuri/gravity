package com.mrdeveloper.my_framework.core;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

public class GraphicsGame {

    //region Fields
    private AssetManager mAssetManagerGame;
    private Bitmap mFrameBufferGame;
    private Canvas mCanvasGame;
    private Paint mPaintGame;
    private Bitmap mTextureGame;
    //endregion

    public GraphicsGame(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        mAssetManagerGame = assetManagerGame;
        mFrameBufferGame = frameBufferGame;
        mCanvasGame = new Canvas(frameBufferGame);
        mPaintGame = new Paint();
    }

    //region Methods of graphics
    public void clearScene(int colorRGB) {
        mCanvasGame.drawRGB(colorRGB, colorRGB, colorRGB);
    }

    public void drawPixel(int x, int y, int color) {
        mPaintGame.setColor(color);
        mCanvasGame.drawPoint(x, y, mPaintGame);
    }

    public void drawLine(int startX, int startY, int stopX, int stopY, int color) {
        mPaintGame.setColor(color);
        mCanvasGame.drawLine(startX, startY, stopX, stopY, mPaintGame);
    }

    public void drawText(String text, int x, int y, int color, int sizeText, Typeface font) {
        mPaintGame.setColor(color);
        mPaintGame.setTextSize(sizeText);
        mPaintGame.setTypeface(font);
        mCanvasGame.drawText(text, x, y, mPaintGame);
    }

    public void drawTexture(Bitmap textureGame, int x, int y) {
        mCanvasGame.drawBitmap(textureGame, x, y, null);
    }

    public Bitmap newTexture(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = mAssetManagerGame.open(fileName);
            mTextureGame = BitmapFactory.decodeStream(inputStream);
            if (mTextureGame == null) {
                throw new RuntimeException("Не возможно загрузить файл" + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не возможно загрузить файл" + fileName);
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mTextureGame;
    }

    public Bitmap newSprite(Bitmap textureAtlas, int x, int y, int widthSprite, int heightSprite) {
        return Bitmap.createBitmap(textureAtlas, x, y, widthSprite, heightSprite);
    }
    //endregion

    //region Get&Set
    public int getWidthFrameBuffer() {
        return mFrameBufferGame.getWidth();
    }

    public int getHeightFrameBuffer() {
        return mFrameBufferGame.getHeight();
    }
    //endregion


}
