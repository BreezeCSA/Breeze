package com.goku.breeze.ui.dialog.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.goku.breeze.util.FmeaUtil.FmeaLogItem;

public class FmeaLogTableContentProvider implements IStructuredContentProvider {
	public static int PageItemSize = 10;
	private final List<FmeaLogItem> displayLog = new ArrayList<FmeaLogItem>();
	private int pageNumber = 0;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		ArrayList<Object> ret = new ArrayList<Object>();
		for (int i = this.pageNumber * PageItemSize; i < (this.pageNumber + 1) * PageItemSize && i < this.displayLog.size(); ++i) {
			ret.add(this.displayLog.get(i));
		}
		return ret.toArray();
	}

	public int getTotalPageNumber() {
		if (this.displayLog.size() % PageItemSize == 0)
			return this.displayLog.size() / PageItemSize;
		return this.displayLog.size() / PageItemSize + 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		this.displayLog.clear();
		if (newInput == null)
			return;

		Object[] ni = (Object[]) newInput;
		Set<String> filter = (Set<String>) ni[0];
		List<FmeaLogItem> log = (List<FmeaLogItem>) ni[1];

		for (FmeaLogItem item : log) {
			if (filter.contains(item.field))
				this.displayLog.add(item);
		}
		this.pageNumber = 0;
	}

	public void setPageNumber(int pageNum) {
		this.pageNumber = pageNum;
	}

}
