/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { Nullable } from '../../../../types';
import type { CommitStyle } from '../commit-style';
/**
 * Checks if an item is suitable for applying a commit. The element suits us if it
 *  - has the same styles as in the commit (commitStyle.options.style)
 *  - has the same tag as in the commit (commitStyle.options.element)
 *
 * @param commitStyle - style commit
 * @param elm - checked item
 * @param strict - strict mode - false - the default tag is suitable for us if it is also in the commit
 * @private
 */
export declare function isSuitElement(commitStyle: CommitStyle, elm: Nullable<Node>, strict: boolean): elm is HTMLElement;
/**
 * Inside the parent element there is a block with the same styles
 * @example
 * For selection:
 * ```html
 * <p>|test<strong>test</strong>|</p>
 * ```
 * Apply `{element:'strong'}`
 */
export declare function isSameStyleChild(commitStyle: CommitStyle, elm: Nullable<Node>): elm is HTMLElement;