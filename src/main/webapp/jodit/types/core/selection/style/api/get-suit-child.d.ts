/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { Nullable } from '../../../../types';
import type { CommitStyle } from '../commit-style';
/**
 * Checks if child elements are suitable for applying styles.
 * An element is suitable for us only if it is the only significant child.
 * If the child matches then returns it.
 * @example
 * `<font><strong>selected</strong></font>`
 * @private
 */
export declare function getSuitChild(style: CommitStyle, font: HTMLElement): Nullable<HTMLElement>;