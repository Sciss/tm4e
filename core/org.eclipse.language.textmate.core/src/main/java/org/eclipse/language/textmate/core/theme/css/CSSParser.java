/*******************************************************************************
 * Copyright (c) 2005, 2016 Angelo Zerr and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.language.textmate.core.theme.css;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import org.eclipse.language.textmate.core.internal.css.CSSConditionFactory;
import org.eclipse.language.textmate.core.internal.css.CSSDocumentHandler;
import org.eclipse.language.textmate.core.internal.css.CSSSelectorFactory;
import org.eclipse.language.textmate.core.internal.css.ExtendedSelector;
import org.eclipse.language.textmate.core.theme.IStyle;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Parser;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

/**
 * CSS Parser to parse style for TextMate syntax coloration.
 *
 */
public class CSSParser {

	private final CSSDocumentHandler handler;

	public CSSParser(InputStream source) throws Exception {
		this(toSource(source));
	}

	private static InputSource toSource(InputStream source) {
		InputSource in = new InputSource();
		in.setByteStream(source);
		return in;
	}

	public CSSParser(InputSource source) throws Exception {
		this(source, SACParserFactory.newInstance().makeParser());
	}

	public CSSParser(String source) throws Exception {
		this(new InputSource(new StringReader(source)));
	}

	public CSSParser(InputSource source, Parser parser) throws CSSException, IOException {
		this.handler = new CSSDocumentHandler();
		parser.setDocumentHandler(handler);
		parser.setConditionFactory(CSSConditionFactory.INSTANCE);
		parser.setSelectorFactory(CSSSelectorFactory.INSTANCE);
		parser.parseStyleSheet(source);
	}

	public IStyle getBestStyle(List<String> names) {
		int bestSpecificity = 0;
		IStyle bestStyle = null;
		for (IStyle style : handler.getList()) {
			SelectorList list = ((CSSStyle) style).getSelectorList();
			for (int i = 0; i < list.getLength(); i++) {
				Selector selector = list.item(i);
				if (selector instanceof ExtendedSelector) {
					ExtendedSelector s = ((ExtendedSelector) selector);
					int nbMatch = s.nbMatch(names);
					if (nbMatch > 0 && nbMatch == s.nbClass()) {
						if (bestStyle == null || (nbMatch > bestSpecificity)) {
							bestStyle = style;
							bestSpecificity = nbMatch;
						}
					}
				}
			}
		}
		return bestStyle;
	}

	public List<IStyle> getStyles() {
		return handler.getList();
	}

}