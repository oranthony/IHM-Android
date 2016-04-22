package polytechnice.ihm.projet.Contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.text.Html;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.View;

/**
 * Make safe the click of an URL
 * @author Patrice Camousseigt
 */
public final class SafeURLSpan extends URLSpan {

    /**
     * Constructor
     * @param url the url
     */
    public SafeURLSpan(String url) {
        super(url);
    }

    /**
     * The action done clicking on a widget
     * @param widget
     */
    @Override
    public void onClick(View widget) {
        try {
            final Uri uri = Uri.parse(getURL());
            final Context context = widget.getContext();
            final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (context != null && intent != null) {
                intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
                context.startActivity(intent);
            }
        } catch (Throwable ex) {
        }
    }

    /**
     * Parse a html CharSequence
     * @param html the html CharSequence
     * @return the new CharSequence
     */
    public static CharSequence parseSafeHtml(CharSequence html) {
        return replaceURLSpans(Html.fromHtml(html.toString()));
    }

    /**
     * Replace URL spans
     * @param text the text
     * @return the new text
     */
    public static CharSequence replaceURLSpans(CharSequence text) {
        if (text instanceof Spannable) {
            final Spannable s = (Spannable)text;
            final URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
            if (spans != null && spans.length > 0) {
                for (int i = spans.length - 1; i >= 0; i--) {
                    final URLSpan span = spans[i];
                    final int start = s.getSpanStart(span);
                    final int end = s.getSpanEnd(span);
                    final int flags = s.getSpanFlags(span);
                    s.removeSpan(span);
                    s.setSpan(new SafeURLSpan(span.getURL()), start, end, flags);
                }
            }
        }
        return text;
    }
}