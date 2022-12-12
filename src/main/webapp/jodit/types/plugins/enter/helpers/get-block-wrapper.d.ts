/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/enter
 */
import type { IJodit, Nullable } from '../../../types';
/**
 * Finds a suitable parent block container
 * @private
 */
export declare function getBlockWrapper(jodit: IJodit, current: Node | null, tagReg?: RegExp): Nullable<HTMLElement>;