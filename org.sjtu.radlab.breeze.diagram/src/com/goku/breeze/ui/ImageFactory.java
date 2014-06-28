package com.goku.breeze.ui;

import org.eclipse.swt.graphics.Image;

import BreezeModel.diagram.part.BreezeDiagramEditorPlugin;

public class ImageFactory {
	static Image imgAdd, imgDelete, imgHome, imgPrev, imgNext, imgLog, imgChart;

	public static Image getImageAdd() {
		if (imgAdd == null)
			imgAdd = readImage("icons/obj16/add.png");
		return imgAdd;
	}

	public static Image getImageChart() {
		if (imgChart == null)
			imgChart = readImage("icons/obj16/chart.png");
		return imgChart;
	}

	public static Image getImageDelete() {
		if (imgDelete == null)
			imgDelete = readImage("icons/obj16/remove.png");
		return imgDelete;
	}

	public static Image getImageHome() {
		if (imgHome == null)
			imgHome = readImage("icons/obj16/home.png");
		return imgHome;
	}

	public static Image getImageLog() {
		if (imgLog == null)
			imgLog = readImage("icons/obj16/save.png");
		return imgLog;
	}

	public static Image getImageNext() {
		if (imgNext == null)
			imgNext = readImage("icons/obj16/next.png");
		return imgNext;
	}

	public static Image getImagePrev() {
		if (imgPrev == null)
			imgPrev = readImage("icons/obj16/prev.png");
		return imgPrev;
	}

	private static Image readImage(String path) {
		try {
			return BreezeDiagramEditorPlugin.getBundledImageDescriptor(path).createImage();
		} catch (Exception e) {
		}
		return null;
	}
}
