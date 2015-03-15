package io.tony.arcaretrofit.operations;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.pivotal.arca.dispatcher.ErrorBroadcaster;
import io.pivotal.arca.service.Operation;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.Task;
import io.tony.arcaretrofit.tasks.CardListTask;

public class CardListOperation extends Operation {
    public CardListOperation(final Uri uri) {
        super(uri);
    }

    public CardListOperation(final Parcel parcel) {
        super(parcel);
    }

    @Override
    public Set<Task<?>> onCreateTasks() {
        final Set<Task<?>> tasks = new HashSet<>();
        tasks.add(new CardListTask());
        return tasks;
    }

    @Override
    public void onSuccess(final Context context, final List<Task<?>> completed) {
        context.getContentResolver().notifyChange(getUri(), null);
    }

    @Override
    public void onFailure(final Context context, final ServiceError error) {
        ErrorBroadcaster.broadcast(context, getUri(), error.getCode(), error.getMessage());
    }

    public static final Creator<CardListOperation> CREATOR = new Creator<CardListOperation>() {
        @Override
        public CardListOperation createFromParcel(final Parcel in) {
            return new CardListOperation(in);
        }

        @Override
        public CardListOperation[] newArray(final int size) {
            return new CardListOperation[size];
        }
    };
}
