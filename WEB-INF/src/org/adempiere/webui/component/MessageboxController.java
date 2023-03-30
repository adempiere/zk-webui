package org.adempiere.webui.component;

import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class MessageboxController extends SelectorComposer<Messagebox> {

    @Listen("onCtrlKey = #btnOk")
    public void handleKeys(KeyEvent event) {
        System.out.println(event.getKeyCode()
                + ",ctrl pressed: " + event.isCtrlKey()
                + ",alt pressed: " + event.isAltKey()
                + ", shift pressed:" + event.isShiftKey());
    }
}
