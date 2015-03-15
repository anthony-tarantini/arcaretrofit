package io.tony.arcaretrofit.fragments;

import java.util.Arrays;
import java.util.Collection;

import io.pivotal.arca.adapters.Binding;
import io.pivotal.arca.dispatcher.Query;
import io.pivotal.arca.fragments.ArcaFragment;
import io.pivotal.arca.fragments.ArcaFragmentBindings;
import io.pivotal.arca.fragments.ArcaSimpleAdapterFragment;
import io.tony.arcaretrofit.R;
import io.tony.arcaretrofit.binders.CardListFragmentViewBinder;
import io.tony.arcaretrofit.datasets.CardTable;
import io.tony.arcaretrofit.monitors.CardListFragmentMonitor;
import io.tony.arcaretrofit.providers.ArcaRetrofitContentProvider;

@ArcaFragment(
        fragmentLayout = R.layout.fragment_card_list,
        adapterItemLayout = R.layout.list_item_card,
        binder = CardListFragmentViewBinder.class,
        monitor = CardListFragmentMonitor.class
)
public class CardListFragment extends ArcaSimpleAdapterFragment {
    @ArcaFragmentBindings
    private static final Collection<Binding> BINDINGS = Arrays.asList(
            new Binding(R.id.list_item_card_image, CardTable.Columns.ID),
            new Binding(R.id.list_item_card_name, CardTable.Columns.NAME),
            new Binding(R.id.list_item_card_manacost, CardTable.Columns.MANACOST),
            new Binding(R.id.list_item_card_text, CardTable.Columns.TEXT)
    );

    @Override
    public int getAdapterViewId() {
        return android.R.id.list;
    }

    @Override
    public void onStart() {
        super.onStart();
        final Query query = new Query(ArcaRetrofitContentProvider.Uris.CARD);
        execute(query);
    }
}
