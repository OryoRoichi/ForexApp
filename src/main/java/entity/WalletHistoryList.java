package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

public class WalletHistoryList {
    List<WalletHistory> list;

    public WalletHistoryList() {
        this.list = new ArrayList<>();
    }

    public void  addToList(WalletHistory historyNote) {
        list.add(historyNote);
    }

    @Override
    public String toString() {
        return "WalletHistoryList{" +
                "list=" + list +
                '}';
    }
}
