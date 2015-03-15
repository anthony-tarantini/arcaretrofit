package io.tony.arcaretrofit.utils;

public class ImageUtil {
    public static final String IMAGE_URL = "https://api.mtgdb.info/content/card_images/%d.jpeg";

    public static String getImageUrl(final int id) {
        return String.format(IMAGE_URL, id);
    }
}

