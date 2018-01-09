package core.legion.mtglife.dices;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

import static trikita.anvil.BaseDSL.sip;
import static trikita.anvil.BaseDSL.dip;

public class DiceUtils {

    public Bitmap drawDice(int edges, int value) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(dip(2));
        paint.setTextSize(sip(12));

        Bitmap bitmap = Bitmap.createBitmap(dip(48), dip(48), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        switch (edges) {
            case 4:
                canvas.drawPath(drawK4(canvas), paint);
                break;
            case 6:
                canvas.drawPath(drawK6(canvas), paint);
                break;
            case 8:
                canvas.drawPath(drawK8(canvas), paint);
                break;
            case 10:
                canvas.drawPath(drawK10(canvas), paint);
                break;
            case 12:
                canvas.drawPath(drawK12(canvas), paint);
                break;
            case 20:
                canvas.drawPath(drawK20(canvas), paint);
                break;
        }

        drawTextAtCenter(canvas, paint, String.valueOf(value));
        return bitmap;
    }

    private Path drawK4(Canvas canvas) {
        Point[] points = getPoints(canvas.getWidth() / 2, canvas.getHeight() / 2, 3, canvas.getHeight() / 2);

        Path path = new Path();
        path.moveTo(points[0].x, points[0].y);
        path.lineTo(points[1].x, points[1].y);
        path.lineTo(points[2].x, points[2].y);
        path.close();

        return path;
    }

    private Path drawK6(Canvas canvas) {
        Point[] points = getPoints(canvas.getWidth() / 2, canvas.getHeight() / 2, 4, canvas.getHeight() / 2);

        Path path = new Path();
        path.moveTo(points[0].x, points[0].y);
        path.lineTo(points[1].x, points[1].y);
        path.lineTo(points[2].x, points[2].y);
        path.lineTo(points[3].x, points[3].y);
        path.close();

        return path;
    }

    private Path drawK8(Canvas canvas) {
        Point[] points = getPoints(canvas.getWidth() / 2, canvas.getHeight() / 2, 6, canvas.getHeight() / 2);

        Path path = new Path();
        path.moveTo(points[0].x, points[0].y);
        path.lineTo(points[1].x, points[1].y);
        path.lineTo(points[2].x, points[2].y);
        path.lineTo(points[3].x, points[3].y);
        path.lineTo(points[4].x, points[4].y);
        path.lineTo(points[5].x, points[5].y);

        path.lineTo(points[0].x, points[0].y);
        path.lineTo(points[2].x, points[2].y);
        path.lineTo(points[4].x, points[4].y);
        path.close();

        return path;
    }

    @SuppressWarnings("UnusedParameters")
    private Path drawK10(Canvas canvas) {
        return new Path();
    }

    private Path drawK12(Canvas canvas) {
        Point[] outPoints = getPoints(canvas.getWidth() / 2, canvas.getHeight() / 2, 10, canvas.getHeight() / 2);
        Point[] inPoints = getPoints(canvas.getWidth() / 2, canvas.getHeight() / 2, 5, canvas.getHeight() / 3);

        Path path = new Path();
        path.moveTo(outPoints[0].x, outPoints[0].y);
        path.lineTo(outPoints[1].x, outPoints[1].y);
        path.lineTo(outPoints[2].x, outPoints[2].y);
        path.lineTo(outPoints[3].x, outPoints[3].y);
        path.lineTo(outPoints[4].x, outPoints[4].y);
        path.lineTo(outPoints[5].x, outPoints[5].y);
        path.lineTo(outPoints[6].x, outPoints[6].y);
        path.lineTo(outPoints[7].x, outPoints[7].y);
        path.lineTo(outPoints[8].x, outPoints[8].y);
        path.lineTo(outPoints[9].x, outPoints[9].y);
        path.close();

        path.moveTo(inPoints[0].x, inPoints[0].y);
        path.lineTo(inPoints[1].x, inPoints[1].y);
        path.lineTo(inPoints[2].x, inPoints[2].y);
        path.lineTo(inPoints[3].x, inPoints[3].y);
        path.lineTo(inPoints[4].x, inPoints[4].y);
        path.close();

        path.moveTo(outPoints[0].x, outPoints[0].y);
        path.lineTo(inPoints[0].x, inPoints[0].y);

        path.moveTo(outPoints[2].x, outPoints[2].y);
        path.lineTo(inPoints[1].x, inPoints[1].y);

        path.moveTo(outPoints[4].x, outPoints[4].y);
        path.lineTo(inPoints[2].x, inPoints[2].y);

        path.moveTo(outPoints[6].x, outPoints[6].y);
        path.lineTo(inPoints[3].x, inPoints[3].y);

        path.moveTo(outPoints[8].x, outPoints[8].y);
        path.lineTo(inPoints[4].x, inPoints[4].y);

        return path;
    }

    private Path drawK20(Canvas canvas) {
        Point[] outPoints = getPoints(canvas.getWidth() / 2, canvas.getHeight() / 2, 6, canvas.getHeight() / 2);
        Point[] inPoints = getPoints(canvas.getWidth() / 2, canvas.getHeight() / 2, 3, canvas.getHeight() / 3);

        Path path = new Path();
        path.moveTo(outPoints[0].x, outPoints[0].y);
        path.lineTo(outPoints[1].x, outPoints[1].y);
        path.lineTo(outPoints[2].x, outPoints[2].y);
        path.lineTo(outPoints[3].x, outPoints[3].y);
        path.lineTo(outPoints[4].x, outPoints[4].y);
        path.lineTo(outPoints[5].x, outPoints[5].y);
        path.close();

        path.moveTo(inPoints[0].x, inPoints[0].y);
        path.lineTo(inPoints[1].x, inPoints[1].y);
        path.lineTo(inPoints[2].x, inPoints[2].y);
        path.close();

        path.moveTo(inPoints[0].x, inPoints[0].y);
        path.lineTo(outPoints[1].x, outPoints[1].y);
        path.lineTo(inPoints[1].x, inPoints[1].y);
        path.lineTo(outPoints[3].x, outPoints[3].y);
        path.lineTo(inPoints[2].x, inPoints[2].y);
        path.lineTo(outPoints[5].x, outPoints[5].y);
        path.close();

        path.moveTo(outPoints[0].x, outPoints[0].y);
        path.lineTo(inPoints[0].x, inPoints[0].y);

        path.moveTo(outPoints[2].x, outPoints[2].y);
        path.lineTo(inPoints[1].x, inPoints[1].y);

        path.moveTo(outPoints[4].x, outPoints[4].y);
        path.lineTo(inPoints[2].x, inPoints[2].y);


        return path;
    }

    private Point[] getPoints(float cx, float cy, float spikes, float outRadius) {
        Point[] points = new Point[(int) spikes];
        float rot = (float) (Math.PI / 2 * 3);
        int x;
        int y;
        float step = (float) (Math.PI / spikes);

        for (int i = 0; i < spikes; i++) {
            x = (int) (cx + Math.cos(rot) * outRadius);
            y = (int) (cy + Math.sin(rot) * outRadius);
            points[i] = new Point(x, y);
            rot += step;
            rot += step;
        }
        return points;
    }

    private void drawTextAtCenter(Canvas canvas, Paint paint, String text) {
        Rect bounds = new Rect();
        paint.setStrokeWidth(dip(1));
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = (int) (canvas.getWidth() / 2 - paint.measureText(text) / 2);
        int y = (int) (canvas.getHeight() / 2 - (paint.descent() + paint.ascent()) / 2);
        canvas.drawText(text, x, y, paint);
    }
}
