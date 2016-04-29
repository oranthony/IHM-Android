package polytechnice.ihm.projet.siPresentation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import polytechnice.ihm.projet.R;

/**
 * Created by antho on 20/04/2016.
 */
public class SIPresentationFragment extends Fragment implements BlurScrollView.OnScrollViewListener {

    private static final String BLURRED_IMG_PATH = "blurred_image.png";
    private BlurScrollView mScrollView;
    private ImageView mBlurredImage, mBackground;
    private ImageView mNormalImage;
    private LinearLayout layoutScroll;
    private float alpha, alphaBackground;

    private View v;

    private static final int ORIENTATION_PORTRAIT = 1;
    private static final int ORIENTATION_LANDSCAPE = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int surfaceOrientation = getResources().getConfiguration().orientation;

        if (surfaceOrientation == ORIENTATION_PORTRAIT) {
            v = inflater.inflate(R.layout.sipresentationportrait, container, false);
        }else { //Landscape
            v = inflater.inflate(R.layout.sipresentationlandscape, container, false);
        }

        layoutScroll = (LinearLayout) v.findViewById(R.id.layoutScroll);
        mBlurredImage = (ImageView) v.findViewById(R.id.blurredImage);
        mNormalImage = (ImageView) v.findViewById(R.id.normalImage);
        mBackground = (ImageView) v.findViewById(R.id.backgroundImage);

        mScrollView = (BlurScrollView) v.findViewById(android.R.id.list);

        // Try to find the blurred image
        final File blurredImage = new File(getActivity().getFilesDir() + BLURRED_IMG_PATH);

        if (!blurredImage.exists()) {
            generateBlurImage(blurredImage);
        } else {
            // The image has been found. Let's update the view
            updateView();
        }
        mScrollView.setOnScrollViewListener(this);

        return v;
    }

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
    }

    private void generateBlurImage(final File blurredImage) {
        // launch the progressbar in ActionBar
        //setSupportProgressBarIndeterminateVisibility(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // No image found => let's generate it!
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap image = BitmapFactory.decodeResource(getResources(),
                        R.drawable.dsc6671, options);
                Bitmap newImg = Blur.fastblur(SIPresentationFragment.this.getContext(), image, 24);
                ImageUtils.storeImage(newImg, blurredImage);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateView();
                        // And finally stop the progressbar
                        //setSupportProgressBarIndeterminateVisibility(false);
                    }
                });
            }
        }).start();
    }

    private void updateView() {
        final int screenWidth = ImageUtils.getScreenWidth(getActivity());
        Bitmap bmpBlurred = BitmapFactory.decodeFile(getActivity().getFilesDir()
                + BLURRED_IMG_PATH);
        bmpBlurred = Bitmap
                .createScaledBitmap(
                        bmpBlurred,
                        screenWidth,
                        (int) (bmpBlurred.getHeight() * ((float) screenWidth) / bmpBlurred
                                .getWidth()), false);
        mBlurredImage.setImageBitmap(bmpBlurred);
    }

    @Override
    public void onScrollChanged(BlurScrollView v, int l, int t, int oldl,
                                int oldt) {

        // Calculate the ratio between the scroll amount and the list
        // header weight to determinate the top picture alpha
        float paddingTop = layoutScroll.getTop();

        if (paddingTop != 0) {
            alpha = (float) (t) / paddingTop;
            alphaBackground = alpha;
        }

        // Apply a ceil
        if (alpha > 1) {
            alpha = 1;
        }
        if (alphaBackground > 0.10f) {
            alphaBackground = 0.10f;
        }

        // Apply on the ImageView if needed
        if (Build.VERSION.SDK_INT >= 11) {
            mBlurredImage.setAlpha(alpha);
            mBackground.setAlpha(alphaBackground);
        } else {
            Drawable backgroundBlurredImage = mBlurredImage.getBackground();
            Drawable backgroundImage = mBackground.getBackground();
            if (backgroundBlurredImage != null && backgroundImage != null) {
                backgroundBlurredImage.setAlpha((int) alpha * 255);// 255 is max
                backgroundImage.setAlpha((int) alphaBackground * 255);// 255 is
                // max
            }
        }

        // Parallax effect : we apply half the scroll amount to our
        // three views
        if (Build.VERSION.SDK_INT >= 11) {
            mBlurredImage.setTop(-t / 4);
            mNormalImage.setTop(-t / 4);
            mBackground.setTop(-t / 4);
        } else {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, -t / 4, 0, 0);
            mBlurredImage.setLayoutParams(layoutParams);
            mNormalImage.setLayoutParams(layoutParams);
            mBackground.setLayoutParams(layoutParams);
        }
    }
}
