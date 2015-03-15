package io.tony.arcaretrofit.providers;

import android.net.Uri;

import io.pivotal.arca.provider.DatabaseProvider;
import io.tony.arcaretrofit.datasets.CardTable;

public class ArcaRetrofitContentProvider extends DatabaseProvider {

    private static final String AUTHORITY = ArcaRetrofitContentProvider.class.getName();
    public static final String CONTENT = "content://";

    public static final class Uris {
        public static final Uri CARD = createUri(Paths.CARD);
    }

    private static Uri createUri(final String path) {
        return Uri.parse(CONTENT + AUTHORITY + "/" + path);
    }

    private static final class Paths {
        public static final String CARD = "card";
    }

    @Override
    public boolean onCreate() {
        registerDataset(AUTHORITY, Paths.CARD, CardTable.class);
        return false;
    }
}

