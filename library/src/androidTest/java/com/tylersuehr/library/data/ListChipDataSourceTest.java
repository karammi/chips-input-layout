package com.tylersuehr.library.data;

import android.support.test.runner.AndroidJUnit4;

import com.tylersuehr.library.Mocker;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Copyright © 2017 Tyler Suehr
 *
 * Tests the full functionality of {@link ListChipDataSource}.
 *
 * @author Tyler Suehr
 * @version 1.0
 */
@RunWith(AndroidJUnit4.class)
public class ListChipDataSourceTest {
    @Test
    public void getSelectedChips() throws Exception {
        final ListChipDataSource ls = new ListChipDataSource();
        assertNotNull(ls.getSelectedChips());
    }

    @Test
    public void getFilteredChips() throws Exception {
        final ListChipDataSource ls = new ListChipDataSource();
        assertNotNull(ls.getFilteredChips());
    }

    @Test
    public void getOriginalChips() throws Exception {
        final ListChipDataSource ls = new ListChipDataSource();
        assertNotNull(ls.getOriginalChips());
    }

    @Test
    public void setFilterableChips() throws Exception {
        final List<Mocker.TestChip> chips = Mocker.mockChips(4);

        final ListChipDataSource ls = new ListChipDataSource();
        ls.setFilterableChips(chips);

        assertTrue(ls.selectedChips.isEmpty());
        assertTrue(ls.filteredChips.size() == chips.size());
        assertTrue(ls.originalChips.size() == chips.size());
    }

    @Test
    public void createFilteredChip() throws Exception {
        final ListChipDataSource ls = new ListChipDataSource();
        ls.createFilteredChip(Mocker.mock());
        assertFalse(ls.originalChips.isEmpty());
        assertFalse(ls.filteredChips.isEmpty());
    }

    @Test
    public void createSelectedChip() throws Exception {
        final ListChipDataSource ls = new ListChipDataSource();
        ls.createSelectedChip(Mocker.mock());
        assertFalse(ls.selectedChips.isEmpty());
    }

    @Test
    public void getFilteredChip() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createFilteredChip(chip);

        assertEquals(ls.getFilteredChip(0), chip);
    }

    @Test
    public void getSelectedChip() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createSelectedChip(chip);

        assertEquals(ls.getSelectedChip(0), chip);
    }

    @Test
    public void takeChip() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createFilteredChip(chip);
        ls.takeChip(chip);

        assertFalse(ls.originalChips.contains(chip));
        assertFalse(ls.filteredChips.contains(chip));
        assertTrue(ls.selectedChips.contains(chip));
    }

    @Test
    public void takeChipNonExistent() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();

        final ListChipDataSource ls = new ListChipDataSource();
        ls.takeChip(chip);

        assertFalse(ls.originalChips.contains(chip));
        assertFalse(ls.filteredChips.contains(chip));
        assertTrue(ls.selectedChips.contains(chip));
        assertFalse(ls.selectedChips.get(0).isFilterable());
    }

    @Test
    public void takeChipByPosition() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createFilteredChip(chip);
        ls.takeChip(0);

        assertFalse(ls.originalChips.contains(chip));
        assertFalse(ls.filteredChips.contains(chip));
        assertTrue(ls.selectedChips.contains(chip));
    }

    @Test
    public void replaceChip() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();
        chip.setFilterable(true);

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createSelectedChip(chip);
        ls.replaceChip(chip);

        assertFalse(ls.selectedChips.contains(chip));
        assertTrue(ls.originalChips.contains(chip));
        assertTrue(ls.filteredChips.contains(chip));
    }

    @Test
    public void replaceChipByPosition() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();
        chip.setFilterable(true);

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createSelectedChip(chip);
        ls.replaceChip(0);

        assertFalse(ls.selectedChips.contains(chip));
        assertTrue(ls.originalChips.contains(chip));
        assertTrue(ls.filteredChips.contains(chip));
    }

    @Test
    public void existsInDataSource_filtered() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createFilteredChip(chip);

        assertTrue(ls.existsInDataSource(chip));
    }

    @Test
    public void existsInDataSource_selected() throws Exception {
        final Mocker.TestChip chip = Mocker.mock();

        final ListChipDataSource ls = new ListChipDataSource();
        ls.createSelectedChip(chip);

        assertTrue(ls.existsInDataSource(chip));
    }
}