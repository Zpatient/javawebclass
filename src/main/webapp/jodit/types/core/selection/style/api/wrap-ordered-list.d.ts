/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { IJodit } from '../../../../types';
import type { CommitStyle } from '../commit-style';
/**
 * Replaces non-leaf items with leaf items and either creates a new list or
 * adds a new item to the nearest old list
 * @private
 */
export declare function wrapOrderedList(commitStyle: CommitStyle, wrapper: HTMLElement, jodit: IJodit): HTMLElement;