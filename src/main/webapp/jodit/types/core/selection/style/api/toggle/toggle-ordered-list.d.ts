/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { IJodit, CommitMode } from '../../../../../types';
import type { CommitStyle } from '../../commit-style';
/**
 * Replaces `ul->ol` or `ol->ul`, apply styles to the list, or remove a list item from it
 * @private
 */
export declare function toggleOrderedList(style: CommitStyle, li: HTMLElement, jodit: IJodit, mode: CommitMode): CommitMode;