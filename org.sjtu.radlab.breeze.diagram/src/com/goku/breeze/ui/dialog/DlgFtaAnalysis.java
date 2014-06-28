package com.goku.breeze.ui.dialog;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

import com.goku.breeze.compiler.model.BreezeEvent;

public class DlgFtaAnalysis extends Dialog {
	public final static String TYPE_CUT_SET = "Cut Set";
	public final static String TYPE_PATH_SET = "Path Set";
	private List<Set<BreezeEvent>> eventSet = null;
	private int maxSizeOfSet = 0;
	private long probSum = 0;
	private final Map<Set<BreezeEvent>, Long> setImportance = new HashMap<Set<BreezeEvent>, Long>();
	private Table tbEventImpotantce = null;
	private Table tbEventSet = null;
	private final String type;

	public DlgFtaAnalysis(Shell parentShell, String type, List<Set<BreezeEvent>> equalEventSet) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.eventSet = equalEventSet;

		for (Set<BreezeEvent> set : this.eventSet)
			if (set.size() > this.maxSizeOfSet) this.maxSizeOfSet = set.size();

		this.type = type;

		super.setShellStyle(super.getShellStyle() | SWT.RESIZE | SWT.MODELESS);
		super.setBlockOnOpen(false);
	}

	private Map<BreezeEvent, Double> calculateEventImportance() {
		Map<BreezeEvent, Double> map = new HashMap<BreezeEvent, Double>();
		for (Set<BreezeEvent> set : this.eventSet) {
			long prob = this.setImportance.get(set);
			for (BreezeEvent event : set) {
				if (map.get(event) == null)
					map.put(event, (double) prob);
				else {
					Double d = map.get(event);
					d += (double) prob;
					map.put(event, d);
				}
			}
		}

		for (BreezeEvent event : map.keySet()) {
			Double d = map.get(event);
			d = d / this.probSum;
			map.put(event, d);
		}

		return map;
	}

	private void calculateEventSetImportance() {
		int maxSetSize = 0;
		for (Set<BreezeEvent> set : this.eventSet)
			if (set.size() > maxSetSize) maxSetSize = set.size();

		for (Set<BreezeEvent> set : this.eventSet) {
			long prob = 1;
			for (BreezeEvent event : set) {
				int p = 100;
				try {
					p = Integer.parseInt(event.getProbability());
				} catch (Exception e) {
					e.printStackTrace();
				}
				prob *= p;
			}
			for (int i = set.size(); i < maxSetSize; ++i)
				prob *= 100;
			this.setImportance.put(set, prob);
			this.probSum += prob;
		}
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(this.type + " Analysis");
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		if (id == IDialogConstants.OK_ID) {
			Button btn = super.createButton(parent, id, label, defaultButton);
			btn.setText("Close");
			return btn;
		}
		return null;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite baseComposite = new Composite(parent, SWT.NONE);
		baseComposite.setLayout(new GridLayout());

		ScrolledComposite sc = new ScrolledComposite(baseComposite, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setLayoutData(new GridData(780, 500));

		Composite composite = this.drawMainArea(sc);
		composite.setSize(600, 1400);
		composite.layout();

		sc.setContent(composite);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, 1400));
		sc.layout();
		return baseComposite;
	}

	private void drawEventImportance(Composite composite) {
		// draw atomic importance table
		Map<BreezeEvent, Double> eventImpo = this.calculateEventImportance();
		this.tbEventImpotantce = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd1.heightHint = 200;
		gd1.minimumHeight = 200;
		this.tbEventImpotantce.setLayoutData(gd1);
		this.tbEventImpotantce.setHeaderVisible(true);

		TableColumn tc = new TableColumn(this.tbEventImpotantce, SWT.NONE);
		tc.setText("atomic event");
		tc.setWidth(200);
		tc = new TableColumn(this.tbEventImpotantce, SWT.NONE);
		tc.setText("importance");
		tc.setWidth(200);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		DefaultPieDataset pieSet = new DefaultPieDataset();

		for (BreezeEvent event : eventImpo.keySet()) {
			Double d = eventImpo.get(event);
			TableItem item = new TableItem(this.tbEventImpotantce, SWT.NONE);
			item.setText(new String[] { event.getId(), d.toString() });

			//
			dataset.addValue(d, event.getId(), event.getId());
			pieSet.setValue(event.getId(), d);
		}

		// draw atomic event importance event chart
		JFreeChart chart0 = ChartFactory.createBarChart("Event Importance Chart", "Event", "Importance", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartComposite chartComposite0 = new ChartComposite(composite, SWT.NONE, chart0, true);
		GridData gd2 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd2.heightHint = 400;
		gd2.minimumHeight = 400;
		chartComposite0.setLayoutData(gd2);

		JFreeChart chart1 = ChartFactory.createPieChart("Importance Chart", pieSet, true, true, false);
		PiePlot plot = (PiePlot) chart1.getPlot();
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0} = {2}", new DecimalFormat("0"),
				new DecimalFormat("0.00%"));
		plot.setLabelGenerator(generator);
		ChartComposite chartComposite1 = new ChartComposite(composite, SWT.NONE, chart1, true);
		GridData gd3 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd3.heightHint = 400;
		gd3.minimumHeight = 400;
		chartComposite1.setLayoutData(gd3);

		try {
			ChartUtilities.saveChartAsPNG(new File("/home/goku/cutSetImportance.png"), chart0, 800, 600);
			ChartUtilities.saveChartAsPNG(new File("/home/goku/cutSetRate.png"), chart1, 800, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Composite drawMainArea(ScrolledComposite sc) {
		Composite composite = new Composite(sc, SWT.NONE);

		GridLayout gl = new GridLayout();
		gl.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		gl.marginWidth = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		gl.verticalSpacing = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		gl.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);

		composite.setLayout(gl);
		applyDialogFont(composite);

		Label lb0 = new Label(composite, SWT.NONE);
		lb0.setText("Minimum " + this.type);

		this.drawMinCutSetTable(composite);

		Label lb1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		lb1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label lb2 = new Label(composite, SWT.NONE);
		lb2.setText("Event Importance");

		this.drawEventImportance(composite);

		return composite;
	}

	private void drawMinCutSetTable(Composite composite) {
		// draw minimum cut set table
		this.tbEventSet = new Table(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		this.calculateEventSetImportance();
		GridData gd0 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd0.heightHint = 200;
		gd0.minimumHeight = 200;
		this.tbEventSet.setLayoutData(gd0);

		for (int i = 0; i <= this.maxSizeOfSet + 1; ++i) {
			TableColumn col = new TableColumn(this.tbEventSet, SWT.NONE);
			if (i == 0)
				col.setText("Index");
			else if (i <= this.maxSizeOfSet)
				col.setText("event");
			else col.setText("Importance");
			col.setWidth(100);
		}
		this.tbEventSet.setHeaderVisible(true);

		for (int i = 0; i < this.eventSet.size(); ++i) {
			TableItem item = new TableItem(this.tbEventSet, SWT.NONE);
			Set<BreezeEvent> eventSet = this.eventSet.get(i);
			String[] txt = new String[this.maxSizeOfSet + 2];
			int count = 0;
			txt[count++] = "" + i;
			for (BreezeEvent event : eventSet) {
				txt[count++] = event.getId();
			}

			for (int j = eventSet.size() + 1; j < this.maxSizeOfSet; ++j)
				txt[j] = "";
			txt[this.maxSizeOfSet + 1] = (((double) this.setImportance.get(eventSet)) / this.probSum) + "";
			item.setText(txt);
		}
	}

	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

}
