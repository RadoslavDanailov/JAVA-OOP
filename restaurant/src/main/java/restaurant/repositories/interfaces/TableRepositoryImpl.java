package restaurant.repositories.interfaces;

import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TableRepositoryImpl implements TableRepository<Table> {
    private List<Table> tables;

    public TableRepositoryImpl() {
        this.tables = new ArrayList<>();
    }

    protected List<Table> getTables() {
        return tables;
    }

    protected void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableList(this.tables);
    }

    @Override
    public void add(Table entity) {
        this.tables.add(entity);
    }

    @Override
    public Table byNumber(int number) {
        for (Table table : this.getTables()){
            if (table.getTableNumber() == number){
                return table;
            }
        }
        return null;
    }
}
