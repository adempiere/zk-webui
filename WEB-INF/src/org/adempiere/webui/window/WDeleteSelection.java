/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2016 ADempiere Foundation All Rights Reserved.               *
 *****************************************************************************/
package org.adempiere.webui.window;

import org.adempiere.controller.DeleteSelectionController;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zhtml.Pre;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.North;
import org.zkoss.zul.South;

import java.util.Vector;


/**
 * The DeleteSelectionController provides a MVC used for 
 * get data for delete records with selection
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/592">
 * 		@see FR [ 592 ] Delete Selection dialog is not MVC</a>
 */
public class WDeleteSelection extends DeleteSelectionController implements EventListener {

	
	
	public WDeleteSelection(GridTab tab) {
		super(tab);
	}

	/**	Main Container	*/
	private Window container = null;
	/**	List for Select	*/
	private Listbox listbox;
	/**	Confirm Panel	*/
	private ConfirmPanel confirmPanel;

	/**
	 * Init components
	 */
	private void initComponents() {
		container = new Window();
		container.setTitle(Msg.getMsg(Env.getCtx(), "DeleteSelection"));
		container.setAttribute("modal", Boolean.TRUE);
		ZKUpdateUtil.setWidth(container, "500px");
		ZKUpdateUtil.setHeight(container, "400px");
		container.setBorder("normal");
		container.setSizable(true);
		container.setClosable(true);
		container.setMaximizable(true);
		//	Init list
		listbox = new Listbox();
		// FR [ 2877111 ]
		Vector<String> data = getData();
		for(int i = 0; i < data.size(); i++) {
			String record = data.get(i);
			listbox.appendItem(record, record);
		}
		//	Is a multiple selection
		listbox.setMultiple(true);
		//	Instance Panel
		confirmPanel = new ConfirmPanel(true);
		//	
		Div div = new Div();
		div.setStyle("width: 100%; height: 100%");
		Pre pre = new Pre();
		Text text = new Text(Msg.getMsg(Env.getCtx(), "DeleteSelectionDescription"));
		text.setParent(pre);
		pre.setParent(div);
		//
		
		Borderlayout layout = new Borderlayout();
		layout.setParent(container);
		ZKUpdateUtil.setWidth(layout, "100%");
		ZKUpdateUtil.setHeight(layout, "100%");
		
		North north = new North();
		north.setParent(layout);
		north.appendChild(div);
		
		Center center = new Center();
		center.setParent(layout);
		center.appendChild(listbox);
		ZKUpdateUtil.setVflex(listbox,true);
		listbox.setSizedByContent(false);
		ZKUpdateUtil.setWidth(listbox, "100%");
		//
		South south = new South();
		south.setParent(layout);
		south.appendChild(confirmPanel);
		//	Add Listener
		confirmPanel.addActionListener(Events.ON_CLICK, this);
		//	Default Selected
		if(isDefaultSelected()
				&& getSelection() != null) {
			listbox.setSelectedIndices(getSelection());
		}
	}

	@Override
	public void showDialog() {
		initComponents();
		AEnv.showCenterScreen(container);
	}

	@Override
	public void onEvent(Event event) throws Exception {
		if(event.getTarget() == confirmPanel.getButton(ConfirmPanel.A_CANCEL)) {
			container.detach();
		} else if(event.getTarget() == confirmPanel.getOKButton()) {
			setSelection(listbox.getSelectedIndices());
			setIsOkPressed(true);
			container.detach();
		}
	}
}
