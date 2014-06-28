package com.goku.breeze.ui.dialog;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.goku.breeze.util.FmeaUtil;

public class DlgFmeaRpnChart extends Dialog {
	private final Map<String, List<Integer>> rpnSeries;

	public DlgFmeaRpnChart(Shell parentShell, String path, String nodeId) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.rpnSeries = FmeaUtil.getRpnSeries(path, nodeId);

		super.setShellStyle(super.getShellStyle() | SWT.RESIZE | SWT.MODELESS);
		super.setBlockOnOpen(false);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("RPN Chart");
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
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		gl.marginWidth = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		gl.verticalSpacing = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		gl.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);

		composite.setLayout(gl);
		applyDialogFont(composite);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (String key : this.rpnSeries.keySet()) {
			List<Integer> list = this.rpnSeries.get(key);
			if (list == null) continue;
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i) == null) continue;
				dataset.addValue(list.get(i), key, i + "");
			}
		}

		JFreeChart chart = ChartFactory.createLineChart("RPN", "Index", "Value", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		LineAndShapeRenderer shapeRenderer = (LineAndShapeRenderer) plot.getRenderer();
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.pink);
		plot.setRangeGridlinePaint(Color.pink);
		plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));

		shapeRenderer.setBaseShapesVisible(true);
		CategoryItemRenderer xyitem = plot.getRenderer();
		xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		xyitem.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		plot.setRenderer(xyitem);

		ChartComposite chartComposite1 = new ChartComposite(composite, SWT.NONE, chart, true);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.widthHint = 600;
		gd.minimumHeight = 400;
		chartComposite1.setLayoutData(gd);

		try {
			ChartUtilities.saveChartAsPNG(new File("/home/goku/rpn.png"), chart, 800, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return composite;
	}
}
