package com.richlosardo.swing.components;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.JTabbedPane;

public class DragDropTabbedPane extends JTabbedPane implements Transferable, DragGestureListener, DropTargetListener {

	private int dragTabIndex;
	
	public DragDropTabbedPane() {
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
		new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);		
	}
	
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] {DataFlavor.stringFlavor};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.stringFlavor);
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		return new StringSelection(String.valueOf(dragTabIndex));
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		int dragOverIndex = getUI().tabForCoordinate(this, dtde.getLocation().x, dtde.getLocation().y);
		if (dragOverIndex > -1) {
			dtde.acceptDrag(dtde.getDropAction());
		}
		else {
			dtde.rejectDrag();
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		int dropIndex = getUI().tabForCoordinate(this, dtde.getLocation().x, dtde.getLocation().y);
		if (dropIndex != dragTabIndex) {
			Component[] componentArray = getComponents();
			DragDropTabComponent dragPanel = (DragDropTabComponent) getComponent(dragTabIndex);
			removeAll();
			for (int i=0; i<=componentArray.length-1; i++) {
				if (i == dropIndex && dropIndex < dragTabIndex) {
					addTab(dragPanel.getTitle(), (Component) dragPanel);
				}
				if (componentArray[i] != dragPanel) {
					DragDropTabComponent panel = (DragDropTabComponent) componentArray[i];
					addTab(panel.getTitle(), (Component) panel);
				}
				if (i == dropIndex && dropIndex > dragTabIndex) {
					addTab(dragPanel.getTitle(), (Component) dragPanel);
				}
			}
			setSelectedIndex(dropIndex);
		}
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		dragTabIndex = getUI().tabForCoordinate(this, dge.getDragOrigin().x, dge.getDragOrigin().y);
		if (dragTabIndex > -1) {
			dge.startDrag(null, this);
		}
	}

}
