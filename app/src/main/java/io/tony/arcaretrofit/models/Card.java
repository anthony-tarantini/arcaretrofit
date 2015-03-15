package io.tony.arcaretrofit.models;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.tony.arcaretrofit.datasets.CardTable;

public class Card {
    @SerializedName("id")
    private int mId;
    public int getmId() {
        return mId;
    }
    public void setmId(int mId) {
        this.mId = mId;
    }

    @SerializedName("name")
    private String mName;
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }

    @SerializedName("manaCost")
    private String mManacost;
    public String getmManacost() {
        return mManacost;
    }
    public void setmManacost(String mManacost) {
        this.mManacost = mManacost;
    }

    @SerializedName("description")
    private String mText;
    public String getmText() {
        return mText;
    }
    public void setmText(String mText) {
        this.mText = mText;
    }

    public ContentValues toContentValues() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(CardTable.Columns.ID, mId);
        contentValues.put(CardTable.Columns.NAME, mName);
        contentValues.put(CardTable.Columns.MANACOST, mManacost);
        contentValues.put(CardTable.Columns.TEXT, mText);
        return contentValues;
    }
    public static ContentValues[] toContentValues(final List<Card> cards){
        final List<ContentValues> contentValues = new ArrayList<>();
        for (final Card card : cards) {
            contentValues.add(card.toContentValues());
        }
        return contentValues.toArray(new ContentValues[cards.size()]);
    }
}
