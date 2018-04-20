package com.garcia.felipe.redditapp.Details.UI;

import android.graphics.Bitmap;

public interface DetailFragmentUI {
    void setTitle(String title);
    void setDescription(String description);
    void setImage(Bitmap image);
    void setCategory(String category);
}
