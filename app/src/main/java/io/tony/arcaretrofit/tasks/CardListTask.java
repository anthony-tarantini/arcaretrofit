package io.tony.arcaretrofit.tasks;

import android.content.Context;

import java.util.List;

import io.pivotal.arca.service.Task;
import io.pivotal.arca.threading.Identifier;
import io.tony.arcaretrofit.models.Card;
import io.tony.arcaretrofit.providers.ArcaRetrofitContentProvider;
import io.tony.arcaretrofit.services.RetroService;

public class CardListTask extends Task<List<Card>> {
    @Override
    public Identifier<String> onCreateIdentifier() {
        return new Identifier<>(CardListTask.class.getSimpleName());
    }

    @Override
    public List<Card> onExecuteNetworking(Context context) throws Exception {
        return RetroService.getInstance().getApiService().getCardList();
    }

    @Override
    public void onExecuteProcessing(Context context, List<Card> data) throws Exception {
        context.getContentResolver().bulkInsert(ArcaRetrofitContentProvider.Uris.CARD, Card.toContentValues(data));
    }
}
