package com.goku.breeze.compiler.event;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;
import com.goku.breeze.compiler.model.BreezeObject;

public class TranslationEvent {
	public BaseSMVSourceFormatter formatter;
	public BreezeObject targetObject;
	public StringBuilder translationBuffer;
}
