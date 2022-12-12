/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module constants
 */
import type { IDictionary } from '../types';
export declare const INVISIBLE_SPACE = "\uFEFF";
export declare const NBSP_SPACE = "\u00A0";
export declare const INVISIBLE_SPACE_REG_EXP: () => RegExp;
export declare const INVISIBLE_SPACE_REG_EXP_END: () => RegExp;
export declare const INVISIBLE_SPACE_REG_EXP_START: () => RegExp;
export declare const SPACE_REG_EXP: () => RegExp;
export declare const SPACE_REG_EXP_START: () => RegExp;
export declare const SPACE_REG_EXP_END: () => RegExp;
export declare const IS_BLOCK: RegExp;
export declare const IS_INLINE: RegExp;
export declare const INSEPARABLE_TAGS: Array<keyof HTMLElementTagNameMap>;
export declare const MAY_BE_REMOVED_WITH_KEY: RegExp;
export declare const KEY_META = "Meta";
export declare const KEY_BACKSPACE = "Backspace";
export declare const KEY_TAB = "Tab";
export declare const KEY_ENTER = "Enter";
export declare const KEY_ESC = "Escape";
export declare const KEY_ALT = "Alt";
export declare const KEY_LEFT = "ArrowLeft";
export declare const KEY_UP = "ArrowUp";
export declare const KEY_RIGHT = "ArrowRight";
export declare const KEY_DOWN = "ArrowDown";
export declare const KEY_SPACE = "Space";
export declare const KEY_DELETE = "Delete";
export declare const KEY_F3 = "F3";
export declare const NEARBY = 5;
export declare const ACCURACY = 10;
export declare const COMMAND_KEYS: string[];
export declare const BR = "br";
export declare const PARAGRAPH = "p";
/**
 * WYSIWYG editor mode
 */
export declare const MODE_WYSIWYG = 1;
/**
 * html editor mode
 */
export declare const MODE_SOURCE = 2;
/**
 * Source code editor and HTML editor both like
 * @see http://getuikit.com/docs/htmleditor.html|this
 */
export declare const MODE_SPLIT = 3;
/**
 * Is Internet Explorer
 */
export declare const IS_IE: boolean;
/**
 * For IE11 it will be 'text'. Need for dataTransfer.setData
 */
export declare const TEXT_PLAIN: string;
export declare const TEXT_HTML: string;
export declare const TEXT_RTF: string;
export declare const MARKER_CLASS = "jodit-selection_marker";
export declare const EMULATE_DBLCLICK_TIMEOUT = 300;
/**
 * Paste the copied text as HTML, all content will be pasted exactly as it was on the clipboard.
 * So how would you copy its code directly into the source document.
 * ```
 * <h1 style="color:red">test</h1>
 * ```
 * Will be inserted into the document as
 * ```
 * <h1 style="color:red">test</h1>
 * ```
 */
export declare const INSERT_AS_HTML = "insert_as_html";
/**
 * Same as [[INSERT_AS_HTML]], but content will be stripped of extra styles and empty tags
 * ```html
 * <h1 style="color:red">test</h1>
 * ```
 * Will be inserted into the document as
 * ```html
 * <h1>test</h1>
 * ```
 */
export declare const INSERT_CLEAR_HTML = "insert_clear_html";
/**
 * The contents of the clipboard will be pasted into the document as plain text, i.e. all tags will be displayed as text.
 * ```html
 * <h1>test</h1>
 * ```
 * Will be inserted into the document as
 * ```html
 * &gt;&lt;h1&gt;test&lt;/h1&gt;
 * ```
 */
export declare const INSERT_AS_TEXT = "insert_as_text";
/**
 * All tags will be stripped:
 * ```html
 * <h1>test</h1>
 * ```
 * Will be inserted into the document as
 * ```html
 * test
 * ```
 */
export declare const INSERT_ONLY_TEXT = "insert_only_text";
export declare const SAFE_COUNT_CHANGE_CALL = 10;
export declare const IS_MAC: boolean;
export declare const KEY_ALIASES: IDictionary<string>;
export declare const BASE_PATH: string;
export declare const TEMP_ATTR = "data-jodit-temp";
export declare const lang: IDictionary<IDictionary<string>>;
export declare const CLIPBOARD_ID = "clipboard";
export declare const SOURCE_CONSUMER = "source-consumer";