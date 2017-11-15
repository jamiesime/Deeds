package example.codeclan.com.deeds;

import org.junit.Test;

import java.util.ArrayList;

import static example.codeclan.com.deeds.Deed.all;

/**
 * Created by user on 15/11/2017.
 */

public class TestDeed {

    DBHelper dbHelper;

    @Test
    public void testSortByDate() throws Exception {
        ArrayList<Deed> deedsToSort = Deed.all(dbHelper);
        Deed.sortByDate(deedsToSort);
    }
}
