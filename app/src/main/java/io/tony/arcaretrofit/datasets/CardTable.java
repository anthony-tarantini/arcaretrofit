package io.tony.arcaretrofit.datasets;


import io.pivotal.arca.provider.Column;
import io.pivotal.arca.provider.SQLiteTable;
import io.pivotal.arca.provider.Unique;

public class CardTable extends SQLiteTable {
    public interface Columns extends SQLiteTable.Columns {
        @Unique(Unique.OnConflict.REPLACE)
        @Column(Column.Type.INTEGER)
        String ID = "id";

        @Column(Column.Type.TEXT)
        String NAME = "name";

        @Column(Column.Type.TEXT)
        String MANACOST = "manacost";

        @Column(Column.Type.TEXT)
        String TEXT = "text";
    }
}
