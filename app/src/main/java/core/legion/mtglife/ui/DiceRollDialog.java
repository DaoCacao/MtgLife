package core.legion.mtglife.ui;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

import core.legion.mtglife.utils.DiceUtils;
import trikita.anvil.Anvil;
import trikita.anvil.RenderableView;

import static trikita.anvil.BaseDSL.CENTER;
import static trikita.anvil.BaseDSL.WRAP;
import static trikita.anvil.BaseDSL.dip;
import static trikita.anvil.BaseDSL.padding;
import static trikita.anvil.BaseDSL.size;
import static trikita.anvil.BaseDSL.weight;
import static trikita.anvil.DSL.backgroundColor;
import static trikita.anvil.DSL.button;
import static trikita.anvil.DSL.gravity;
import static trikita.anvil.DSL.imageBitmap;
import static trikita.anvil.DSL.imageView;
import static trikita.anvil.DSL.linearLayout;
import static trikita.anvil.DSL.onClick;
import static trikita.anvil.DSL.orientation;
import static trikita.anvil.DSL.text;

public class DiceRollDialog extends AlertDialog {

    private final DiceUtils diceUtils;

    public DiceRollDialog(@NonNull Context context) {
        super(context);

        diceUtils = new DiceUtils();

        setView(new RenderableView(context) {
            @Override
            public void view() {
                linearLayout(() -> {
                    orientation(LinearLayout.VERTICAL);

                    linearLayout(() -> {
                        orientation(LinearLayout.HORIZONTAL);
                        gravity(CENTER);
                        padding(dip(8));

                        initDice(4);
                        initDice(6);
                        initDice(8);
//                        TODO -->
//                        initDice(10);
                        initDice(12);
                        initDice(20);
                    });

                    linearLayout(() -> {
                        orientation(LinearLayout.HORIZONTAL);

                        initButton("SHUFFLE", v -> {
                            v.setEnabled(false);
                            Anvil.render();
                            new Handler().postDelayed(() -> v.setEnabled(true), 300);
                        });
                        initButton("OK!", v -> dismiss());
                    });
                });
            }
        });
    }

    private void initDice(int edges) {
        imageView(() -> {
            padding(dip(4));
            imageBitmap(diceUtils.drawDice(edges, new Random().nextInt(edges) + 1));
            Anvil.currentView().animate().rotationBy(360).setDuration(300);
        });
    }

    private void initButton(String text, View.OnClickListener onClick) {
        button(() -> {
            size(WRAP, WRAP);
            weight(0.5f);
            backgroundColor(0);
            text(text);
            onClick(onClick);
        });
    }
}
