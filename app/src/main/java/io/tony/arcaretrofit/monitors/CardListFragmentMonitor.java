package io.tony.arcaretrofit.monitors;

import android.content.Context;
import android.net.Uri;

import io.pivotal.arca.dispatcher.Query;
import io.pivotal.arca.dispatcher.QueryResult;
import io.pivotal.arca.monitor.RequestMonitor;
import io.pivotal.arca.service.OperationService;
import io.tony.arcaretrofit.operations.CardListOperation;

public class CardListFragmentMonitor extends RequestMonitor.AbstractRequestMonitor {

    @Override
    public int onPostExecute(final Context context, final Query request, final QueryResult result) {
        final int count = result.getResult().getCount();
        if (count == 0 && getDataFromNetwork(context, request.getUri())) {
            return Flags.DATA_SYNCING;
        } else {
            return 0;
        }
    }

    private boolean getDataFromNetwork(final Context context, final Uri uri) {
        final boolean isSyncing = OperationService.start(context, new CardListOperation(uri));
        return isSyncing;
    }
}
