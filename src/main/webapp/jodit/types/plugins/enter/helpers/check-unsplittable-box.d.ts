/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/enter
 */
import type { IJodit } from '../../../types';
/**
 * Inside quote/tables cell, etc. you can't split so just add br
 * @private
 */
export declare function checkUnsplittableBox(jodit: IJodit, currentBox: HTMLElement): boolean;
