package com.github.unchama.seichiassist.data.menu.slot.functional.toggle;

import com.github.unchama.seichiassist.data.menu.icon.Icon;
import com.github.unchama.seichiassist.data.menu.slot.functional.FunctionalSlot;
import com.github.unchama.seichiassist.util.builder.IconBuilder;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author karayuu
 */
public class ToggleSlot extends FunctionalSlot {
    /**
     * Toggleとして利用するFunctionalSlot
     */
    private List<FunctionalSlot> slots = new ArrayList<>();

    /**
     * Slotを生成します.
     *
     * @param builder スロットに表示するIconのBuilder ({@code null} は許容されません)
     */
    private ToggleSlot(@Nonnull IconBuilder<? extends Icon> builder) {
        super(builder);
    }
}
