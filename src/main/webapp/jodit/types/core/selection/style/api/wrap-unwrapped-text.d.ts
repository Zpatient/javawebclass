/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { IJodit } from '../../../../types';
import type { CommitStyle } from '../commit-style';
/**
 * Wrap text or inline elements inside Block element
 * @private
 */
export declare function wrapUnwrappedText(style: CommitStyle, elm: Node, jodit: IJodit, getRange: () => Range): HTMLElement;