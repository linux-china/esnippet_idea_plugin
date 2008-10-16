package org.mvnsearch.snippet.plugin.util;

import com.intellij.openapi.util.text.StringUtil;
import org.mvnsearch.snippet.plugin.SnippetAppComponent;
import org.mvnsearch.snippet.impl.mvnsearch.SnippetService;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.util.List;

/**
 * snippet mnenonic completer
 *
 * @author linux_china@hotmail.com
 */
public class SnippetMnemonicCompleter extends AutoCompleter {
    public SnippetMnemonicCompleter(JTextComponent comp) {
        super(comp);
    }

    /**
     * update list model depending on the data in textfield
     *
     * @return data updated or not
     */
    protected boolean updateListData() {
        String value = textComp.getText();
        if (StringUtil.isNotEmpty(value)) {
            SnippetService service = SnippetAppComponent.getInstance().getSnippetService();
            List<String> mnemonicList = service.findMnemonicList(value);
            if (mnemonicList != null && mnemonicList.size() > 0) {
                if (!(mnemonicList.size() == 1 && mnemonicList.get(0).equals(value))) {
                    list.setListData(mnemonicList.toArray());
                    return true;
                }
            }
        }
        list.setListData(new String[0]);
        return true;
    }

    /**
     * user has selected some item in the list. update textfield accordingly
     *
     * @param selected item
     */
    protected void acceptedListItem(String selected) {
        if (selected == null)
            return;
        int prefixlen = textComp.getDocument().getLength();
        try {
            textComp.getDocument().insertString(textComp.getCaretPosition(), selected.substring(prefixlen), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
