/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * [[include:core/selection/README.md]]
 * @packageDocumentation
 * @module selection
 */
import type { CanUndef, HTMLTagNames, IDictionary, IJodit, ISelect, IStyle, MarkerInfo, Nullable } from '../../types';
export declare class Select implements ISelect {
    readonly jodit: IJodit;
    constructor(jodit: IJodit);
    /**
     * Short alias for this.jodit
     */
    private get j();
    /**
     * Throw Error exception if parameter is not Node
     */
    private errorNode;
    /**
     * Return current work place - for Jodit is Editor
     */
    private get area();
    /**
     * Editor Window - it can be different for iframe mode
     */
    private get win();
    /**
     * Current jodit editor doc
     */
    private get doc();
    /**
     * Return current selection object
     */
    get sel(): ISelect['sel'];
    /**
     * Return first selected range or create new
     */
    get range(): Range;
    /**
     * Checks if the selected text is currently inside the editor
     */
    get isInsideArea(): boolean;
    /**
     * Return current selection object
     * @param select - Immediately add in selection
     */
    createRange(select?: boolean): Range;
    /**
     * Remove all selected content
     */
    remove(): void;
    /**
     * Clear all selection
     */
    clear(): void;
    /**
     * Remove node element from editor
     */
    removeNode(node: Node): void;
    /**
     * Insert the cursor to any point x, y
     *
     * @param x - Coordinate by horizontal
     * @param y - Coordinate by vertical
     * @returns false - Something went wrong
     */
    insertCursorAtPoint(x: number, y: number): boolean;
    /**
     * Define element is selection helper
     */
    static isMarker(elm: Nullable<Node>): elm is HTMLElement;
    /**
     * Check if editor has selection markers
     */
    get hasMarkers(): boolean;
    /**
     * Check if editor has selection markers
     */
    get markers(): HTMLElement[];
    /**
     * Remove all markers
     */
    removeMarkers(): void;
    /**
     * Create marker element
     */
    marker(atStart?: boolean, range?: Range): HTMLSpanElement;
    /**
     * Restores user selections using marker invisible elements in the DOM.
     */
    restore(): void;
    /**
     * Saves selections using marker invisible elements in the DOM.
     * @param silent - Do not change current range
     */
    save(silent?: boolean): MarkerInfo[];
    /**
     * Set focus in editor
     */
    focus(options?: FocusOptions): boolean;
    /**
     * Checks whether the current selection is something or just set the cursor is
     * @returns true Selection does't have content
     */
    isCollapsed(): boolean;
    /**
     * Checks whether the editor currently in focus
     */
    isFocused(): boolean;
    /**
     * Returns the current element under the cursor inside editor
     */
    current(checkChild?: boolean): null | Node;
    /**
     * Insert element in editor
     *
     * @param insertCursorAfter - After insert, cursor will move after element
     * @param fireChange - After insert, editor fire change event. You can prevent this behavior
     */
    insertNode(node: Node, insertCursorAfter?: boolean, fireChange?: boolean): void;
    /**
     * Inserts in the current cursor position some HTML snippet
     *
     * @param html - HTML The text to be inserted into the document
     * @example
     * ```javascript
     * parent.s.insertHTML('<img src="image.png"/>');
     * ```
     */
    insertHTML(html: number | string | Node, insertCursorAfter?: boolean): void;
    /**
     * Insert image in editor
     *
     * @param url - URL for image, or HTMLImageElement
     * @param styles - If specified, it will be applied <code>$(image).css(styles)</code>
     */
    insertImage(url: string | HTMLImageElement, styles?: Nullable<IDictionary<string>>, defaultWidth?: Nullable<number | string>): void;
    /**
     * Call callback for all selection node
     */
    eachSelection(callback: (current: Node) => void): void;
    /**
     * Checks if the cursor is at the end(start) block
     *
     * @param  start - true - check whether the cursor is at the start block
     * @param parentBlock - Find in this
     *
     * @returns true - the cursor is at the end(start) block, null - cursor somewhere outside
     */
    cursorInTheEdge(start: boolean, parentBlock: HTMLElement): Nullable<boolean>;
    /**
     * Wrapper for cursorInTheEdge
     */
    cursorOnTheLeft(parentBlock: HTMLElement): Nullable<boolean>;
    /**
     * Wrapper for cursorInTheEdge
     */
    cursorOnTheRight(parentBlock: HTMLElement): Nullable<boolean>;
    /**
     * Set cursor after the node
     * @returns fake invisible textnode. After insert it can be removed
     */
    setCursorAfter(node: Node): Nullable<Text>;
    /**
     * Set cursor before the node
     * @returns fake invisible textnode. After insert it can be removed
     */
    setCursorBefore(node: Node): Nullable<Text>;
    /**
     * Add fake node for new cursor position
     */
    private setCursorNearWith;
    /**
     * Set cursor in the node
     * @param inStart - set cursor in start of element
     */
    setCursorIn(node: Node, inStart?: boolean): Node;
    /**
     * Set range selection
     */
    selectRange(range: Range, focus?: boolean): this;
    /**
     * Select node
     * @param inward - select all inside
     */
    select(node: Node | HTMLElement | HTMLTableElement | HTMLTableCellElement, inward?: boolean): this;
    /**
     * Return current selected HTML
     * @example
     * ```javascript
     * const editor = Jodit.make();
     * console.log(editor.s.html); // html
     * console.log(Jodit.modules.Helpers.stripTags(editor.s.html)); // plain text
     * ```
     */
    get html(): string;
    /**
     * Wrap all selected fragments inside Tag or apply some callback
     */
    wrapInTagGen(): Generator<HTMLElement>;
    /**
     * Wrap all selected fragments inside Tag or apply some callback
     */
    wrapInTag(tagOrCallback: HTMLTagNames | ((font: HTMLElement) => any)): HTMLElement[];
    /**
     * Apply some css rules for all selections. It method wraps selections in nodeName tag.
     * @example
     * ```js
     * const editor = Jodit.make('#editor');
     * editor.value = 'test';
     * editor.execCommand('selectall');
     *
     * editor.s.applyStyle({color: 'red'}) // will wrap `text` in `span` and add style `color:red`
     * editor.s.applyStyle({color: 'red'}) // will remove `color:red` from `span`
     * ```
     */
    applyStyle(style: CanUndef<IStyle>, options?: {
        /**
         * equal CSSRule (e.g. strong === font-weight: 700)
         */
        element?: HTMLTagNames;
        className?: string;
        /**
         * tag for wrapping and apply styles
         */
        defaultTag?: HTMLTagNames;
    }): void;
    /**
     * Split selection on two parts: left and right
     */
    splitSelection(currentBox: HTMLElement): Nullable<Element>;
    expandSelection(): this;
}
